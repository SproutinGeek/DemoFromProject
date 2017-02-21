package com.tml.demo.compat;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.TextView;

/**
 * Compatability class
 * Created by 1021354 on 03-11-2015.
 */
public class ResourceCompat {

    /**
     * Return a drawable object associated with a particular resource ID and
     * styled for the specified theme. Various types of objects will be
     * returned depending on the underlying resource -- for example, a solid
     * color, PNG image, scalable image, etc.
     *
     * @param context
     * @param drawableID
     * @return
     */
    public static Drawable getDrawable(Context context, @DrawableRes int drawableID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getDrawable(drawableID, context.getTheme());
        } else {
            return context.getResources().getDrawable(drawableID);
        }
    }

    /**
     * * Returns a themed color integer associated with a particular resource ID.
     * If the resource holds a complex {@link ColorStateList}, then the default
     * color from the set is returned.
     *
     * @param color
     * @param context
     * @return
     */
    public static int getColor(Context context, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getColor(color, context.getTheme());
        } else {
            return context.getResources().getColor(color);
        }
    }

    /**
     * Sets the text appearance from the specified style resource.
     * <p/>
     * Use a framework-defined {@code TextAppearance} style like
     * {@link android.R.style#TextAppearance_Material_Body1 @android:style/TextAppearance.Material.Body1}
     * set of attributes that can be used in a custom style.
     *
     * @param context
     * @param textView
     * @param styltes
     */
    public static void setTextApperence(Context context, TextView textView, @StyleRes int styltes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textView.setTextAppearance(styltes);
        } else {
            textView.setTextAppearance(context, styltes);
        }
    }

    public static ColorStateList getColorStateList(Context context, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getColorStateList(color, context.getTheme());
        } else {
            return context.getResources().getColorStateList(color);
        }
    }

    /**
     * Set the background to a given Drawable, or remove the background. If the
     * background has padding, this View's padding is set to the background's
     * padding. However, when a background is removed, this View's padding isn't
     * touched. If setting the padding is desired, please use
     * @param v
     * @param context
     * @param drawable in {link DrawableRes}
     */
    public static void setBackground(View v, Context context, @DrawableRes int drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(getDrawable(context, drawable));
        }
        else {
            v.setBackgroundDrawable(getDrawable(context, drawable));
        }
    }

    /**
     * Set the background to a given Drawable, or remove the background. If the
     * background has padding, this View's padding is set to the background's
     * padding. However, when a background is removed, this View's padding isn't
     * touched. If setting the padding is desired, please use
     * @param v
     * @param context
     * @param drawable in {link Drawable}
     */
    public static void setDrawableBackground(View v, Context context, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(drawable);
        }
        else {
            v.setBackgroundDrawable(drawable);
        }
    }
}
