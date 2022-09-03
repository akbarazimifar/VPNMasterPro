package com.developer.vpnmasster.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.developer.vpnmasster.R;
import com.developer.vpnmasster.api.WebAPI;
import com.developer.vpnmasster.speed_meter.activities.HomeActivity;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init_variables ();
            new Handler().postDelayed(() -> {
                new Thread(() -> {
                    try {
                        OkHttpClient okHttpClient = new OkHttpClient();
                        Request request = new Request.Builder().url(WebAPI.API+"includes/api.php?freeServers").build();
                        Response response = okHttpClient.newCall(request).execute();
                        WebAPI.FREE_SERVERS = Objects.requireNonNull(response.body()).string();

                        request = new Request.Builder().url(WebAPI.API+"includes/api.php?proServers").build();
                        response = okHttpClient.newCall(request).execute();
                        WebAPI.PREMIUM_SERVERS = Objects.requireNonNull(response.body()).string();

                    } catch (IOException e) {
                        Log.v("Kabila",e.toString());
                        e.printStackTrace();
                    }


                }).start();
                try {
                    Log.v("SERVER_API",WebAPI.FREE_SERVERS);
                    Thread.sleep(3000);
                    Log.v("SERVER_API","after "+WebAPI.FREE_SERVERS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            },1000);

        }

    private void init_variables () {
        SharedPreferences sharedPref = getSharedPreferences (
                "setting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit ();
        editor.putString ("UNIT", "Mbps");
        editor.apply ();

    }


}
