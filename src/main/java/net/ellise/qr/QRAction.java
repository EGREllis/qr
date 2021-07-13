package net.ellise.qr;

import java.io.File;

public interface QRAction {
    String getUrl(Object[] params);
    File getFile(Object[] params);
}
