package net.ellise.qr;

import org.junit.Test;

import java.io.File;

public class QRActionTest {
    @Test
    public void when_given_then() {
        String fileBase = ".";
        String urlBase = "http://localhost:8080";

        QRGenerator qrGenerator = new QRGeneratorImpl(urlBase, fileBase);
        File outputFile = qrGenerator.generateQRCode(new QRActionImpl(), new Object[] {1,2});
        System.err.println(outputFile.getAbsolutePath());
        assert 1 == 0;
    }
}
