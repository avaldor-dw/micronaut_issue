/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */

package com.deveryware.asset.dao;

import com.arangodb.ArangoDB;
import com.deveryware.asset.configuration.ArangoDBConfiguration;
import com.deveryware.asset.dao.model.Asset;
import lombok.extern.slf4j.Slf4j;
import javax.inject.Singleton;

@Singleton
@Slf4j
class AssetDaoImpl implements AssetDao {
    public static final String ASSETS = "assets";

    private final ArangoDB arangoDB;

    private ArangoDBConfiguration arangoDBConfiguration;

    AssetDaoImpl(ArangoDB arangoDB, ArangoDBConfiguration arangoDBConfiguration) {
        this.arangoDB = arangoDB;
        this.arangoDBConfiguration = arangoDBConfiguration;
    }

    @Override
    public void createAsset(Asset asset) {
        arangoDB.db(arangoDBConfiguration.getDbName()).collection(ASSETS).insertDocument(asset);
    }
}
