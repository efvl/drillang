package app.prog.evv.drillang.utils;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtils {

    public static String calcChecksum(byte[] bytes) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);
        byte[] digest = md.digest();
        String checksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return checksum;
    }

}
