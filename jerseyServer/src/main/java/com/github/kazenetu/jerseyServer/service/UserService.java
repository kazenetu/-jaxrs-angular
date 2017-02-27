package com.github.kazenetu.jerseyServer.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

import com.github.kazenetu.jerseyServer.entity.UserData;
import com.github.kazenetu.jerseyServer.model.UserRepository;

@ApplicationScoped
public class UserService {

    @PostConstruct
    public void PostConstruct(){
    }

    @PreDestroy
    public void PreDestroy(){
    }

    /**
     * ログインチェック
     * @param userID ユーザーID
     * @param password パスワード
     * @return ログイン成否
     */
    public Optional<UserData> login(String userID, String password) {
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

    /**
     * ユーザー全レコードを取得する
     * @return ユーザー全レコードのリスト
     */
    public List<UserData> getUsers(){
        try(UserRepository userRepository=new UserRepository()){
            return userRepository.getUsers();
        }
    }

}
