package net.itorbit.pesaram.core.utils;

public class CustomString {

    /**
     * As Java String doesn't have replaceLast() function and we need this function
     * for removing the file extension, this method takes care of that.
     * @param text
     * @param regex
     * @param replacement
     * @return String
     */
    public static String replaceLast(String text, String regex, String replacement){
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }
}
