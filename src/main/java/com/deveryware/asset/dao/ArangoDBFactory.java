/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */

package com.deveryware.asset.dao;

import com.arangodb.ArangoDB;
import com.deveryware.asset.configuration.ArangoDBConfiguration;
import io.micronaut.context.annotation.Factory;
import lombok.extern.slf4j.Slf4j;
import javax.inject.Singleton;

@Factory
@Slf4j
public class ArangoDBFactory {
    private ArangoDBConfiguration arangoDBConfiguration;

    public ArangoDBFactory(ArangoDBConfiguration arangoDBConfiguration) {
        this.arangoDBConfiguration = arangoDBConfiguration;
    }

    @Singleton
    public ArangoDB getArangoDB() {
        log.info("Init arangodb...");
        ArangoDB arangoDB = new ArangoDB.Builder().host(arangoDBConfiguration.getHost(), arangoDBConfiguration.getPort())
                .user(arangoDBConfiguration.getUser())
                .password(arangoDBConfiguration.getPassword())
                .acquireHostList(false)
                .build();
        throw new RuntimeException();
        //return arangoDB;
    }
}
