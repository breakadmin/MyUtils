package pers.lbreak;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pers.lbreak.myutils.utils.VideoThumbnails;

public class ThumbActivity extends AppCompatActivity {
    VideoThumbnails videoThumbnail=new VideoThumbnails.VideoThumbnailUtilsBuilder()
            .setResize(true).setWidthAndHeight(200,300).build();
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.img2)
    ImageView img2;
    String uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumb);
        ButterKnife.bind(this);
        uri = "android.resource://" + getPackageName() + "/" + R.raw.one;
        img.post(new Runnable() {
            @Override
            public void run() {
                img.setImageBitmap(videoThumbnail.getThumbnail("http://ver.mobi:8080/video/video.mp4"));
                img2.setImageBitmap(videoThumbnail.getThumbnail(getApplicationContext(), Uri.parse(uri)));
            }
        });

    }
}
