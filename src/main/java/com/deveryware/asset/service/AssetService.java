/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */

package com.deveryware.asset.service;

import com.deveryware.asset.controller.model.AssetDto;

public interface AssetService {
    AssetDto createAsset(AssetDto assetDto);

    AssetDto updateAsset(AssetDto assetDto);
}
