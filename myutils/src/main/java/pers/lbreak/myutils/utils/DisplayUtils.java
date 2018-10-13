package pers.lbreak.myutils.utils;

import android.content.Context;

/**
 * Created by break on 2018/9/12/012.
 */

public class DisplayUtils {
    private static class LazyHolder {
        private static final DisplayUtils INSTANCE = new DisplayUtils();
    }

    private DisplayUtils (){}
    public static final DisplayUtils getInstance() {

        return LazyHolder.INSTANCE;
    }

    public int dip2Px(Context context,float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
