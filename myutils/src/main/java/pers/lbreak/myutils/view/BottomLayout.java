package pers.lbreak.myutils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import pers.lbreak.myutils.R;

/**
 * Created by break on 2018/10/11/011.
 */
public class BottomLayout extends LinearLayout  {
     int normalColor = Color.BLACK;//默认字体颜色
     int selectColor = Color.RED;//选中字体颜色    private int viewNum=3;
     int selectNum = 0;//默认选中第一个
     Drawable bg;//默认背景
     Drawable selectBg;//选中背景
     String[] name;//文本数组
     int nameRes;//文本数组id
     int viewNum = 3;//默认数量
    selectListener selectListener;

    public void setSelectListener(BottomLayout.selectListener selectListener) {
        this.selectListener = selectListener;
    }

    public interface selectListener{
        void selectItem(View view,int position);
    }
    //获取默认选中项
    public int getDefaultSelect(){
        return selectNum;
    }

    public BottomLayout(Context context) {
        super(context);
    }

    public BottomLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BottomLayout);
        normalColor=ta.getColor(R.styleable.BottomLayout_normalColor,normalColor);
        selectColor=ta.getColor(R.styleable.BottomLayout_selectColor,selectColor);
        viewNum=ta.getInteger(R.styleable.BottomLayout_viewNum,viewNum);
        selectNum=ta.getInteger(R.styleable.BottomLayout_selectNum,selectNum);
        if (selectNum>viewNum){
         selectNum=0;
        }
        bg=ta.getDrawable(R.styleable.BottomLayout_bg);
        selectBg=ta.getDrawable(R.styleable.BottomLayout_selectBg);

        nameRes=ta.getResourceId(R.styleable.BottomLayout_text,R.array.basicText);
        name=getResources().getStringArray(nameRes);


        init();
        ta.recycle();
    }

    public BottomLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void init(){
        for(int i=0;i<viewNum;i++){
            TextView view=new TextView(getContext());
            if (name[i]!=null){
                view.setText(name[i]);
            }
            if (selectNum==i){
                if (selectBg!=null){
                    view.setBackgroundDrawable(selectBg);
                }
                view.setTextColor(selectColor);
            }else{
                if (bg!=null){
                    view.setBackgroundDrawable(bg);
                }
                view.setTextColor(normalColor);
            }


            LayoutParams layoutParams=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            layoutParams.weight=1;
            view.setGravity(Gravity.CENTER);

            view.setOnClickListener(new viewClickListener(i));
            addView(view,layoutParams);
        }

    }
    class viewClickListener  implements OnClickListener{
        int position;

        public viewClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (selectListener!=null){
                selectListener.selectItem(view,position);
            }

            for (int i=0;i<viewNum;i++){
                TextView v= (TextView) getChildAt(i);
                if (position==i){
                    v.setTextColor(selectColor);
                    if (selectBg!=null){
                        v.setBackgroundDrawable(selectBg);
                    }
                }else{
                    v.setTextColor(normalColor);
                    if (bg!=null){
                        v.setBackgroundDrawable(bg);
                    }
                }
            }

        }
    }


}
