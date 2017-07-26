package com.me.loginmvp.view;

import com.me.loginmvp.bean.User;

/**
 * Created by tao.liu on 2017/7/26.
 */

public interface IUserLogView {

        String getUsername();

        String getPassword();

        void clearUsername();

        void clearPassword();

        void showLoading();

        void hideLoading();

        void toMainActivity(User user);

        void showFailedError();

}
