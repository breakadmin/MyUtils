package pers.lbreak.myutils.utils;

import android.media.MediaPlayer;
import android.media.MediaRecorder;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;

/**
 * 音频录制与播放工具类
 */
public class AudioUtils {
    private MediaRecorder mr = null;
    private MediaPlayer mediaPlayer=null;
    private static class AudioUtilsHolder{
        private static AudioUtils instance=new AudioUtils();
    }
    private AudioUtils(){}
    public static AudioUtils getInstance(){
        return AudioUtilsHolder.instance;
    }

    /**
     * 判断文件是否存在
     * @param file
     * @throws Exception
     */
    private void fileExists(File file) throws Exception {
        if (!file.exists()){
            throw new Exception("文件不存在");
        }
    }
    /**
     * 获取录音时长
     * @param mUri 地址
     * @throws Exception
     */
    public  String getDuration(String mUri){
        String duration=null;
        android.media.MediaMetadataRetriever mmr = new android.media.MediaMetadataRetriever();

        try {
            if (mUri != null) {
                HashMap<String, String> headers=null;
                if (headers == null) {
                    headers = new HashMap<String, String>();
                    headers.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; MW-KW-001 Build/JRO03C) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/1.0.0.001 U4/0.8.0 Mobile Safari/533.1");
                }
                mmr.setDataSource(mUri, headers);
            }

            duration = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION);
        } catch (Exception ex) {
        } finally {
            mmr.release();
        }

        return Long.valueOf(duration)/1000+"";
    }

    /**
     * 播放音频
     */
    public void play(File file) throws Exception {

        fileExists(file);

        mediaPlayer = new MediaPlayer();

            try {
                mediaPlayer.setDataSource(file.getPath());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * 释放资源
     */
    public void release(){
        if (mr != null) {
            mr.stop();
            mr.release();
            mr = null;
        }
        if (mediaPlayer!=null){
            mediaPlayer=null;
        }
    }
    //开始录制
    public void startRecord(File file) {
        if (mr == null) {
            mr = new MediaRecorder();
            mr.setAudioSource(MediaRecorder.AudioSource.MIC);  //音频输入源
            mr.setOutputFormat(MediaRecorder.OutputFormat.AMR_WB);   //设置输出格式
            mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);   //设置编码格式
            mr.setOutputFile(file.getPath());

            try {
                mr.prepare();
                mr.start();  //开始录制
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 文件转字节
     *  Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     * @param filename
     * @return
     * @throws IOException
     */
    public  byte[] docToByte(String filename) throws IOException {

        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(filename, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    fc.size()).load();
            System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {

                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 根据byte数组，生成文件
     */
    public  boolean byteToDoc(byte[] bfile, File file) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;

        try {

            if(!file.exists()&&file.isDirectory()){//判断文件目录是否存在
                file.mkdirs();
            }

            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    return false;
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 停止录制，资源释放或停止录制，资源释放删除文件
     * @param file
     */
    public boolean stopRecord(File file) throws Exception {
        boolean result = false;
        if (mr != null) {
            mr.stop();
            mr.release();
            mr = null;
        }
        if (file!=null){//删除
            fileExists(file);

            file.delete();
            result=true;
        }
        return result;
    }
}
