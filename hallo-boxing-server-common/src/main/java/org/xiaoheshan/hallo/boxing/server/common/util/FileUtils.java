package org.xiaoheshan.hallo.boxing.server.common.util;

import java.io.File;
import java.util.UUID;

/**
 * @author : _Chf
 * @since : 05-08-2018
 */
public abstract class FileUtils {

    private static final String PROJECT_PATH;

    private static final String IMAGE_STORE_PATH;

    static {
        PROJECT_PATH = FileUtils.class.getResource("/").getPath();
        IMAGE_STORE_PATH = PROJECT_PATH + "images" + File.separator;
        File file = new File(IMAGE_STORE_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static File makeImageFile(String fileName) {
        return new File(IMAGE_STORE_PATH + System.currentTimeMillis() + fileName);
    }

}
