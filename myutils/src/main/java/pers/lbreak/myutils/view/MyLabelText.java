package pers.lbreak.myutils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import pers.lbreak.myutils.R;
import pers.lbreak.myutils.utils.DisplayUtils;


/**
 * 设置drawable图片大小,并设置点击效果
 */
public class MyLabelText extends android.support.v7.widget.AppCompatTextView {
    Drawable[] drawable = getCompoundDrawables();
    Drawable bg=getBackground();
    int bgColor;
    boolean bgClick=false;
    int drawableColor;
    boolean drawableClick=false;
    DisplayUtils displayUtils=DisplayUtils.getInstance();
    int w=displayUtils.dip2Px(getContext(),35);//drawable图片大小


    public MyLabelText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyLabelText);
        int width= (int) ta.getDimension(R.styleable.MyLabelText_drawableWidth,w);
        int height= (int) ta.getDimension(R.styleable.MyLabelText_drawableHeight,w);
        bgClick=ta.getBoolean(R.styleable.MyLabelText_backgroundClickable,false);//是否为bg添加点击效果
        if (bgClick){//bg 点击颜色
            bgColor=ta.getColor(R.styleable.MyLabelText_backgroundColor,getResources().getColor(R.color.click_overlay_color));
        }

        drawableClick=ta.getBoolean(R.styleable.MyLabelText_drawableClickable,false);//是否为drawable添加点击效果
        if (drawableClick){//drawable 点击颜色
            drawableColor=ta.getColor(R.styleable.MyLabelText_drawableColor,getResources().getColor(R.color.colorAccent));
        }
        ta.recycle();
        init(width,height, true,false);

    }

    /**
     * 重新设置drawable 大小
     * @param width 宽度
     * @param height 高度
     * @param reGetDrawable 是否重新获取
     * @param dp 尺寸转化
     */
    public void init(int width,int height, boolean reGetDrawable,boolean dp){
        if (reGetDrawable){
            drawable = getCompoundDrawables();
        }
        if (dp){//px转px
            width=displayUtils.dip2Px(getContext(),width);
            height=displayUtils.dip2Px(getContext(),height);
        }

        for (int i = 0; i < drawable.length; i++) {
            if (drawable[i] != null) {
                drawable[i].setBounds(0,0,width,height);
                setCompoundDrawables(drawable[0],drawable[1],drawable[2],drawable[3]);
            }

        }

    }
    //设置点击效果
    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);

        if (drawableClick){//drawable 点击效果
            //当src图片为Null，获取背景图片
            for (int i = 0; i < drawable.length; i++) {
                if (drawable[i]!=null){
                    if (pressed) {
                        drawable[i].setColorFilter(drawableColor, PorterDuff.Mode.MULTIPLY);
                    }else{
                        drawable[i].clearColorFilter();
                    }
                }
                invalidate();
            }
        }
        if(bgClick&&bg!=null){
            if (pressed) {
                bg.setColorFilter(bgColor, PorterDuff.Mode.MULTIPLY);
            } else {
                bg.clearColorFilter();
            }
            invalidate();
        }

    }
}
