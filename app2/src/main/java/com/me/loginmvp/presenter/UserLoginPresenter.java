package com.me.loginmvp.presenter;

import android.os.Handler;

import com.me.loginmvp.bean.User;
import com.me.loginmvp.biz.IUserBiz;
import com.me.loginmvp.biz.OnLoginListener;
import com.me.loginmvp.biz.UserBiz;
import com.me.loginmvp.view.IUserLogView;

/**
 * Created by tao.liu on 2017/7/26.
 */

public class UserLoginPresenter {

        private IUserBiz userBiz;

        private IUserLogView userLogView;

        private Handler mHandler = new Handler();

        public UserLoginPresenter(IUserLogView userLogView) {
                this.userLogView = userLogView;
                this.userBiz = new UserBiz();
        }

        public void login() {
                userLogView.showLoading();

                userBiz.login(userLogView.getUsername(), userLogView.getPassword(), new OnLoginListener() {
                        @Override
                        public void loginSuccess(final User user) {
                                // need execute on UI thread
                                mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                                userLogView.toMainActivity(user);
                                                userLogView.hideLoading();
                                        }
                                });
                        }

                        @Override
                        public void loginFailed() {
                                // need execute on UI thread
                                mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                                userLogView.showFailedError();
                                                userLogView.hideLoading();
                                        }
                                });

                        }
                });
        }

        public void clear() {
                userLogView.clearUsername();
                userLogView.clearPassword();
        }
}
