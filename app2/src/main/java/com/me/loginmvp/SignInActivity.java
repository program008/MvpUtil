package com.me.loginmvp;

import android.content.Intent;
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
import com.me.loginmvp.presenter.UserSigninPresenter;
import com.me.loginmvp.view.IUserSignInView;

public class SignInActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener ,IUserSignInView{
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

        private ProgressBar mProgressbar;

        private UserSigninPresenter userSigninPresenter = new UserSigninPresenter(this);

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                //取消状态栏
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                setContentView(R.layout.activity_sign_in);

                mVideoView = (VideoView) findViewById(R.id.videoView);
                mBalloonRelativeLayout = (BalloonRelativeLayout) findViewById(R.id.balloonRelativeLayout);
                initVideoView();
                initView();
        }

        private void initView() {
                mUsername = (EditText) findViewById(R.id.et_username);
                mPassword = (EditText) findViewById(R.id.et_password);
                Button  mSignIn = (Button) findViewById(R.id.btn_sign_in);
                mProgressbar = (ProgressBar) findViewById(R.id.id_pb_loading);

                //登录
                mSignIn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                userSigninPresenter.signin();
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
        public String getUsername() {
                return mUsername.getText().toString();
        }

        @Override
        public String getPassword() {
                return mPassword.getText().toString();
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
                Toast.makeText(this, "sign in success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,UserLoginActivity.class));
                finish();
        }

        @Override
        public void showFailedError() {
                Toast.makeText(this, "sign in failed", Toast.LENGTH_SHORT).show();
                finish();
        }
}
