/*
 * Copyright (c) 2010, 2013 Carlos Romel Pereira da Silva <carlos.romel@gmail.com>
 */
package br.eti.romel.rfs.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

public interface RFS {

    UUID setFile(String nomeArquivo) throws FileNotFoundException;

    UUID setFile(File arquivo) throws FileNotFoundException;

    UUID setFile(InputStream arquivo, RFSInfo info);

    String getPath(UUID uuid);

    InputStream getFile(UUID uuid) throws FileNotFoundException;

    RFSInfo getFileInfo(UUID uuid) throws FileNotFoundException;
}
