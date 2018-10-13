package pers.lbreak.myutils.basic;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import pers.lbreak.myutils.R;


/**
 * Created by 惠普 on 2018-07-07.
 */

public class app extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("app","自定义");

        Toast.makeText(this, "阿萨斯", Toast.LENGTH_SHORT).show();
        //设置全局字体
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath(getResources().getString(R.string.text_font))
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                                .build());
    }

}
