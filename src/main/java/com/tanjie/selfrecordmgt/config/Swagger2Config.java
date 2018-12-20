package com.tanjie.selfrecordmgt.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author tanjie
 * Desc: swagger2配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean("Apis")
    public Docket userApis() {
        ParameterBuilder acceptPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        acceptPar.name("accept").description("在accept中设置版本信息")
                .modelRef(new ModelRef("string")).parameterType("header").defaultValue("application/json, text/plain, */*; charset=utf-8 ;version=dev_1")
                .required(false).build();
        //header中的ticket参数非必填，传空也可以
        //根据每个方法名也知道当前方法在设置什么参数
        pars.add(acceptPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("测试版本1")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo())
                .enable(true)
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("平台接口文档")
                .description("接口测试")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("token", "token", "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!login).*$"))
                        .build()
        );
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference("token", authorizationScopes));
    }
}