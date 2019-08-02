/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */
package com.deveryware.asset.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetDto {
    private String uid;

    private String name;

    private String groupIb;
}
