package net.ellise;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QRConverterImpl implements QRConverter {
    @Override
    public String convertFromFileToString(File from) {
        try {
            BufferedImage bufferedImage = ImageIO.read(from);
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            System.out.println("There is no QR code in the image");
            return null;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    public File convertFromStringToFile(File file, String message) {
        try {
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
            return new File(path);
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
