package com.github.kazenetu.jerseyServer.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

import com.github.kazenetu.jerseyServer.model.UserRepository;

@ApplicationScoped
public class UserService {

    @PostConstruct
    public void PostConstruct(){
        System.out.println("UserService PostConstruct:"+hashCode());
    }

    @PreDestroy
    public void PreDestroy(){
        System.out.println("UserService PreDestroy");
    }

    /**
     * ログインチェック
     * @param userID ユーザーID
     * @param password パスワード
     * @return ログイン成否
     */
    public boolean login(String userID, String password) {
        try(UserRepository userRepository=new UserRepository()){
            return userRepository.login(userID, password);
        }
    }

    /**
     * パスワード変更
     * @param userID ユーザーID
     * @param password パスワード
     * @param newPassword 新パスワード
     * @return 変更成否
     */
    public boolean passwordChange(String userID, String password, String newPassword) {
        try(UserRepository userRepository=new UserRepository()){
            return userRepository.passwordChange(userID, password, newPassword);
        }
    }

}
