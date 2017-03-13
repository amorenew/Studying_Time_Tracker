package com.enew.timetracker.modules.commons;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.TextView;

import com.enew.timetracker.R;

import java.util.Locale;

/**
 * Created by TCIG_PC_54 on 3/7/2017.
 */

public class Util {
    public static void setVectorDrawableLeft(Context context, TextView textView, @DrawableRes int idVectorDrawable) {
        Drawable drawable = AppCompatDrawableManager.get().getDrawable(context, idVectorDrawable);
        textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }

    public static Drawable getVectorDrawable(Context context, @DrawableRes int idVectorDrawable) {
        return AppCompatDrawableManager.get().getDrawable(context, idVectorDrawable);
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = getVectorDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static Drawable getDrawableLocale(Activity activity, @DrawableRes int drawableResId) {
//        if (!Util.isRTL() || !ResourcesCompat.getDrawable(activity.getResources(), drawableResId, null).isAutoMirrored())
        if (!Util.isRTL())
            return ResourcesCompat.getDrawable(activity.getResources(), R.drawable.ic_back_white, null);
        /**
         * Flip it for RTl because Kitkat doesn't flip
         */
        Bitmap bitmap = Util.getBitmapFromVectorDrawable(activity, drawableResId);
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return new BitmapDrawable(activity.getResources(), bitmap);
    }

    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }

    public static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }

}
