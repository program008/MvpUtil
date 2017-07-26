package com.me.mvputil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.me.mvputil.mvp.UserLoginActivity;

public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
        }

        /**
         * 跳转到登录界面
         * @param view
         */
        public void startActivity(View view) {
                startActivity(new Intent(this, UserLoginActivity.class));

        }
}
