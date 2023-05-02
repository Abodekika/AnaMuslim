package com.example.anamuslim;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.example.anamuslim.utils.DownloadUtil;
import com.example.anamuslim.utils.Util;

import java.io.File;
import java.io.IOException;

public class MyMediaPlayer {
    private static MyMediaPlayer mediaPlayer_instance;
    MediaPlayer mediaPlayer;
    private Context context;
    public boolean isPause;

    public static MyMediaPlayer getInstance(Context context) {

        if (mediaPlayer_instance == null) {

            mediaPlayer_instance = new MyMediaPlayer();

        }

        mediaPlayer_instance.init(context);

        return mediaPlayer_instance;

    }

    private void init(Context context) {

        this.context = context;
        this.mediaPlayer = new MediaPlayer();
        this.isPause = false;

    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public void setDataSource(File fileSource) {


        try {
            mediaPlayer.setDataSource(String.valueOf(fileSource));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void start() {
        mediaPlayer.start();
    }

    public void prepareAsync() {
        mediaPlayer.prepareAsync();
    }

    public void prepare() {
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.reset();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void reset() {
        mediaPlayer.reset();
    }

    public boolean isPlay() {

        return mediaPlayer.isPlaying();
    }

    public void playAudio(int id, String filepath) {


        File file = Util.audioFileLocation(id);
        if (isPause()) {

            mediaPlayer.reset();
            mediaPlayer.stop();
        }
        if (!mediaPlayer.isPlaying()) {
            if (!file.exists()) {
                if (Util.isConnected(context))
                    DownloadUtil.downloadAudio(filepath, context, id);
                else {
                    Toast.makeText(context, "لا يوجد انترنت ", Toast.LENGTH_SHORT).show();

                }
            } else {
                setDataSource(file);
                if (!file.exists()) {

                    prepareAsync();

                } else {
                    prepare();
                    start();

                }

            }
        } else {
            stop();
        }


    }
}