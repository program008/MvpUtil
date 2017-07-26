package com.me.loginmvp.presenter;

import android.os.Handler;

import com.me.loginmvp.bean.User;
import com.me.loginmvp.biz.IUserBiz;
import com.me.loginmvp.biz.OnSigninListener;
import com.me.loginmvp.biz.UserBiz;
import com.me.loginmvp.view.IUserSignInView;

/**
 * Created by tao.liu on 2017/7/26.
 */

public class UserSigninPresenter {
        private IUserBiz userBiz;
        private IUserSignInView userSignInView;
        private Handler mHandler = new Handler();

        public UserSigninPresenter(IUserSignInView userSignInView) {
                this.userSignInView = userSignInView;
                this.userBiz = new UserBiz();
        }

        public void signin(){
                userSignInView.showLoading();
                userBiz.signin(userSignInView.getUsername(), userSignInView.getPassword(), new OnSigninListener() {
                        @Override
                        public void signinSuccess(final User user) {
                                mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                                userSignInView.toMainActivity(user);
                                                userSignInView.hideLoading();
                                        }
                                });
                        }

                        @Override
                        public void signinFailed() {
                                mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                                userSignInView.showFailedError();
                                                userSignInView.hideLoading();
                                        }
                                });
                        }
                });
        }
}
