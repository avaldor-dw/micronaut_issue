/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */

package com.deveryware.asset.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {NotFoundException.class, ExceptionHandler.class})
@Slf4j
public class NotFoundExceptionHandler implements ExceptionHandler<NotFoundException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, NotFoundException exception) {
        log.error("Error", exception);
        return HttpResponse.notFound().body("toto");
    }
}
