package com.github.kazenetu.jerseyServer.model;

import com.github.kazenetu.jerseyServer.db.Database;
import com.github.kazenetu.jerseyServer.db.SqliteTest;

/**
 * リポジトリクラスのスーパークラス
 *
 */
public class Repository implements AutoCloseable {

    /**
     * DBの種類
     */
    private enum DBType {
        SQLite, PostgreSQL;
    }

    /**
     * データベースインターフェース
     */
    protected Database db;

    /**
     * コンストラクタ
     */
    public Repository() {

        // dbの種類を設定
        String dbName = System.getenv("DBNAME");

        if (dbName == null || "".equals(dbName)) {
            dbName = DBType.SQLite.toString();
        }

        // dbのインスタンスを作成
        if (DBType.SQLite.toString().equals(dbName)) {
            db = new SqliteTest();
        }
    }

    /**
     * クローズメソッド
     */
    @Override
    public void close() {
        db.close();
        db = null;
    }
}
