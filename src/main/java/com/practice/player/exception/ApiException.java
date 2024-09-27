package com.practice.player.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApiException extends RuntimeException{

    public ApiException(String id) {super("did not find player with id: " + id); }
}
