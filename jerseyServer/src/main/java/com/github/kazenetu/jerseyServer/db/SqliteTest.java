package com.github.kazenetu.jerseyServer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class SqliteTest implements AutoCloseable {

    private Connection con = null;

    public SqliteTest() {
        try {
            // JDBCドライバーの指定
            Class.forName("org.sqlite.JDBC");

            //クラスローダーからdbファイルの物理パスを取得する
            String filePath = SqliteTest.class.getClassLoader().getResource("test.db").getPath();

            // データベースに接続する なければ作成される
            con = DriverManager.getConnection("jdbc:sqlite:" + filePath);

        } catch (ClassNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            // Connection の例外が発生した時

            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
        }

    }

    public boolean Login(String id) {
        String sql = "select count(USER_ID) as cnt from MT_USER where USER_ID=?;";

        PreparedStatement statement;
        try {
            statement = con.prepareStatement(sql);

            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            if (result.getInt("cnt") > 0) {
                return true;
            }
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        //プロパティ取得テスト
        getPropertiesTest();

        return false;
    }

    public boolean updateTest(String id) {
        String sql = "update MT_USER set PASSWORD=PASSWORD+1 where USER_ID=?;";

        PreparedStatement statement;
        try {
            con.setAutoCommit(false);
            con.setSavepoint();

            statement = con.prepareStatement(sql);
            statement.setString(1, id);

            int result = statement.executeUpdate();
            if (result > 0) {
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        return false;
    }

    /**
     * プロパティファイル取得テスト
     */
    private void getPropertiesTest() {
        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle("connection_main");
        } catch (MissingResourceException e) {
            e.printStackTrace();
            return;
        }

        try {
            System.out.println(bundle.getString("url"));
            System.out.println(bundle.getString("driver"));
            System.out.println(bundle.getString("user"));
            System.out.println(bundle.getString("password"));
        } catch (MissingResourceException e) {
            e.printStackTrace();
            return;
        }
    }
}