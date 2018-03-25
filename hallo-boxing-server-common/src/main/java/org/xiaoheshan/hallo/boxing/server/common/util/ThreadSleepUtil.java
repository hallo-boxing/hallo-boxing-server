package org.xiaoheshan.hallo.boxing.server.common.util;

/**
 * @author : _Chf
 * @since : 03-25-2018
 */
public abstract class ThreadSleepUtil {

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

}
