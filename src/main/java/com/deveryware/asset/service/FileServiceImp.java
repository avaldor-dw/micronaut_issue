/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */

package com.deveryware.asset.service;

import com.deveryware.asset.configuration.AssetConfiguration;
import io.micronaut.http.server.types.files.SystemFile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.inject.Singleton;
import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@Singleton
@Slf4j
@AllArgsConstructor
public class FileServiceImp implements FileService {
    private final AssetConfiguration assetConfiguration;

    @Override
    public Optional<SystemFile> getFile(String uid) {
        String folder = assetConfiguration.getFilepath() + uid;
        log.debug("Getting file in folder {}" + folder);
        File file = new File(folder);
        if (file.exists() && file.isDirectory()) {
            Optional<File> file1 = Arrays.stream(file.listFiles()).findFirst();
            if (file1.isPresent()) {
                SystemFile attach = new SystemFile(file1.get()).attach(file1.get().getName());
                return of(attach);
            }
        }
        return empty();
    }
}
