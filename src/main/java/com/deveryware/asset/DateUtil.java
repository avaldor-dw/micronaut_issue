/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */

package com.deveryware.asset;

import lombok.extern.slf4j.Slf4j;
import java.time.Instant;

@Slf4j
public class DateUtil {
    private DateUtil() {
    }

    public static long getUnixTime() {
        return Instant.now().getEpochSecond();
    }
}
