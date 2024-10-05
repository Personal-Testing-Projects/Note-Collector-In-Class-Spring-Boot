package lk.ijse.appspringboot.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateID() {
        return "NID" + UUID.randomUUID();
    }

    public static String profilrPicToBase64(byte[] pofilePic) {
        return Base64.getEncoder().encodeToString(pofilePic);
    }

    public static String generateUserId() {
        return "UID" + UUID.randomUUID();
    }
}
