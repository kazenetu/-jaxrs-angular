package com.github.kazenetu.jerseyServer.service;

import javax.enterprise.context.ApplicationScoped;

import com.github.kazenetu.jerseyServer.db.SqliteTest;

@ApplicationScoped
public class UserService {

	/**
	 * ログインチェック
	 * @param userID ユーザーID
	 * @param password パスワード
	 * @param filePath SQLiteのファイルパス
	 * @return ログイン成否
	 */
	public boolean login(String userID,String password,String filePath){

		try(SqliteTest test = new SqliteTest(filePath);){
    		return test.updateTest(userID);
		}

	}
}
