package com.me.loginmvp.biz;

import android.content.Context;

/**
 * Created by tao.liu on 2017/7/26.
 */

public interface IUserBiz {

        void login(String username,String password,OnLoginListener onLoginListener);
        void signin(String username, String password, OnSigninListener onSigninListener);

}
