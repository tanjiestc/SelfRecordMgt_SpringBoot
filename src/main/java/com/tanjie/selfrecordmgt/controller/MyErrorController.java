package com.tanjie.selfrecordmgt.controller;


import com.tanjie.selfrecordmgt.model.result.ReturnData;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//@Controller
public class MyErrorController extends AbstractErrorController {

    public MyErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping("/error")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new ReturnData().addObj("msg","error"), status);
    }



    @Override
    public String getErrorPath() {
        return "/error";
    }
}
