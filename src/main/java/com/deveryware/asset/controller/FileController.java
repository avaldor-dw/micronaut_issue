/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */

package com.deveryware.asset.controller;

import com.deveryware.asset.exception.FileNotFoundException;
import com.deveryware.asset.service.AssetService;
import com.deveryware.asset.service.FileService;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Status;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;
import io.micronaut.http.server.types.files.SystemFile;
import io.micronaut.validation.Validated;
import lombok.extern.slf4j.Slf4j;

@Validated
@Controller("${micronaut.application.name}/files")
@Slf4j
class FileController {
    private final AssetService assetService;

    private final FileService fileService;

    FileController(AssetService assetService, FileService fileService) {
        this.assetService = assetService;
        this.fileService = fileService;
    }

    @Value("${micronaut.application.name}")
    private String basePath;

    @Get("/{uid}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Status(HttpStatus.OK)
    public SystemFile getFile(@PathVariable("uid") String uid) {
        return fileService.getFile(uid).orElseThrow(FileNotFoundException::new);
    }

    @Error(global = true)
    public HttpResponse<JsonError> basicError(HttpRequest request, Exception e) {
        JsonError error = new JsonError("Internal Server Error: " + e.getMessage()).link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.<JsonError>notFound().body(error);
    }
}
