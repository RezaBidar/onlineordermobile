package ir.rezabidar.onlineorder.datastore;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by ReZaBiDaR on 7/20/2016.
 */
public class Session {

    private static final String PREFERENCE_NAME = "session";

    public static void logIn(Activity activity , String username, String name) {
        SharedPreferences basket = activity.getSharedPreferences(PREFERENCE_NAME , activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = basket.edit();
        editor.putBoolean("loggedin" , true);
        editor.putString("username" , username);
        editor.putString("name" , name);
        editor.commit();

    }

    public static boolean isLoggedIn(Activity activity) {
        SharedPreferences basket = activity.getSharedPreferences(PREFERENCE_NAME , activity.MODE_PRIVATE);
        return basket.getBoolean("loggedin" , false);
    }

    public static void logOut(Activity activity) {
        SharedPreferences basket = activity.getSharedPreferences(PREFERENCE_NAME , activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = basket.edit();
        editor.clear();
        editor.commit();
    }
}
