package com.tanjie.selfrecordmgt.exception;

import com.tanjie.selfrecordmgt.model.result.Result;
import com.tanjie.selfrecordmgt.model.result.ResultCode;
import com.tanjie.selfrecordmgt.model.result.ReturnData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 全局异常处理器
 *
 * @author tanjie
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(ParamException.class)
    @ResponseBody
    public Result paramExceptionHandler(HttpServletRequest request, ParamException exception) {
        return Result.failure(ResultCode.PARAM_TYPE_BIND_ERROR, new ReturnData().addObj("errorMessage", exception.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result httpMessageNotReadableExceptionHandler(HttpServletRequest request, Exception exception) {
        HttpMessageNotReadableException e = (HttpMessageNotReadableException) exception;
//        Throwable cause = e.getCause();
        ReturnData data = new ReturnData().addObj("cause", exception.getMessage());

        log.info("httpMessageNotReadableExceptionHandler 参数无法解析异常===>" + exception.getMessage());
        return Result.failure(ResultCode.PARAM_TYPE_BIND_ERROR, data);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest request, Exception exception) {

        exception.printStackTrace();
        return Result.failure(ResultCode.SYSTEM_INNER_ERROR, new ReturnData().addObj("errorMessage", exception.getMessage()));
    }


    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public Result systemExceptionHandler(HttpServletRequest request, Exception exception) {

        return Result.failure(ResultCode.SYSTEM_INNER_ERROR, new ReturnData().addObj("errorMessage", exception.getMessage()));
    }

}
