package net.ellise.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QRGeneratorImpl implements QRGenerator {
    private final String urlBase;
    private final String fileBase;

    public QRGeneratorImpl(String urlBase, String fileBase) {
        this.urlBase = urlBase;
        this.fileBase = fileBase;
    }

    private Object[] prePendUrl(Object prepend, Object params[]) {
        Object[] newParams = new Object[params.length+1];
        newParams[0] = prepend;
        for (int i = 0; i < params.length; i++) {
            newParams[i+1] = params[i];
        }
        return newParams;
    }

    @Override
    public File generateQRCode(QRAction action, Object[] params) {
        try {
            Object[] fileParams = prePendUrl(fileBase, params);
            Object[] urlParams = prePendUrl(urlBase, params);

            File file = action.getFile(fileParams);
            String message = action.getUrl(urlParams);

            //path where we want to get QR Code
            System.out.println("Encoding file: " + file.getAbsolutePath());
            String path = file.getAbsolutePath();
            //Encoding charset to be used
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            //generates QR code with Low level(L) error correction capability
            hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //invoking the user-defined method that creates the QR code
            int width = 200;
            int height = 200;

            //the BitMatrix class represents the 2D matrix of bits
            //MultiFormatWriter is a factory class that finds the appropriate Writer subclass for the BarcodeFormat requested and encodes the barcode with the supplied contents.
            BitMatrix matrix = new MultiFormatWriter().encode(new String(message.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));

            //prints if the QR code is generated
            System.out.println("QR Code created successfully.");
            return file;
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
