package net.ellise;

import java.io.File;

public enum Action {
    SELL("%1$s/sell/%2$d", "%1$s/sell%2$d.png"),
    REFUND("%1$s/refund/%2$d", "%1$s/refund%2$d.png");

    private final String urlFormat;
    private final String filePath;

    Action(String urlFormat, String filePath) {
        this.urlFormat = urlFormat;
        this.filePath = filePath;
    }

    public String getUrl(String base, int id) {
        return String.format(urlFormat, base, id);
    }

    public File getFile(String base, int id) {
        return new File(String.format(filePath, base, id));
    }
}
