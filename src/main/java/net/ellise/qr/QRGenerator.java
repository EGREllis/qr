package net.ellise.qr;

import java.io.File;

public interface QRGenerator {
    File generateQRCode(QRAction action, Object[] params);
}
