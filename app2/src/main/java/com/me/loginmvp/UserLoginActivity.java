package com.me.loginmvp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.me.loginmvp.balloonrelativelayout.BalloonRelativeLayout;
import com.me.loginmvp.bean.User;
import com.me.loginmvp.presenter.UserLoginPresenter;
import com.me.loginmvp.view.IUserLogView;

public class UserLoginActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener,IUserLogView {

        private BalloonRelativeLayout mBalloonRelativeLayout;
        private VideoView mVideoView;

        private int TIME = 100;//这里默认每隔100毫秒添加一个气泡
        Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {

                @Override
                public void run() {
                        // handler自带方法实现定时器
                        try {
                                mHandler.postDelayed(this, TIME);
                                mBalloonRelativeLayout.addBalloon();
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
        };

        private EditText mUsername;

        private EditText mPassword;

        private Button mLogin;

        private Button mClean;

        private ProgressBar mProgressbar;

        private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                //取消状态栏
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                setContentView(R.layout.activity_user_login);

                mVideoView = (VideoView) findViewById(R.id.videoView);
                mBalloonRelativeLayout = (BalloonRelativeLayout) findViewById(R.id.balloonRelativeLayout);
                initVideoView();
                initView();
        }

        /**
         * 初始化控件
         */
        private void initView() {
                mUsername = (EditText) findViewById(R.id.et_username);
                mPassword = (EditText) findViewById(R.id.et_password);
                mLogin = (Button) findViewById(R.id.btn_login);
                mClean = (Button) findViewById(R.id.btn_clean);
                mProgressbar = (ProgressBar) findViewById(R.id.id_pb_loading);

                //登录
                mLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                mUserLoginPresenter.login();
                        }
                });

                //清除输入框
                mClean.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                mUserLoginPresenter.clear();
                        }
                });

        }

        private void initVideoView() {
                //设置屏幕常亮
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mqr));
                //设置相关的监听
                mVideoView.setOnPreparedListener(this);
                mVideoView.setOnCompletionListener(this);
        }

        //播放准备
        @Override
        public void onPrepared(MediaPlayer mp) {
                //开始播放
                mVideoView.start();
                mHandler.postDelayed(runnable, TIME);
        }

        //播放结束
        @Override
        public void onCompletion(MediaPlayer mp) {
                //开始播放
                mVideoView.start();
        }

        @Override
        protected void onDestroy() {
                super.onDestroy();
        }

        @Override
        public String getUsername() {
                return mUsername.getText().toString();
        }

        @Override
        public String getPassword() {
                return mPassword.getText().toString();
        }

        @Override
        public void clearUsername() {
                mUsername.setText("");
        }

        @Override
        public void clearPassword() {
                mPassword.setText("");
        }

        @Override
        public void showLoading() {
                mProgressbar.setVisibility(View.VISIBLE);
        }

        @Override
        public void hideLoading() {
                mProgressbar.setVisibility(View.GONE);
        }

        @Override
        public void toMainActivity(User user) {
                Toast.makeText(this, "Welcome " + user.getUsername(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void showFailedError() {
                Toast.makeText(this, "Sorry! you are username or password error", Toast.LENGTH_SHORT).show();
        }
}
