import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mats on 07/12/15.
 */
public class day4 {
    public static void main(String[] args) {
        String input = "iwrupvqb";
        String hash;

        int n = 0;
        do {
            n++;
            hash = DigestUtils.md5Hex(input + n);
        } while (!hash.substring(0, 6).equals("000000"));
        System.out.println("Hash " + hash + " with n = " + n);
    }
}
