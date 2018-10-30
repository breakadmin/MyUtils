package pers.lbreak;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pers.lbreak.myutils.utils.AudioUtils;

public class AudioActivity extends AppCompatActivity {


    @BindView(R.id.start)
    Button start;
    @BindView(R.id.play)
    Button play;
    String name = "audio.mp3";
    File file;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.duration)
    TextView duration;

    @BindView(R.id.layout)
    LinearLayout layout;
    private boolean isStart = false;
    AudioUtils audioUtils = AudioUtils.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        ButterKnife.bind(this);
        file = new File(getExternalCacheDir(), name);

    }


    @OnClick({R.id.start, R.id.play, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                if (!isStart) {
                    audioUtils.startRecord(file);
                    start.setText("停止录制");
                    isStart = true;
                } else {
                    try {
                        audioUtils.stopRecord(null);
                        duration.setText("语音时长:" + audioUtils.getDuration(file.getPath()) + "秒");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    start.setText("开始录制");
                    isStart = false;
                }
                break;
            case R.id.play:

                try {
                    audioUtils.play(file);
                    Toast.makeText(this, "播放成功", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                    Toast.makeText(this, "播放失败," + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.delete:
                try {
                    audioUtils.stopRecord(file);
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                    Toast.makeText(this, "删除失败," + e.getMessage(), Toast.LENGTH_SHORT).show();

                }

                break;

        }
    }



}