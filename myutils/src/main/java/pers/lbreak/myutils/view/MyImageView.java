package pers.lbreak.myutils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import pers.lbreak.myutils.R;

/**
 * 点击效果
 */
public class MyImageView extends android.support.v7.widget.AppCompatImageView{
    int color;
    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (this.isClickable()){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyImageView);
            color=ta.getColor(R.styleable.MyImageView_pressedColor,getResources().getColor(R.color.click_overlay_color));
            ta.recycle();
        }


    }
    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        Drawable drawable=getDrawable();
        if (drawable==null) {
            drawable=getBackground();
        }

        if (pressed) {
            drawable.setColorFilter(color,PorterDuff.Mode.MULTIPLY);;
        } else {
            drawable.clearColorFilter();
        }

    }



}