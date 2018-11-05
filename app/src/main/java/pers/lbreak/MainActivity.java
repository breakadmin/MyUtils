package pers.lbreak;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pers.lbreak.myutils.basic.BaseActivity;
import pers.lbreak.myutils.utils.DisplayUtils;
import pers.lbreak.myutils.view.BottomLayout;
import pers.lbreak.myutils.view.LoadingDialog;
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
    @BindView(R.id.dialog_btn)
    Button dialogBtn;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStatusBarColor(R.color.colorAccent);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        layout.setSelectListener(new BottomLayout.selectListener() {
            @Override
            public void selectItem(View view, int position) {
                Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });

        Log.e("tag", "*********onCreate********");
//        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e("tag", "*********onStart********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("tag", "*********onRestart********");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("tag", "*********onStop********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("tag", "*********onDestroy********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("tag", "*********onPause********");
    }

    @Override
    protected void onResume() {
        Log.e("tag", "*********onResume********");
        super.onResume();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @OnClick(R.id.dialog_btn)
    public void onViewClicked() {
        LoadingDialog.Builder loadBuilder = new LoadingDialog.Builder(this)
                .setMessage("加载中...")
                .setCancelable(true)
                .setTextColor(Color.WHITE)
                .setTextSize(6)
                .setDialogWidth(100)
                .setCancelOutside(true);
        LoadingDialog dialog = loadBuilder.create();
        dialog.show();


    }

    @OnClick({R.id.dialog, R.id.dialog_btn, R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.dialog_btn:
                break;
            case R.id.btn:
                startActivity(new Intent(MainActivity.this,ThumbActivity.class));
                break;
        }
    }
}
