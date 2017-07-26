package com.me.loginmvp.biz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;

import com.me.loginmvp.bean.User;

/**
 * Created by tao.liu on 2017/7/26.
 */

public class UserBiz implements IUserBiz {

        /**
         * 登录
         *
         * @param username
         * @param password
         * @param onLoginListener
         */
        @Override
        public void login(final String username, final String password, final OnLoginListener onLoginListener) {
                new CountDownTimer(3 * 1000, 1000) {
                        @Override
                        public void onTick(long l) {
                                Log.d("UserBiz", "倒计时 " + Math.round(l / 1000.0));
                        }

                        @Override
                        public void onFinish() {
                                if ("program008".equals(username) && "123456".equals(password)) {
                                        User user = new User();
                                        user.setUsername(username);
                                        user.setPassword(password);
                                        onLoginListener.loginSuccess(user);
                                }
                                else {
                                        onLoginListener.loginFailed();
                                }
                        }
                }.start();
        }

        /**
         * 注册
         *
         * @param username
         * @param password
         * @param onSigninListener
         */
        @Override
        public void signin(final String username, final String password, final OnSigninListener onSigninListener) {
                new CountDownTimer(4 * 1000, 1000) {
                        @Override
                        public void onTick(long l) {
                                Log.d("UserBiz", "倒计时 " + Math.round(l / 1000.0));
                        }

                        @Override
                        public void onFinish() {
                                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                                        // TODO: 2017/7/26 注册
                                        User user = new User();
                                        user.setUsername(username);
                                        user.setPassword(password);
                                        onSigninListener.signinSuccess(user);
                                }
                                else {
                                    onSigninListener.signinFailed();
                                }
                        }
                }.start();
        }


}
