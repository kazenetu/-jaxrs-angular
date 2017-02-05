package com.github.kazenetu.jerseyServer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.github.kazenetu.jerseyServer.db.SqliteTest;

@Named
@RequestScoped
public class UserRepository implements AutoCloseable {
    private SqliteTest db;

    public UserRepository(){
        System.out.println("UserRepository PostConstruct:"+hashCode());
        db = new SqliteTest();
    }

    @Override
    public void close(){
        System.out.println("UserRepository PreDestroy");
        db.close();
    }


    /**
     * ログインチェック
     * @param userID ユーザーID
     * @param password パスワード
     * @return ログイン成否
     */
    public boolean login(String userID, String password) {
        String sql = "select count(USER_ID) as cnt from MT_USER where USER_ID=?;";

        ArrayList<Object> params = new ArrayList<>();
        params.add(userID);

        try {
            List<Map<String,Object>> result = db.query(sql, params);
            if (result.isEmpty()) {
                return false;
            }
            if((int)result.get(0).get("cnt") > 0){
                return true;
            }
        } catch (Exception e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return false;
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
}
