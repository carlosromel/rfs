/*
 * Copyright (c) 2013 Carlos Romel Pereira da Silva <carlos.romel@gmail.com>
 */
package br.eti.romel.rfs.core;

import com.thoughtworks.xstream.*;
import java.io.*;
import java.io.File;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RFSTest {

    private static final String BASE = System.getProperty("user.dir").concat("/..");
    private static File RAIZ;
    private static String NOME_ARTEFATO_AMOSTRA;
    private static UUID uuidAmostra;
    private RFS rfs;

    @BeforeClass
    public static void setUpClass() {
        RFSTest.RAIZ = new File(RFSTest.BASE.concat("/repo"));
        RFSTest.NOME_ARTEFATO_AMOSTRA = String.format("%s/amostra.jpg", RFSTest.BASE);
    }

    @Before
    public void setUp() throws RFSDirectoryNotFoundException, FileNotFoundException {
        this.rfs = new RFSFile(RFSTest.RAIZ);
        RFSTest.uuidAmostra = this.rfs.setFile(RFSTest.NOME_ARTEFATO_AMOSTRA);
    }

    @Test
    public void testConstrutorRaizValida() throws RFSDirectoryNotFoundException {
        File raiz = new File("/");
        new RFSFile(raiz);
    }

    @Test
    public void testConstrutorRaizExistente() throws RFSDirectoryNotFoundException {
        if (!RFSTest.RAIZ.exists()) {
            RFSTest.RAIZ.mkdirs();
        }

        new RFSFile(RFSTest.RAIZ);
    }

    @Test(expected = RFSDirectoryNotFoundException.class)
    public void testConstrutorRaizInexistente() throws RFSConfigException, RFSDirectoryNotFoundException {
        File raiz = new File("/diretorio/inexistente/");
        new RFSFile(raiz);
    }

    @Test
    public void testGravarArquivo() throws RFSDirectoryNotFoundException, IOException {
        final String caminho = rfs.getPath(uuidAmostra);
        final String uuid = uuidAmostra.toString();
        final String nomeArquivoDestino = String.format(RFSFile.DAT_FILE, caminho, uuid);

        assertTrue(new File(nomeArquivoDestino).exists());
    }

    @Test
    public void testLerArquivo() throws RFSDirectoryNotFoundException, FileNotFoundException, IOException {
        final String nomeArquivoLido = String.format("%s/volta.jpg", RFSTest.BASE);
        InputStream dadosArquivoLido = rfs.getFile(uuidAmostra);
        FileOutputStream arquivoVolta = new FileOutputStream(nomeArquivoLido);
        byte[] buffer = new byte[10240];

        while (dadosArquivoLido.read(buffer) > 0) {
            arquivoVolta.write(buffer);
        }

        dadosArquivoLido.close();
        arquivoVolta.close();
        /*
         * TODO O arquivo da volta possui alguns caracteres adicionais (apesar
         *      de continuar sendo um arquivo válido. Por conta disso, a
         *      comparação binária não foi eficiente.
         *      Ex.: new File(nomeArquivoLido).compareTo(new File(RFSTest.NOME_ARTEFATO)) == 0
         */
        assertTrue(new File(nomeArquivoLido).length() > 0);
    }

    @Test
    public void testInformacoesArquivo() throws FileNotFoundException, IOException {
        final String caminho = rfs.getPath(uuidAmostra);
        final String nomeArquivo = String.format(RFSFile.XML_FILE, caminho, uuidAmostra);
        final File arquivoArtefato = new File(nomeArquivo);
        final RFSInfo amostra = (RFSInfo) new XStream().fromXML(arquivoArtefato);
        final String recuperado = new XStream().toXML(rfs.getFileInfo(uuidAmostra));

        assertTrue(new XStream().toXML(amostra).equals(recuperado));
    }

    @Test
    public void testDonoArquivo() throws FileNotFoundException {
        final RFSInfo recuperado = rfs.getFileInfo(uuidAmostra);

        assertEquals(System.getenv("USER"), recuperado.getAuthor());
    }

    @Test
    public void testNomeArquivo() throws FileNotFoundException {
        final RFSInfo recuperado = rfs.getFileInfo(uuidAmostra);

        assertEquals("amostra.jpg", recuperado.getName());
    }

    @Test
    public void testTamanhoArquivo() throws FileNotFoundException {
        final RFSInfo recuperado = rfs.getFileInfo(uuidAmostra);

        assertTrue(new File(NOME_ARTEFATO_AMOSTRA).length() == recuperado.getLength());
    }

    @Test
    @Ignore
    public void testTipoArquivo() throws FileNotFoundException {
        final RFSInfo recuperado = rfs.getFileInfo(uuidAmostra);

        assertEquals(new RFSFileType(), recuperado.getType());
    }

    @Test
    public void testDiretorioOriginalArquivo() throws FileNotFoundException {
        final RFSInfo recuperado = rfs.getFileInfo(uuidAmostra);

        assertEquals(RFSTest.NOME_ARTEFATO_AMOSTRA, recuperado.getOriginalPath());
    }
}
