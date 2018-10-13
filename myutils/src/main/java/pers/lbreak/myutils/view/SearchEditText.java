package pers.lbreak.myutils.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;

import pers.lbreak.myutils.R;


/**
 * 自定义搜索框 加入删除图标及删除功能
 */
public class SearchEditText extends android.support.v7.widget.AppCompatEditText implements OnFocusChangeListener,TextWatcher{

    //  删除按钮
    private Drawable mClearDrawable;
    //  是否获得焦点
    private boolean mHasFoucus = false;
    public SearchEditText(Context context) {
        this(context,null);
    }


    public SearchEditText(Context context, AttributeSet attrs) {
        this(context,attrs,android.R.attr.editTextStyle);
    }


    public SearchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @SuppressWarnings("deprecation")
    private void init() {
        //  获取drawableRight,如果没有就使用默认的
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(R.drawable.icon_delete);
        }

        //  默认设置不可见
        setClearIconVisiable(false);
        //  设置焦点监听
        setOnFocusChangeListener(this);
        //  设置文字监听
        addTextChangedListener(this);
    }

    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            boolean flag = event.getX() > (getWidth() - getTotalPaddingRight()) && event.getX() < (getWidth() - getPaddingRight())
                    ? true : false;
            if (flag) {
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }


    private void setClearIconVisiable(boolean b) {
        Drawable right = b ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mHasFoucus) {
            setClearIconVisiable(getText().length() > 0);
        }
    }

    /*
     *  监听焦点，如果获得焦点并且有输入的文字显示删除按钮,否则隐藏
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        mHasFoucus = hasFocus;
        if (hasFocus) {
            setClearIconVisiable(getText().length() > 0);
        } else {
            setClearIconVisiable(false);
        }

    }


}