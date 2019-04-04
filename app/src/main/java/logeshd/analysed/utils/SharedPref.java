package logeshd.analysed.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    public static SharedPreferences getSharePref(Context context){
        return context.getSharedPreferences("status",Context.MODE_PRIVATE);
    }

    public static String getString(Context context, String key){
        return getSharePref(context).getString(key,null);
    }

    public static int getInt(Context context, String key){
        return getSharePref(context).getInt(key,0);
    }

    public static Boolean getBoolean(Context context, String key){
        return getSharePref(context).getBoolean(key,false);
    }

    public static void putString(Context context, String key,String value){
        getSharePref(context).edit().putString(key,value).apply();
    }

    public static void putInt(Context context, String key,int value){
        getSharePref(context).edit().putInt(key,value).apply();
    }

    public static void putBoolean(Context context, String key,Boolean value){
        getSharePref(context).edit().putBoolean(key,value).apply();
    }
}