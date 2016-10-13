package com.example.hertzfastlane;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * Created by rtrev on 10/12/2016.
 */

public class ReplaceFont {
    public static void replaceDefaultFont(Context context, String defaultFont, String customFont){
        Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), customFont);
        replaceFont(defaultFont, customFontTypeface);
    }

    private static void replaceFont(String defaultFont, Typeface customFontTypeface) {
        Field myField = null;
        try {
            myField =Typeface.class.getDeclaredField(defaultFont);
            myField.setAccessible(true);
            myField.set(null, customFontTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
