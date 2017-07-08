package com.banditcat.app.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;


public class MathUtils {
    private MathUtils() {
    }

    public static BigDecimal factor(BigDecimal totalAmount, BigDecimal qty, BigDecimal totalQty) {
        return MathUtils.divide(MathUtils.multiply(totalAmount, qty), totalQty, 8);
    }

    public static boolean equalsZero(BigDecimal d) {
        BigDecimal bd = toBigDecimal(d);
        BigDecimal zero = BigDecimal.ZERO.setScale(bd.scale());
        return zero.equals(bd);
    }

    public static BigDecimal min(BigDecimal d1, BigDecimal d2) {
        BigDecimal bd1 = toBigDecimal(d1);
        BigDecimal bd2 = toBigDecimal(d2);
        return bd1.compareTo(bd2) < 0 ? bd1 : bd2;
    }

    public static BigDecimal max(BigDecimal d1, BigDecimal d2) {
        BigDecimal bd1 = toBigDecimal(d1);
        BigDecimal bd2 = toBigDecimal(d2);
        return bd1.compareTo(bd2) < 0 ? bd2 : bd1;
    }

    public static BigDecimal add(BigDecimal d1, BigDecimal d2) {
        BigDecimal bd1 = toBigDecimal(d1);
        BigDecimal bd2 = toBigDecimal(d2);
        return bd1.add(bd2);
    }

    public static BigDecimal subtract(BigDecimal d1, BigDecimal d2) {
        BigDecimal bd1 = toBigDecimal(d1);
        BigDecimal bd2 = toBigDecimal(d2);
        return bd1.subtract(bd2);
    }

    public static BigDecimal multiply(BigDecimal d1, BigDecimal d2) {
        if (d1 == null) {
            d1 = new BigDecimal(0);
        }
        if (d2 == null) {
            d2 = new BigDecimal(0);
        }
        BigDecimal bd1 = toBigDecimal(d1);
        BigDecimal bd2 = toBigDecimal(d2);
        return bd1.multiply(bd2);
    }

    public static BigDecimal divide(BigDecimal d1, BigDecimal d2) {
        BigDecimal bd1 = toBigDecimal(d1);
        BigDecimal bd2 = toBigDecimal(d2);
        if (equalsZero(bd2)) {
            return BigDecimal.ZERO;
        }
        return bd1.divide(bd2, BigDecimal.ROUND_HALF_EVEN);
    }

    public static BigDecimal divide(BigDecimal d1, BigDecimal d2, int scale) {
        BigDecimal bd1 = toBigDecimal(d1);
        BigDecimal bd2 = toBigDecimal(d2);
        if (equalsZero(bd2)) {
            return BigDecimal.ZERO;
        }
        return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_EVEN);
    }

    public static BigDecimal divideRoundDown(BigDecimal d1, BigDecimal d2) {
        BigDecimal bd1 = toBigDecimal(d1);
        BigDecimal bd2 = toBigDecimal(d2);
        if (equalsZero(bd2)) {
            return BigDecimal.ZERO;
        }

        BigDecimal divide = bd1.divide(bd2, BigDecimal.ROUND_DOWN);
        long longValue = divide.longValue();
        return new BigDecimal(longValue);
    }

    public static BigDecimal mode(BigDecimal d1, BigDecimal d2) {
        BigDecimal bd1 = toBigDecimal(d1);
        BigDecimal bd2 = toBigDecimal(d2);
        if (equalsZero(bd2)) {
            return d1;
        }

        BigDecimal divide = bd1.divide(bd2, BigDecimal.ROUND_DOWN);
        long longValue = divide.longValue();
        return bd1.subtract(new BigDecimal(longValue).multiply(bd2));
    }

    public static int compareTo(BigDecimal d1, BigDecimal d2) {
        return toBigDecimal(d1).compareTo(toBigDecimal(d2));
    }

    public static int compareTo(Integer d1, Integer d2) {
        return toBigDecimal(d1).compareTo(toBigDecimal(d2));
    }

    public static int compareTo(Long d1, Long d2) {
        return toBigDecimal(d1).compareTo(toBigDecimal(d2));
    }

    public static int compareTo(BigDecimal d1, Integer d2) {
        return toBigDecimal(d1).compareTo(toBigDecimal(d2));
    }

    public static BigDecimal toBigDecimal(BigDecimal d) {
        return d != null ? d : BigDecimal.ZERO;
    }

    public static BigDecimal toBigDecimal(Long d) {
        return d != null ? new BigDecimal(d) : BigDecimal.ZERO;
    }

    public static BigDecimal toBigDecimal(Integer d) {
        return d != null ? new BigDecimal(d) : BigDecimal.ZERO;
    }


    public static Integer toInteger(Integer d) {
        return d != null ? d : 0;
    }

    public static Long toLong(BigDecimal d) {
        return d != null ? d.longValue() : 0;
    }


    public static String toString(BigDecimal bigDecimal) {
        DecimalFormat df = new DecimalFormat("#.########");


        return bigDecimal != null ? df.format(bigDecimal) : "0";


    }

}
