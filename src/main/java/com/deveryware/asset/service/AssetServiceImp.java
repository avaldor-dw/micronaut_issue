/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */

package com.deveryware.asset.service;

import com.deveryware.asset.controller.model.AssetDto;
import com.deveryware.asset.dao.AssetDao;
import com.deveryware.asset.dao.model.Asset;
import lombok.extern.slf4j.Slf4j;
import javax.inject.Singleton;

@Singleton
@Slf4j
public class AssetServiceImp implements AssetService {
    private final AssetDao assetDao;

    public AssetServiceImp(AssetDao assetDao) {
        this.assetDao = assetDao;
    }

    @Override
    public AssetDto createAsset(AssetDto assetDto) {
        log.debug("AssetDto info to create {}", assetDto.toString());

        Asset asset = new Asset(assetDto.getUid(), assetDto.getName(), assetDto.getGroupIb());

        log.debug("Create new Asset {}", asset.toString());

        assetDao.createAsset(asset);
        return assetDto;
    }

    @Override
    public AssetDto updateAsset(AssetDto assetDto) {
        return new AssetDto();
    }
}
