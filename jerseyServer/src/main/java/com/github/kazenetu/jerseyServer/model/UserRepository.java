package com.github.kazenetu.jerseyServer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.github.kazenetu.jerseyServer.entity.UserData;

@Named
@RequestScoped
public class UserRepository extends Repository {

    public UserRepository(){
        System.out.println("UserRepository UserRepository:"+hashCode());
    }

    /**
     * ログインチェック
     * @param userID ユーザーID
     * @param password パスワード
     * @return ユーザー情報(検索できない場合はnull)
     */
    public Optional<UserData> login(String userID, String password) {
        String sql = "select NAME,AGE from MT_USER where USER_ID=?;";

        ArrayList<Object> params = new ArrayList<>();
        params.add(userID);

        UserData userData = null;
        try {
            List<Map<String,Object>> result = db.query(sql, params);
            if (!result.isEmpty()) {
                Map<String,Object> row = result.get(0);

                userData = new UserData();
                userData.setName(userID);
                userData.setName((String)row.get("NAME"));
                userData.setAge((int)row.get("AGE"));
            }
        } catch (Exception e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        return Optional.ofNullable(userData);
    }

    /**
     * パスワード変更
     * @param userID ユーザーID
     * @param password パスワード
     * @param newPassword 新パスワード
     * @return 正常終了結果
     */
    public boolean passwordChange(String userID, String password, String newPassword) {
        String sql = "update MT_USER set PASSWORD=PASSWORD+1 where USER_ID=?;";

        ArrayList<Object> params = new ArrayList<>();
        params.add(userID);

        try {
            db.setTransaction();

            if (db.execute(sql, params) > 0) {
                db.commit();
                return true;
            } else {
                db.rollback();
                return false;
            }
        } catch (Exception e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        return false;
    }

    /**
     * ユーザー全レコードを取得する
     * @return ユーザー全レコードのリスト
     */
    public List<UserData> getUsers(){
        String sql = "select NAME,AGE from MT_USER;";

        List<UserData> users = new ArrayList<>();

        try {
            List<Map<String,Object>> result = db.query(sql, new ArrayList<>());
            if (!result.isEmpty()) {
                result.forEach(row->{
                    users.add(new UserData("",(String)row.get("NAME"),"",(int)row.get("AGE")));
                });
            }
        } catch (Exception e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        return users;
    }
}
