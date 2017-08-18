package com.utils;

import java.util.Collection;

/**
 * Created by liran on 17/08/17.
 */
public class Utils {
    /**
     *
     * @param objectList
     * @return return objectList as Json List
     */
    public static <T> String getObjectListAsJsonList(Collection<T> objectList){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (Object object : objectList) {
            stringBuilder.append(object);
            stringBuilder.append(",");
        }
        if(!objectList.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
