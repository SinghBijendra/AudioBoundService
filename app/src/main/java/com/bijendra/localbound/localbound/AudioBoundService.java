package com.bijendra.localbound.localbound;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Bijendra on 15-Nov-16.
 */

public class AudioBoundService extends Service {

    private final IBinder myLocalBinder=new MyLocalBinder();
    MediaPlayer mPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myLocalBinder;
    }

    public String getCurrentTime() {
        SimpleDateFormat dateformat =
                new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        return (dateformat.format(new Date()));
    }
    public class MyLocalBinder extends Binder
    {
      public AudioBoundService getService()
      {
          return AudioBoundService.this;
      }
    }
    public void playAudio()
    {
        mPlayer.start();
    }
    public void stopAudio()
    {
        mPlayer.pause();
        mPlayer.seekTo(0);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer=MediaPlayer.create(this,R.raw.aufile1);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPlayer!=null)
        mPlayer.release();
        mPlayer=null;
    }
}
