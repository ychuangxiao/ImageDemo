package com.sb.common.utils;

import java.math.BigDecimal;

/**
 * Created by administrator on 2017/8/26.
 */

public class BigDecimalUtils {

    private static final String EMPTY = "";
    private static final BigDecimal DEFAULT_BIG_DECIMAL = new BigDecimal(0);

    public BigDecimalUtils() {
    }

    public static boolean isNull(BigDecimal bigDecimal) {
        return null == bigDecimal || bigDecimal.compareTo(DEFAULT_BIG_DECIMAL) == 0;
    }

    public static boolean isNotNull(BigDecimal bigDecimal) {
        return !isNull(bigDecimal);
    }

    public static String convertBigDecimalToString(BigDecimal bigDecimal, int newScale) {
        return null != bigDecimal && bigDecimal.compareTo(DEFAULT_BIG_DECIMAL) != 0?bigDecimal.setScale(newScale, 4).toString():"";
    }

    public static String convertBigDecimalToString(BigDecimal bigDecimal) {
        return convertBigDecimalToString(bigDecimal, 0);
    }

    public static String convertBigDecimalToString(BigDecimal bigDecimal, String format) {
        return convertBigDecimalToString(bigDecimal, 0, format);
    }

    public static String convertBigDecimalToString(BigDecimal bigDecimal, int newScale, String format) {
        if(null == bigDecimal) {
            return "";
        } else {
            String result = convertBigDecimalToString(bigDecimal, newScale);
            return !StringUtils.isEmpty(result) && !"".equals(result)?String.format(format, new Object[]{result}):"";
        }
    }
}
