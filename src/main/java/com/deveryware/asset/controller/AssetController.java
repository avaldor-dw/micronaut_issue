/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */

package com.deveryware.asset.controller;

import com.deveryware.asset.controller.model.AssetDto;
import com.deveryware.asset.service.AssetService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Status;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.UUID;

@Controller("${micronaut.application.name}/assets")
@Slf4j
@AllArgsConstructor
public class AssetController {
    private AssetService assetService;

    private MeterRegistry meterRegistry;

    @Get("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Status(HttpStatus.OK)
    public AssetDto getAsset(@PathVariable("uuid") String uid) {
        return new AssetDto();
    }

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Status(HttpStatus.CREATED)
    public AssetDto saveAsset(@Header("sso_login_id") String userId, @Header("case_id") String caseId, AssetDto assetDto) {
        String uuid = UUID.randomUUID().toString();
        assetDto.setUid(uuid);
        assetService.createAsset(assetDto);
        return assetDto;
    }

    @Put("/{uid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Status(HttpStatus.OK)
    public void updateAsset(@Header("sso_login_id") String userId, @Header("case_id") String caseId,
                            @PathVariable("uid") String uid, AssetDto assetDto) {
        assetDto.setUid(uid);
        assetService.updateAsset(assetDto);
    }
}
