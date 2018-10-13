package pers.lbreak;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pers.lbreak.myutils.basic.BaseActivity;
import pers.lbreak.myutils.utils.DisplayUtils;
import pers.lbreak.myutils.view.BottomLayout;
import pers.lbreak.myutils.view.MyImageView;
import pers.lbreak.myutils.view.MyLabelText;


public class MainActivity extends BaseActivity {

    @BindView(R.id.dialog)
    MyLabelText dialog;
    DisplayUtils displayUtils = DisplayUtils.getInstance();
    boolean c = true;
    @BindView(R.id.img)
    MyImageView img;
    @BindView(R.id.layout)
    BottomLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStatusBarColor(R.color.colorAccent);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        layout.setSelectListener(new BottomLayout.selectListener() {
            @Override
            public void selectItem(View view, int position) {
                Toast.makeText(context, "点击了"+position, Toast.LENGTH_SHORT).show();
            }
        });






//        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

    }


    @OnClick({R.id.img, R.id.dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img:
                Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dialog:
                if (c) {

                    dialog.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.view, 0);
                    dialog.init(30, 30, true, true);
                    c = false;
                } else {
                    dialog.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon, 0);
                    dialog.init(30, 30, true, true);

                    c = true;
                }
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
