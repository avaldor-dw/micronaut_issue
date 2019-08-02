/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */

package com.deveryware.asset.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Data;

@ConfigurationProperties("asset")
@Data
public class AssetConfiguration {
    private String filepath;
}
