/*
 * Copyright (C) 2019 Deveryware S.A.S. All Rights Reserved.
 */
package com.deveryware.asset.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * Created by Quoc PHAN on 23/07/2019.
 */
public interface FileWriter {
    void write(InputStream inputStream, Path path, String filename) throws IOException;
}
