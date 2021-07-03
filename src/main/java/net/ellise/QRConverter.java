package net.ellise;

import java.io.File;

public interface QRConverter {
    String convertFromFileToString(File file);
    File convertFromStringToFile(File file, String string);
}
