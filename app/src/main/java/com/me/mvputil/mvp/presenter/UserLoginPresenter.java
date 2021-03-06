package com.me.mvputil.mvp.presenter;

import android.os.Handler;

import com.me.mvputil.mvp.bean.User;
import com.me.mvputil.mvp.biz.IUserBiz;
import com.me.mvputil.mvp.biz.OnLoginListener;
import com.me.mvputil.mvp.biz.UserBiz;
import com.me.mvputil.mvp.view.IUserLoginView;

/**
 * Created by zhy on 15/6/19.
 */
public class UserLoginPresenter {

        private IUserBiz userBiz;

        private IUserLoginView userLoginView;

        private Handler mHandler = new Handler();

        public UserLoginPresenter(IUserLoginView userLoginView) {
                this.userLoginView = userLoginView;
                this.userBiz = new UserBiz();
        }

        public void login() {
                userLoginView.showLoading();
                userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
                        @Override
                        public void loginSuccess(final User user) {
                                //需要在UI线程执行
                                mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                                userLoginView.toMainActivity(user);
                                                userLoginView.hideLoading();
                                        }
                                });

                        }

                        @Override
                        public void loginFailed() {
                                //需要在UI线程执行
                                mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                                userLoginView.showFailedError();
                                                userLoginView.hideLoading();
                                        }
                                });

                        }
                });
        }

        public void clear() {
                userLoginView.clearUserName();
                userLoginView.clearPassword();
        }


}
