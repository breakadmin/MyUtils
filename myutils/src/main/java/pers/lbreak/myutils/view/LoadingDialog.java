package pers.lbreak.myutils.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import pers.lbreak.myutils.R;
import pers.lbreak.myutils.utils.DisplayUtils;

/**
 * 自定义加载中弹框
 */
public class LoadingDialog extends Dialog {


    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder{

        private Context context;
        private String message;
        private boolean isShowMessage=true;
        private boolean isCancelable=false;
        private boolean isCancelOutside=false;
        private int textColor= Color.WHITE;
        private int textSize=5;
        private int maxLines=2;
        private  int dialogWidth=200;
//        private DisplayUtils utils=DisplayUtils.getInstance();

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置提示信息
         * @param message
         * @return
         */
        public Builder setMessage(String message){
            this.message=message;
            return this;
        }
        /**
         * 设置最大行数
         * @param maxLines
         * @return
         */
        public Builder setMaxLines(int maxLines){
            this.maxLines=maxLines;
            return this;
        }
        /**
         * 设置字体颜色
         * @param color
         * @return
         */
        public Builder setTextColor(int color){
            this.textColor=color;
            return this;
        }
        /**
         * 设置对话框高度
         * @param dialogWidth
         * @return
         */
        public Builder setDialogWidth(int dialogWidth){
            this.dialogWidth=dialogWidth;
            return this;
        }
        /**
         * 设置字体大小
         * @param textSize
         * @return
         */
        public Builder setTextSize(int textSize){
            this.textSize=textSize;
            return this;
        }
        /**
         * 设置是否显示提示信息
         * @param isShowMessage
         * @return
         */
        public Builder setShowMessage(boolean isShowMessage){
            this.isShowMessage=isShowMessage;
            return this;
        }

        /**
         * 设置是否可以按返回键取消
         * @param isCancelable
         * @return
         */

        public Builder setCancelable(boolean isCancelable){
            this.isCancelable=isCancelable;
            return this;
        }

        /**
         * 设置是否可以取消
         * @param isCancelOutside
         * @return
         */
        public Builder setCancelOutside(boolean isCancelOutside){
            this.isCancelOutside=isCancelOutside;
            return this;
        }

        public LoadingDialog create(){
            LoadingDialog dialog=new LoadingDialog(context,R.style.dialogStyle);

            LayoutInflater inflater = LayoutInflater.from(context);
            View view=inflater.inflate(R.layout.dialog_loading,null);
            LinearLayout linearLayout=view.findViewById(R.id.dialog_layout);
            ViewGroup.LayoutParams lp= linearLayout.getLayoutParams();
            lp.width= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    dialogWidth, context.getResources().getDisplayMetrics());
            linearLayout.setLayoutParams(lp);

            TextView msgText= (TextView) view.findViewById(R.id.tip);
            if(isShowMessage){

                msgText.setMaxLines(maxLines);
                msgText.setTextColor(textColor);
                msgText.setText(message);
//                msgText.setTextSize(utils.dip2Px(context,textSize));
                msgText.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        textSize, context.getResources().getDisplayMetrics()));
            }else{
                msgText.setVisibility(View.GONE);
            }

            dialog.setContentView(view);
            dialog.setCancelable(isCancelable);
            dialog.setCanceledOnTouchOutside(isCancelOutside);
            return  dialog;

        }


    }
}
