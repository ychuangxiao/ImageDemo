package com.sb.common.utils;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by administrator on 2017/8/26.
 */

public class ArrayUtils {

    public ArrayUtils() {
    }

    public static <V> boolean isNotEmpty(Collection<V> collection) {
        return !isEmpty(collection);
    }

    public static <V> boolean isEmpty(Collection<V> collection) {
        return null == collection || collection.size() < 1;
    }

    public static <V> boolean isNotEmpty(V[] sourceArray) {
        return !isEmpty(sourceArray);
    }

    public static <V> boolean isEmpty(V[] sourceArray) {
        return sourceArray == null || sourceArray.length == 0;
    }

    public static <V> V getLast(V[] sourceArray, V value, V defaultValue, boolean isCircle) {
        if(isEmpty(sourceArray)) {
            return defaultValue;
        } else {
            int currentPosition = -1;

            for(int i = 0; i < sourceArray.length; ++i) {
                if(ObjectUtils.isEquals(value, sourceArray[i])) {
                    currentPosition = i;
                    break;
                }
            }

            return currentPosition == -1?defaultValue:(currentPosition == 0?(isCircle?sourceArray[sourceArray.length - 1]:defaultValue):sourceArray[currentPosition - 1]);
        }
    }

    public static <V> V getNext(V[] sourceArray, V value, V defaultValue, boolean isCircle) {
        if(isEmpty(sourceArray)) {
            return defaultValue;
        } else {
            int currentPosition = -1;

            for(int i = 0; i < sourceArray.length; ++i) {
                if(ObjectUtils.isEquals(value, sourceArray[i])) {
                    currentPosition = i;
                    break;
                }
            }

            return currentPosition == -1?defaultValue:(currentPosition == sourceArray.length - 1?(isCircle?sourceArray[0]:defaultValue):sourceArray[currentPosition + 1]);
        }
    }



    public static long getLast(long[] sourceArray, long value, long defaultValue, boolean isCircle) {
        if(sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0.");
        } else {
            Long[] array = ObjectUtils.transformLongArray(sourceArray);
            return ((Long)getLast(array, Long.valueOf(value), Long.valueOf(defaultValue), isCircle)).longValue();
        }
    }

    public static long getNext(long[] sourceArray, long value, long defaultValue, boolean isCircle) {
        if(sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0.");
        } else {
            Long[] array = ObjectUtils.transformLongArray(sourceArray);
            return ((Long)getNext(array, Long.valueOf(value), Long.valueOf(defaultValue), isCircle)).longValue();
        }
    }

    public static int getLast(int[] sourceArray, int value, int defaultValue, boolean isCircle) {
        if(sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0.");
        } else {
            Integer[] array = ObjectUtils.transformIntArray(sourceArray);
            return ((Integer)getLast(array, Integer.valueOf(value), Integer.valueOf(defaultValue), isCircle)).intValue();
        }
    }

    public static int getNext(int[] sourceArray, int value, int defaultValue, boolean isCircle) {
        if(sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0.");
        } else {
            Integer[] array = ObjectUtils.transformIntArray(sourceArray);
            return ((Integer)getNext(array, Integer.valueOf(value), Integer.valueOf(defaultValue), isCircle)).intValue();
        }
    }
}
