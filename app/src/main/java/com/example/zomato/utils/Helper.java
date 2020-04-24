package com.example.zomato.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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
                builder.append(" ...");
            } else {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    public static void hideKeyboard(Activity context) {
        // Check if no view has focus:
        InputMethodManager inputManager = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(0, 0);
    }

    //region Unused
    /*
    public static String getHighlights(List<String> list) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {

            builder.append(list.get(i));

            if (i != list.size() - 1){
                builder.append(", ");
            }
        }

        return builder.toString();
    }
     */
    //endregion
}
