package com.example.zomato.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

@com.bumptech.glide.annotation.GlideModule
public final class GlideModule extends AppGlideModule {

    @NonNull
    @GlideOption
    public static RequestOptions roundedCornerImage(RequestOptions options, @NonNull Context context, int radius) {
        if (radius > 0) {
            int px = Math.round(radius * (context.getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
            return options.transforms(new CenterCrop(), new RoundedCorners(px));
        }
        return options.transforms(new CenterCrop());
    }
}
