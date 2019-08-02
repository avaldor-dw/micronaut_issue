/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */
package com.deveryware.asset.service;

import lombok.extern.slf4j.Slf4j;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Singleton
@Slf4j
public class FileWriterImpl implements FileWriter {
    public void write(InputStream inputStream, Path path, String filename) throws IOException {
        Files.createDirectories(path);
        Files.copy(inputStream, Paths.get(path.toString(), filename), StandardCopyOption.REPLACE_EXISTING);
    }
}
