package com.example.zomato.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import com.example.zomato.db.Restaurant;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Rajat Sangrame on 23/4/20.
 * http://github.com/rajatsangrame
 */
public class Helper {

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

    public static List<Restaurant> handleSorting(SortRestaurant sort, List<Restaurant> list) {

        if (sort == null) {
            return list;
        }

        //region Price
        if (sort.sortPriceAsc) {

            Collections.sort(list, new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant o1, Restaurant o2) {

                    try {
                        double ob1 = o1.getAverageCostForTwo();
                        double ob2 = o2.getAverageCostForTwo();

                        return Double.compare(ob1, ob2);
                    } catch (NumberFormatException e) {
                        return 0;
                    }

                }
            });

        } else if ((sort.sortPriceDesc)) {

            Collections.sort(list, new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant o1, Restaurant o2) {

                    try {
                        double ob1 = o1.getAverageCostForTwo();
                        double ob2 = o2.getAverageCostForTwo();

                        return Double.compare(ob2, ob1);
                    } catch (NumberFormatException e) {
                        return 0;
                    }

                }
            });
        }
        //endregion

        //region Rating
        else if (sort.sortRatingAsc) {

            Collections.sort(list, new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant o1, Restaurant o2) {

                    try {
                        double ob1 = Double.parseDouble(o1.getUserRating());
                        double ob2 = Double.parseDouble(o2.getUserRating());

                        return Double.compare(ob1, ob2);
                    } catch (NumberFormatException e) {
                        return 0;
                    }

                }
            });

        } else if ((sort.sortRatingDesc)) {

            Collections.sort(list, new Comparator<Restaurant>() {
                @Override
                public int compare(Restaurant o1, Restaurant o2) {

                    try {
                        double ob1 = Double.parseDouble(o1.getUserRating());
                        double ob2 = Double.parseDouble(o2.getUserRating());

                        return Double.compare(ob2, ob1);
                    } catch (NumberFormatException e) {
                        return 0;
                    }

                }
            });
        }
        //endregion

        return list;
    }

}
