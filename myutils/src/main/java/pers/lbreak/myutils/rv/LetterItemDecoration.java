package pers.lbreak.myutils.rv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import pers.lbreak.myutils.utils.DisplayUtils;

/**
 * Created by 惠普 on 2018-01-29.
 */

public class LetterItemDecoration extends RecyclerView.ItemDecoration {
    private int dividerHeight;//分割线高度
    private int dividerLeft;//分割线左距离
    private int dividerRight;//分割线右距离
    private Paint  dividerPaint;//分割线画笔

    private int titleHeight;//字母索引栏高度
    private Paint bgPaint;//绘制背景

    private float textHeight;//文本高度
    private float textBottom;
    private TextPaint textPaint;//文本画笔
    private int  textSize;

    private DisplayUtils utils=DisplayUtils.getInstance();
    private  boolean pxTodp=false;
    private  Context context;
    private Map<Integer,String> titles=new HashMap<>();//存放所有标题的位置

    public LetterItemDecoration(boolean pxTodp, Context context) {
        this.pxTodp = pxTodp;
        this.context = context;
    }

    /**
     *  设置标题
     */
    public LetterItemDecoration setTitles(Map<Integer, String> titles) {
        this.titles = titles;
        return this;
    }

    /**
     * 设置背景
     * @param titleHeight 高度
     * @param color 背景颜色
     * @return
     */
    public LetterItemDecoration setBg(int titleHeight, int color) {
        if (pxTodp){
            titleHeight=utils.dip2Px(context,titleHeight);
        }
        this.titleHeight = titleHeight;


        bgPaint=new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setDither(true);
        bgPaint.setColor(color);

        return this;
    }

    /**
     * 设置文字大小与颜色
     * @param textSize 大小
     * @param color 颜色
     * @return
     */
    public LetterItemDecoration setText(float textSize, int color) {
        if (pxTodp){
            textSize=utils.dip2Px(context,textSize);
        }
        this.textSize= (int) textSize;
        textPaint=new TextPaint();
        textPaint.setColor(color);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        // 计算文字高度
        textHeight = fontMetrics.bottom - fontMetrics.top;
        textBottom=fontMetrics.bottom;
        // 计算文字baseline
        return this;
    }


    /**
     *  设置分割线
     * @param dividerHeight 分割线高度
     * @param dividerLeft
     * @param dividerRight
     * @param color
     * @return
     */
    public LetterItemDecoration setDivider(int dividerHeight, int dividerLeft, int dividerRight, int color) {
        if (pxTodp){
            dividerHeight=utils.dip2Px(context,dividerHeight);
            dividerLeft=utils.dip2Px(context,dividerLeft);
            dividerRight=utils.dip2Px(context,dividerRight);
        }
        this.dividerHeight = dividerHeight;
        this.dividerLeft = dividerLeft;
        this.dividerRight = dividerRight;

        dividerPaint=new Paint();
        dividerPaint.setDither(true);
        dividerPaint.setAntiAlias(true);
        dividerPaint.setColor(color);
        return this;
    }



    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state){

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int top=0;
        int bottom=0;
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child=parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            if(!titles.containsKey(params.getViewLayoutPosition())){
                //绘制分割线
                if (dividerPaint!=null) {


                    top = child.getTop() - params.topMargin - dividerHeight;
                    bottom = top + dividerHeight;
                    dividerPaint.setStyle(Paint.Style.FILL);
                    c.drawRect(left + dividerLeft, top,
                            right - dividerRight, bottom, dividerPaint);
                }

            }else{ //绘制标题
                //绘制标题背景
                if (bgPaint!=null){

                    top=child.getTop()-params.topMargin-titleHeight;
                    bottom=top+titleHeight;
                    c.drawRect(left,top,right,bottom,bgPaint);

                }

                //绘制标题文字
                if (textPaint!=null){


                    float y=bottom - (titleHeight - textHeight) / 2 - textBottom;//计算文字baseLine
                    c.drawText(titles.get(params.getViewLayoutPosition()),textSize,y,textPaint);
                }
            }
        }

    }

    /**
     * 获取位置
     * @param position
     * @return
     */
    private String getPosition(int position) {
        while (position >= 0) {
            if (titles.containsKey(position)) {
                return titles.get(position);
            }
            position--;
        }
        return null;
    }

    /**
     * 绘制在内容的上面，覆盖内容
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int FirstLine=((LinearLayoutManager)parent.getLayoutManager()).findFirstVisibleItemPosition();
        if(FirstLine==RecyclerView.NO_POSITION){
            return;
        }
        String title=getPosition(FirstLine);
        if(TextUtils.isEmpty(title)){
            return;
        }
        boolean flag=false;
        if(getPosition(FirstLine+1)!=null
                &&!title.equals(getPosition(FirstLine+1))){
            //说明是当前组最后一个元素，但不一定碰撞了
            View child=parent.findViewHolderForAdapterPosition(FirstLine).itemView;
            if(child.getTop()+child.getMeasuredHeight()<titleHeight){

                //进一步检测碰撞
                c.save();//保存画布当前的状态
                flag=true;
                c.translate(0,child.getTop()+child.getMeasuredHeight()-titleHeight);//负的代表向上
            }
        }
        if (bgPaint!=null){
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int top=parent.getPaddingTop();
            int bottom=top+titleHeight;
            c.drawRect(left,top,right,bottom,bgPaint);
            if (textPaint!=null){
                float y=bottom - (titleHeight - textHeight) / 2 - textBottom;//计算文字baseLine
                c.drawText(title,textSize,y,textPaint);
            }
        }

        if(flag){
            c.restore(); //还原画布为初始状态
        }
    }


    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        super.getItemOffsets(outRect, view, parent, state);
        int pos=parent.getChildViewHolder(view).getAdapterPosition();
        //是否标题栏的位置
        if (titles.containsKey(pos)){//是,留出头部偏移
            outRect.set(0,titleHeight,0,0);
        }else{
            outRect.set(0,dividerHeight,0,0);
        }
    }


}