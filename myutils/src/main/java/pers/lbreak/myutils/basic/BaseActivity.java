package pers.lbreak.myutils.basic;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;


/**
 * 继承 AppCompatActivity
 * 设置状态栏颜色
 * 字体
 */
public class BaseActivity extends AppCompatActivity {
    public Context context;

    /**
     * 设置状态栏颜色
     * @param statusBarColor
     */
    public void setStatusBarColor(int statusBarColor) {

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintResource(statusBarColor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
    }
    @Override
    protected void attachBaseContext(Context context) {
        //引用全局字体
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (context!=null){
            context=null;
        }
    }
}
