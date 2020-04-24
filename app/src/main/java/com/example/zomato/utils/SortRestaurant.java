package com.example.zomato.utils;

/**
 * Created by Rajat Sangrame on 25/4/20.
 * http://github.com/rajatsangrame
 */
public class SortRestaurant {

    public boolean sortRatingDesc;
    public boolean sortRatingAsc;
    public boolean sortPriceDesc;
    public boolean sortPriceAsc;

    public SortRestaurant(boolean sortRatingDesc, boolean sortRatingAsc, boolean sortPriceDesc, boolean sortPriceAsc) {
        this.sortRatingDesc = sortRatingDesc;
        this.sortRatingAsc = sortRatingAsc;
        this.sortPriceDesc = sortPriceDesc;
        this.sortPriceAsc = sortPriceAsc;
    }

    private SortRestaurant() {
    }
}
