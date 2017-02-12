package com.github.kazenetu.jerseyServer.factory;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;

import com.github.kazenetu.jerseyServer.db.Database;
import com.github.kazenetu.jerseyServer.db.SqliteTest;

public class DatabaseFactory implements Factory<Database> {
    private enum DBType{
        SQLite,
        PostgreSQL;
    }

    @Inject
    public DatabaseFactory(){
    }

    @Override
    public Database provide() {
        String dbName = System.getenv("DBNAME");

        if("".equals(dbName)){
            dbName =DBType.SQLite.toString();
        }

        Database result = null;
        if(DBType.SQLite.toString().equals(dbName)){
            result= new SqliteTest();
        }

        return result;
    }

    @Override
    public void dispose(Database instance) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
