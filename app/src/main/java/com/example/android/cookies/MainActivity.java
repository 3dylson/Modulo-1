package com.example.android.cookies;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void eatCookie(View view) {
        ImageView imageView = findViewById(R.id.android_cookie_image_view);
        imageView.setImageResource(R.drawable.after_cookie);

        TextView textView = findViewById(R.id.status_text_view);
        textView.setText(R.string.msg_i_m_so_full);
    }

}