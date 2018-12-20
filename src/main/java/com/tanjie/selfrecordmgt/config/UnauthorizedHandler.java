package com.tanjie.selfrecordmgt.config;

import com.tanjie.selfrecordmgt.model.result.Result;
import com.tanjie.selfrecordmgt.model.result.ResultCode;
import com.tanjie.selfrecordmgt.model.result.ReturnData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 未登录或无权限时触发的操作
 * 返回：没有携带token或者token无效
 *
 * @author tanjie
 */
@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        //返回json形式的错误信息

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        Result failure = Result.failure(ResultCode.PERMISSION_TOKEN_ERROR, new ReturnData().addObj("errMsg",e.getMessage()));
        httpServletResponse.getWriter().println(failure.toJson());
        httpServletResponse.getWriter().flush();
    }
}
