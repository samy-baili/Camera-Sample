package android.apps.cap6.com.fotozino.utils;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

public class ScreenUtils {

    private static Point getScreenSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public static int getScreenWidth(Activity activity) {
        return getScreenSize(activity).x;
    }
}
