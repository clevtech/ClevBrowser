package com.jambo.jamil.cl;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class Main3Activity extends AppCompatActivity  {
private ImageView im1;
    private Button save, back_button, dlt;
    private EditText editt;
    private Switch mSwitch;
    private int lastInteractionTime;
    private Boolean isScreenOff = false;
    private TextView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        save = (Button) findViewById(R.id.savebt);
        back_button = (Button) findViewById(R.id.back_button);
        editt = (EditText) findViewById(R.id.editt);
        dlt = (Button) findViewById(R.id.dlt);
        home = (TextView)  findViewById(R.id.home);


        SharedPreferences pref = getSharedPreferences("main", MODE_PRIVATE);
        String text = pref.getString("my_string", "").toString();
        editt.setText(text);

        save.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                String text = editt.getText().toString();
                SharedPreferences pref = getSharedPreferences("main", MODE_PRIVATE);
                SharedPreferences.Editor editPref = pref.edit();
                editPref.putString("my_string", text);
                editPref.commit();

                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                intent.putExtra("main", editt.getText().toString());
                startActivity(intent);
            }
        });

        dlt.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
               editt.setText(null);
            }
        });
//        home.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yandex.ru")));
//
//            }
//        });

        mSwitch = (Switch) findViewById(R.id.switchBtn);
        // устанавливаем переключатель программно в значение ON
        //mSwitch.setChecked(true);
        // добавляем слушателя
        SharedPreferences sharedPrefs = getSharedPreferences("boly", MODE_PRIVATE);
        mSwitch.setChecked(sharedPrefs.getBoolean("boly", true));
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // в зависимости от значения isChecked выводим нужное сообщение
                if (isChecked) {
                    SharedPreferences.Editor editor = getSharedPreferences("boly", MODE_PRIVATE).edit();
                    editor.putBoolean("boly", true);
                    editor.commit();
                    System.out.println(isChecked);
                    Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                    intent.putExtra("boly", true);
//                    Bundle extras = new Bundle();
//                    extras.putBoolean("boly", true);
//                    Intent intent = new Intent(Main3Activity.this, MainActivity.class);
//                    intent.putExtras(extras);

                } else {

                    SharedPreferences.Editor editor = getSharedPreferences("boly", MODE_PRIVATE).edit();
                    editor.putBoolean("boly", false);
                    editor.commit();
                    Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                    intent.putExtra("boly", false);
//                    Bundle extras = new Bundle();
//                    extras.putBoolean("boly", false);
//                    Intent intent = new Intent(Main3Activity.this, MainActivity.class);
//                    intent.putExtras(extras);
                }

            }

        });

    }


    public void back(View v){
                switch (v.getId()) {
                    case R.id.back_button:
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
    public class MyBaseActivity extends Activity {

        public static final long DISCONNECT_TIMEOUT = 10000; // 5 min = 5 * 60 * 1000 ms


        private Handler disconnectHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                finish();
                return true;
            }
        });

        private Runnable disconnectCallback = new Runnable() {
            @Override
            public void run() {
                finish();
            }
        };

//        public void resetDisconnectTimer(){
//            disconnectHandler.removeCallbacks(disconnectCallback);
//            disconnectHandler.postDelayed(disconnectCallback, DISCONNECT_TIMEOUT);
//        }
//
//        public void stopDisconnectTimer(){
//            disconnectHandler.removeCallbacks(disconnectCallback);
//        }
//
//        @Override
//        public void onUserInteraction(){
//            resetDisconnectTimer();
//        }
//
//        @Override
//        public void onResume() {
//            super.onResume();
//            //resetDisconnectTimer();
//        }
//
//        @Override
//        public void onStop() {
//            super.onStop();
//            stopDisconnectTimer();
//        }

}}



