package com.littlecow.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义运行时异常，如博客找不到，资源找不到等
 * */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(){
    }

    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(String message,Throwable cause){
        super(message,cause);
    }
}
