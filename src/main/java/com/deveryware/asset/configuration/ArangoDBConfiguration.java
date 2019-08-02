/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */

package com.deveryware.asset.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("asset.db.arangodb")
@Getter
@Setter
public class ArangoDBConfiguration {
    private String dbName;

    private String host;

    private int port;

    private String password;

    private String user;
}
