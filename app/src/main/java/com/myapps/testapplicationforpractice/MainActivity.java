package com.myapps.testapplicationforpractice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    private NotificationManager nm;
    private final int TAG = 1;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Second_Fragment second_fragment;
    private Third_Fragment third_fragment;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        second_fragment = new Second_Fragment();
        third_fragment = new Third_Fragment();
        button = findViewById(R.id.Delete);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ft = fm.beginTransaction();
                if(fm.findFragmentByTag(second_fragment.TAG)!=null)
                    ft.remove(second_fragment);
                if(fm.findFragmentByTag(third_fragment.TAG)!=null)
                    ft.remove(third_fragment);
                ft.commit();
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ft = fm.beginTransaction();
        switch (item.getItemId()){
            case R.id.item_settings:
                Intent intent_1_frag = new Intent(getApplicationContext(),First_Fragment.class);
                startActivity(intent_1_frag);
                break;
            case R.id.item_name:
                if(fm.findFragmentByTag(Third_Fragment.TAG)!=null)
                    ft.remove(third_fragment);
                if(fm.findFragmentByTag(Second_Fragment.TAG)==null)
                    ft.add(R.id.container,second_fragment,Second_Fragment.TAG);
                break;
            case R.id.item_calls:
                if(fm.findFragmentByTag(Second_Fragment.TAG)!=null)
                    ft.remove(second_fragment);
                if(fm.findFragmentByTag(Third_Fragment.TAG)==null)
                    ft.add(R.id.container,third_fragment,Third_Fragment.TAG);
                break;
            case R.id.item_to_main:
                nm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                Notification.Builder builder = new Notification.Builder(getApplicationContext());

                Intent intent = new Intent(MainActivity.this,First_Fragment.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_CANCEL_CURRENT);

                builder
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_launcher_background))
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setTicker("I am here!")
                        .setContentText("Tick on me!")
                        .setWhen(System.currentTimeMillis())
                        .setAutoCancel(true)
                        .setContentTitle("Main");
                nm.notify(TAG, builder.build());
                break;
        }
        ft.commit();
        return super.onOptionsItemSelected(item);
    }
}
