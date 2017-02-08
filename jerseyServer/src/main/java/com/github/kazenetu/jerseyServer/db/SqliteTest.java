package com.github.kazenetu.jerseyServer.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.sqlite.SQLiteDataSource;

public class SqliteTest implements AutoCloseable {

    private Connection con = null;
    private boolean isSetTransaction = false;

    public SqliteTest() {
        System.out.println("SqliteTest new:"+hashCode());

        try {
            //クラスローダーからdbファイルの物理パスを取得する
            String filePath = SqliteTest.class.getClassLoader().getResource("test.db").getPath();

            // データベースに接続する なければ作成される
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:" + filePath);
            con = ds.getConnection();

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            // Connection の例外が発生した時

            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        System.out.println("close");

        if (con != null) {
            try {
                if (isSetTransaction) {
                    con.rollback();
                }
                con.close();
            } catch (SQLException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
        }
    }

    /**
     * トランザクション設定
     * @throws Exception 例外エラー
     */
    public void setTransaction() throws Exception {
        try {
            con.setAutoCommit(false);
            con.setSavepoint();
            isSetTransaction = true;
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    /**
     * コミット
     * @throws Exception 例外エラー
     */
    public void commit() throws Exception {
        try {
            con.commit();
            isSetTransaction = false;
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    /**
     * ロールバック
     * @throws Exception 例外エラー
     */
    public void rollback() throws Exception {
        try {
            con.rollback();
            isSetTransaction = false;
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    /**
     * 更新系SQL実行
     * @param sql 実行SQL
     * @param params パラメータ
     * @return 更新件数
     * @throws Exception 実行時例外エラー
     */
    public int execute(String sql, List<Object> params) throws Exception {
        try (PreparedStatement statement = con.prepareStatement(sql);) {

            int i = 1;
            for (Object param : params) {
                statement.setObject(i, param);
                i++;
            }

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    /**
     * 選択系SQL実行
     * @param sql 実行SQL
     * @param params パラメータ
     * @return 検索結果
     * @throws Exception 実行時例外エラー
     */
    public List<Map<String,Object>> query(String sql, List<Object> params) throws Exception {
        List<Map<String,Object>> resultList = new ArrayList<>();

        try (PreparedStatement statement = con.prepareStatement(sql);) {

            int i = 1;
            for (Object param : params) {
                statement.setObject(i, param);
                i++;
            }

            ResultSet result = statement.executeQuery();
            ResultSetMetaData metaData = result.getMetaData();
            int colCount = metaData.getColumnCount();

            while(result.next()){
                Map<String,Object> recodeMap = new HashMap<>();
                for(i=1;i<=colCount;i++){
                    recodeMap.put(metaData.getColumnName(i), result.getObject(i));
                }
                resultList.add(recodeMap);
            }

        } catch (SQLException e) {
            throw new Exception(e);
        }
        return resultList;
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