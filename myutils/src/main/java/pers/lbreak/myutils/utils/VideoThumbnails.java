package pers.lbreak.myutils.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import java.util.HashMap;
import java.util.Random;

/**
 * 获取视频缩略图 建造者模式
 */
public class VideoThumbnails {

    private static Boolean resize = false;
    private static int w = 300;
    private static int h = 300;
    private static final Random random = new Random();

    /**
     * 获取本地视频缩略图
     * @param context
     * @param aVideoUri 本地视频地址
     * @return
     */
    public Bitmap getThumbnail(Context context, Uri aVideoUri) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(context, aVideoUri);
        String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

        int ran = random.nextInt(Integer.valueOf(duration) + 1);//随机截取0到duration
        Bitmap bitmap = retriever.getFrameAtTime(ran, MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
        if (resize) {
            if (bitmap != null) {
                bitmap = ThumbnailUtils.extractThumbnail(bitmap, w, h, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
            }
        }
        return bitmap;
    }

    /**
     * 获取本地视频缩略图
     * @param url 视频地址
     * @return
     */
    public Bitmap getThumbnail(String url) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();

        try {
            if (Build.VERSION.SDK_INT >= 14) {
                retriever.setDataSource(url, new HashMap<String, String>());
            } else {
                retriever.setDataSource(url);
            }

            String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);


//            int seconds = Integer.valueOf(time) / 1000;// 转换单位为秒)
//            Log.d("", "秒" + seconds);
            int ran = random.nextInt(Integer.valueOf(duration) + 1);//随机截取0到duration
            bitmap = retriever.getFrameAtTime(ran, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);

            Log.d("tag", "时间:" + ran);
            retriever.release();

            //图片缩放
            if (resize) {
                if (bitmap != null) {
                    bitmap = ThumbnailUtils.extractThumbnail(bitmap, w, h, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
                }
            }


        } catch (Exception e) { // Assume this is a corrupt video file
            e.printStackTrace();
        }
        return bitmap;
    }

    public VideoThumbnails(VideoThumbnailUtilsBuilder builder) {
        this.resize = builder.resize;
        this.w = builder.w;
        this.h = builder.h;
    }

    public static class VideoThumbnailUtilsBuilder {
        private Boolean resize = false;
        private int w;
        private int h;

        public VideoThumbnailUtilsBuilder setResize(Boolean resize) {
            this.resize = resize;
            return this;
        }

        /**
         * 设置缩放
         *
         * @param w 缩放后的宽度
         * @param h 缩放后的高度
         * @return
         */
        public VideoThumbnailUtilsBuilder setWidthAndHeight(int w, int h) {
            this.w = w;
            this.h = h;
            return this;
        }


        public VideoThumbnails build() {
            return new VideoThumbnails(this);
        }
    }

}


