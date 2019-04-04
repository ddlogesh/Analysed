package logeshd.analysed.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;

import logeshd.analysed.R;
import logeshd.analysed.common.login;

public class CommonUtils {

    //Hides Keyboard
    public static void hideKeyboard(Activity activity){
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Checks for Internet Connectivity
    public static boolean alerter(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return !(activeNetworkInfo != null && activeNetworkInfo.isConnected());
        }
        return false;
    }

    //Top SnackBar
    public static void setSnackBar(View view, String text, int drawable,String bgColor,int color){
        TSnackbar snackbar = TSnackbar.make(view.findViewById(android.R.id.content), text, TSnackbar.LENGTH_SHORT);
        snackbar.setIconLeft(drawable, 24);
        snackbar.setIconPadding(10);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor(bgColor));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }
}
