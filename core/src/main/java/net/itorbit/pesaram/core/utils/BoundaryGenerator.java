package net.itorbit.pesaram.core.utils;

public class BoundaryGenerator {
    /**
     * Generates a boundary for multipart post request to filemanager service.<br/>
     * @return String
     */
    public static String generateDefaultBoundary() {
        return "---------------------------" + ((double) (Math.random() * Math.pow(10, 26)));
    }
}
