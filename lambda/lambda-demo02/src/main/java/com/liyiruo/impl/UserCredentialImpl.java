package com.liyiruo.impl;

import com.liyiruo.IUserCredential;

/**
 * @author liyiruo
 * @data 2020/3/9  2:57 上午
 */
public class UserCredentialImpl implements IUserCredential {
    @Override
    public String verifyUser(String username) {
        if ("admin".equals(username)) {
            return "系统管理员";
        } else if ("manager".equals(username)) {
            return "用户管理员";
        }
        return "普通会员";
    }

}
