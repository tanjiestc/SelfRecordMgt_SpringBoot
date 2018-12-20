package com.tanjie.selfrecordmgt.utils;

import com.tanjie.selfrecordmgt.model.constant.VersionInfo;
import org.apache.commons.lang3.StringUtils;

public class HeaderProcessUtils {

    public static String handleAccept(String acceptStr) {
        boolean isEmpty = StringUtils.isEmpty(acceptStr);
        if (isEmpty) {
            return null;
        }
        String[] split = StringUtils.split(acceptStr, ";");
        for (String contextStr : split) {
            if (StringUtils.startsWith(contextStr, "version")) {
                return StringUtils.substringBetween(contextStr, "=", "_");
            }
        }
        return null;
    }

    public static boolean isContainVersionInfo(String info) {
        VersionInfo[] values = VersionInfo.values();
        for (VersionInfo value : values) {
            if (StringUtils.equals(value.getName(), info)) {
                return true;
            }
        }
        return false;
    }

}
