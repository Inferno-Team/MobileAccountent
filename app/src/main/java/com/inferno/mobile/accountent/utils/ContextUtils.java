package com.inferno.mobile.accountent.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.ContextThemeWrapper;

import com.inferno.mobile.accountent.models.UserType;

import java.util.Locale;

public class ContextUtils {
    private static final String SHEARED_NAME = "user.save";
    private static final String TOKEN_FIELD = "token";
    private static final String LANG_FIELD = "language";
    private static final String TYPE_FIELD = "type";
    private static final String IS_EXISTS_FIELD = "is_exists";
    private static final String DARK_THEME = "dark_theme";


    private static SharedPreferences getShared(Context context) {
        return context.getSharedPreferences(SHEARED_NAME, Context.MODE_PRIVATE);
    }


    public static void saveUser(Context context, String token, String type) {
        getShared(context)
                .edit()
                .putString(TOKEN_FIELD, token)
                .putString(TYPE_FIELD, type)
                .putBoolean(IS_EXISTS_FIELD, true)
                .apply();
    }

    public static boolean isUserExists(Context context) {
        return getShared(context).getBoolean(IS_EXISTS_FIELD, false);
    }

    public static void deleteUser(Context context) {
        getShared(context)
                .edit()
                .putString(TOKEN_FIELD, "")
                .putString(TYPE_FIELD, "")
                .putBoolean(IS_EXISTS_FIELD, false)
                .apply();
    }

    public static String getToken(Context context) {
        return getShared(context).getString(TOKEN_FIELD, "");
    }

    public static UserType getUserType(Context context) {
        return UserType.valueOf(getShared(context).getString(TYPE_FIELD, UserType.Customer.name()));
    }

    public static boolean isDark(Context context) {
        return getShared(context).getBoolean(DARK_THEME, false);
    }

    public static void saveDarkTheme(Context context) {
        getShared(context)
                .edit()
                .putBoolean(DARK_THEME, true)
                .apply();
    }

    public static void saveLightTheme(Context context) {
        getShared(context)
                .edit()
                .putBoolean(DARK_THEME, false)
                .apply();
    }

    public static void saveLanguage(Context context, String language) {
        getShared(context)
                .edit()
                .putString(LANG_FIELD, language)
                .apply();
    }

    public static String getLanguage(Context context) {
        return getShared(context).getString(LANG_FIELD, "English");
    }

    public static void changeLang(Activity activity){
        String languageName = ContextUtils.getLanguage(activity);
        String _language = languageName.equals("English") ? "en" : "ar";
        Locale language = new Locale(_language);
        Locale.setDefault(language);
        Configuration config = new Configuration();
        config.setLocale(language);
        activity.applyOverrideConfiguration(config);
    }
}
