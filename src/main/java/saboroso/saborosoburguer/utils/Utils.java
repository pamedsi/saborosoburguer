package saboroso.saborosoburguer.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Long> getBurgerIdAndAmount (String longInString) {
        List<Long> result = new ArrayList<>();
        for (String numberInString : longInString.split(",")) {
            try {
                result.add(Long.parseLong(numberInString));
            } catch (NumberFormatException e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }
}
