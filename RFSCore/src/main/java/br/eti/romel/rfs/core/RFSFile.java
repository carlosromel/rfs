/**
 * Copyright (c) 2010, 2013 Carlos Romel Pereira da Silva
 * <carlos.romel@gmail.com>
 */
package br.eti.romel.rfs.core;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RFSFile implements RFS {

    private static final String INEXISTENT_ROOT = "A raiz informada (%s) não existe ou está inacessível.";
    private static final String FILE_NOT_FOUND = "O arquivo (%s) não foi encontrado.";
    public static final String DAT_FILE = "%s/%s.dat";
    public static final String XML_FILE = "%s/%s.xml";

    private File repositoryRoot;

    public RFSFile(File repositoryRoot) throws RFSDirectoryNotFoundException {

        if (repositoryRoot.exists()) {
            this.repositoryRoot = repositoryRoot;
        } else {
            throw new RFSDirectoryNotFoundException(String.format(INEXISTENT_ROOT, repositoryRoot.getAbsolutePath()));
        }
    }

    public File getRepositoryRoot() {

        return repositoryRoot;
    }

    public RFSInfo getFileInfo(UUID uuid) throws FileNotFoundException {
        String fileName = String.format(XML_FILE, getPath(uuid), uuid);

        return (RFSInfo) new XStream().fromXML(new File(fileName));
    }

    public String getPath(UUID uuid) {
        final int density = 2;
        final String raw = uuid.toString().replace("-", "");
        String path = this.repositoryRoot.getAbsolutePath();

        for (int p = 0; p < (raw.length()); p += density) {
            path = path.concat("/").concat(raw.substring(p, p + density));
        }

        return path;
    }

    public InputStream getFile(UUID uuid) throws FileNotFoundException {
        final String fileName = String.format(DAT_FILE, getPath(uuid), uuid);
        InputStream file = null;

        if (new File(fileName).exists()) {
            try {
                file = new FileInputStream(fileName);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RFSFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new FileNotFoundException(String.format(FILE_NOT_FOUND, fileName));
        }

        return file;
    }

    public UUID setFile(String fileName) throws FileNotFoundException {
        return setFile(new File(fileName));
    }

    public UUID setFile(File file) throws FileNotFoundException {
        RFSInfo fileInfo = new RFSInfo();

        fileInfo.setAuthor(System.getenv("USER"));
        fileInfo.setCreationDate(file.lastModified());
        fileInfo.setFileName(file.getName());
        fileInfo.setSize(file.length());
        fileInfo.setType(null);
        fileInfo.setOriginalPath(file.getAbsolutePath());

        return setFile(new FileInputStream(file), fileInfo);
    }

    public UUID setFile(InputStream file, RFSInfo fileInfo) {
        UUID uuid = UUID.randomUUID();
        String caminhoDestino = getPath(uuid);

        if ((new File(caminhoDestino).exists()) || new File(caminhoDestino).mkdirs()) {
            String nomeArquivoDestino = String.format(DAT_FILE, caminhoDestino, uuid);
            String nomeArquivoMeta = String.format(XML_FILE, caminhoDestino, uuid);

            try {
                fileInfo.setDestinationPath(caminhoDestino);

                new XStream().toXML(fileInfo, new FileWriter(nomeArquivoMeta));
                FileOutputStream destino = new FileOutputStream(nomeArquivoDestino);
                byte[] buffer = new byte[KILOBYTE];

                while (file.read(buffer) > 0) {
                    destino.write(buffer);
                }

                destino.close();
            } catch (IOException ex) {
                Logger.getLogger(RFSFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            uuid = null;
        }

        return uuid;
    }
    private static final int KILOBYTE = 10240;
}
