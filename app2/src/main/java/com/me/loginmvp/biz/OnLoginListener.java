package com.me.loginmvp.biz;

import com.me.loginmvp.bean.User;

/**
 * Created by tao.liu on 2017/7/26.
 */

public interface OnLoginListener {

        void loginSuccess(User user);

        void loginFailed();

}
