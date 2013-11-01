/*
 * Copyright (c) 2010, 2013 Carlos Romel Pereira da Silva <carlos.romel@gmail.com>
 */
package br.eti.romel.rfs.core;

import java.io.*;
import java.io.InputStream;
import java.util.*;

public interface RFS {

    public UUID setFile(String nomeArquivo) throws FileNotFoundException;

    public UUID setFile(File arquivo) throws FileNotFoundException;

    public UUID setFile(InputStream arquivo, RFSInfo info);

    public String getPath(UUID uuid);

    public InputStream getFile(UUID uuid) throws FileNotFoundException;

    public RFSInfo getFileInfo(UUID uuid) throws FileNotFoundException;
}
