
import java.security.MessageDigest;

public class PasswordUtil {

    public static boolean isStrong(String pw) {
        if (pw == null || pw.length() < 8) return false;
        boolean hasLetter=false, hasDigit=false;
        for(char c: pw.toCharArray()){
            if(Character.isLetter(c)) hasLetter=true;
            if(Character.isDigit(c)) hasDigit=true;
        }
        return hasLetter && hasDigit;
    }

    public static String hash(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] bytes = md.digest(password.getBytes("UTF-8"));

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
