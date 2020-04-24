package com.example.zomato.utils;

import java.util.List;

/**
 * Created by Rajat Sangrame on 23/4/20.
 * http://github.com/rajatsangrame
 */
public class Helper {

    public static String getCostForTwo(int cost) {

        try {
            return "₹" + cost + " per two person";

        } catch (Exception ignore) {
        }
        return cost + "";
    }


    public static String getHighlights(List<String> list) {

        StringBuilder builder = new StringBuilder();
        int size;
        if (list.size() > 3) {
            size = 3;
        } else {
            size = list.size();
        }

        for (int i = 0; i < size; i++) {

            builder.append(list.get(i));
            if (i == size - 1) {
                builder.append("...");
            } else {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
