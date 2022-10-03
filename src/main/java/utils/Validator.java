package utils;

import java.util.regex.*;

public class Validator {
    private final static String privilegePattern = "^(-1)|(\\d{1,2})$";


    public static boolean validatePrivilege(String privilege){
        return validate(privilegePattern, privilege);
    }

    public static boolean validate(String pattern, String input){
        return Pattern.compile(pattern).matcher(input).matches();
    }
}
