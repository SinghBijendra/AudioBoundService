package com.bijendra.localbound.localbound;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AudioBoundActivity extends AppCompatActivity {

    ServiceConnection serviceConnection;
    AudioBoundService boundService;
     boolean isBound=false;

    TextView tvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_bound);
        tvData= (TextView) findViewById(R.id.tvData);

        serviceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                boundService=((AudioBoundService.MyLocalBinder) iBinder).getService();
                isBound=false;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

        bindService(new Intent(this,AudioBoundService.class),serviceConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    public void gotoAction(View view)
    {


        if(view.getTag().toString().equals("0"))
          tvData.setText( boundService.getCurrentTime());
        else   if(view.getTag().toString().equals("1"))
            boundService.playAudio();
            else   if(view.getTag().toString().equals("2"))
            boundService.stopAudio();

    }
}
