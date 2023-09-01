package saboroso.saborosoburguer.security;

import org.mindrot.jbcrypt.BCrypt;

public class Password {
    public static String hash (String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static Boolean checkHash (String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}