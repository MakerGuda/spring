package com.spring.constant;

public class RedisConstant {

    /**
     * 项目固定前缀
     */
    private static final String PREFIX = "spring";

    /**
     * 分割符
     */
    private static final String SEPARATOR = ":";

    /**
     * 构建redis key
     */
    public static String buildKey(Object... args) {
        StringBuilder builder = new StringBuilder();
        builder.append(PREFIX);
        for (Object arg : args) {
            builder.append(SEPARATOR).append(arg);
        }
        return builder.toString();
    }

}
