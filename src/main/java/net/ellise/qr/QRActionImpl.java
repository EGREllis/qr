package net.ellise.qr;

import java.io.File;

public class QRActionImpl implements QRAction {
    private final String urlFormat;
    private final String fileFormat;

    public QRActionImpl() {
        urlFormat = "%1$s/offer/?id=%2$d";
        fileFormat = "%1$s/offer%2$d.png";
    }

    public QRActionImpl(String urlFormat, String fileFormat) {
        this.urlFormat = urlFormat;
        this.fileFormat = fileFormat;
    }

    @Override
    public String getUrl(Object[] params) {
        return String.format(urlFormat, params);
    }

    @Override
    public File getFile(Object[] params) {
        return new File(String.format(fileFormat, params));
    }
}
