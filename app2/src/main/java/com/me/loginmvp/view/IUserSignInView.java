package com.me.loginmvp.view;

import com.me.loginmvp.bean.User;

/**
 * Created by tao.liu on 2017/7/26.
 */

public interface IUserSignInView {

        String getUsername();

        String getPassword();

        void showLoading();

        void hideLoading();

        void toMainActivity(User user);

        void showFailedError();

}
