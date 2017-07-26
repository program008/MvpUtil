package com.me.mvputil.mvp.biz;

/**
 * Created by zhy on 15/6/19.
 */
public interface IUserBiz {

        void login(String username, String password, OnLoginListener loginListener);
}
