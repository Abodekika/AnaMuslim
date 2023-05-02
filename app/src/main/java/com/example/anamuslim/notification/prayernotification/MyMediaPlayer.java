package com.example.anamuslim.notification.prayernotification;

import android.media.MediaPlayer;
import android.util.Log;

public class MyMediaPlayer implements
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener
    ,MediaPlayer.OnErrorListener
{


    static MediaPlayer instanceMediaPlayer;
    private static final String TAG = "MyMediaPlayer";

    public static MediaPlayer getInstanceMediaPlayer() {
        if (instanceMediaPlayer == null)
            instanceMediaPlayer = new MediaPlayer();

        return instanceMediaPlayer;

    }


    @Override
    public void onCompletion(MediaPlayer mp) {

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                getInstanceMediaPlayer().stop();
                Log.d(TAG, "onCompletion: stop stop " );
            }
        });

    }

    @Override
    public void onPrepared(MediaPlayer mp) {


    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }
}
