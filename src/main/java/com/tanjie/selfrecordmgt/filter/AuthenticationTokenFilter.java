package com.tanjie.selfrecordmgt.filter;

import com.tanjie.selfrecordmgt.model.result.Result;
import com.tanjie.selfrecordmgt.model.result.ResultCode;
import com.tanjie.selfrecordmgt.service.impl.UserDetailsServiceImpl;
import com.tanjie.selfrecordmgt.utils.HeaderProcessUtils;
import com.tanjie.selfrecordmgt.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解析 token 的过滤器
 * 配置在 Spring Security 的配置类中
 * 用于解析 token ，将用户所有的权限写入本次 Spring Security 的会话中
 *
 * @author tanjie
 */
@Slf4j
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * json web token 在请求头的名字
     */
    @Value("${token.header}")
    private String tokenHeader;

    /**
     * 辅助操作 token 的工具类
     */
    @Autowired
    private TokenUtils tokenUtils;

    /**
     * Spring Security 的核心操作服务类
     * 在当前类中将使用 UserDetailsService 来获取 userDetails 对象
     */
    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            String accept = request.getHeader(HttpHeaders.ACCEPT);
            String versionInfo = HeaderProcessUtils.handleAccept(accept);
            //请求头中有正确的版本号信息
            if (StringUtils.isNotEmpty(versionInfo) && HeaderProcessUtils.isContainVersionInfo(versionInfo)) {

            }

            String authToken = request.getHeader(this.tokenHeader);
            String username = this.tokenUtils.getUsernameFromToken(authToken);
            //&& SecurityContextHolder.getContext().getAuthentication() == null
            if (username != null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (this.tokenUtils.validateToken(authToken, userDetails)) {
                    // 生成通过认证
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // 将权限写入本次会话
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                if (!userDetails.isEnabled()) {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().print(Result.failure(ResultCode.USER_ACCOUNT_FORBIDDEN).toJson());
                    return;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

}
