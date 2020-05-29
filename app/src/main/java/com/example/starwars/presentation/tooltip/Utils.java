package com.example.starwars.presentation.tooltip;

import android.content.res.Resources;
import android.graphics.RectF;
import android.view.View;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Px;

final class Utils {

    public static RectF calculateRectOnScreen(@NonNull View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return new RectF(location[0], location[1], location[0] + view.getMeasuredWidth(), location[1] + view.getMeasuredHeight());
    }

    public static RectF calculateRectInWindow(@NonNull View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        return new RectF(location[0], location[1], location[0] + view.getMeasuredWidth(), location[1] + view.getMeasuredHeight());
    }

    public static float pxToDp(@Px float px) {
        return px / Resources.getSystem().getDisplayMetrics().density;
    }

    public static float dpToPx(@Dimension(unit = Dimension.DP) float dp) {
        return dp * Resources.getSystem().getDisplayMetrics().density;
    }
}
