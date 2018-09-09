package com.cnpay.payment

import com.mongodb.async.client.MongoClient
import com.mongodb.async.client.MongoClients
import com.mongodb.async.client.MongoDatabase
import com.zaxxer.hikari.HikariDataSource
import groovy.sql.Sql
import org.redisson.Redisson
import org.redisson.api.RedissonClient

import javax.sql.DataSource

@Singleton(strict = false) //false自定义构造函数才起作用
class DataSources {
    private DataSource mysql = new HikariDataSource()
    private MongoClient mongoClient = MongoClients.create()
    RedissonClient redis = Redisson.create()
    MongoDatabase mongodb = mongoClient.getDatabase("testdb")

    //单例不可以用公开构造函数
    private DataSources(){
        def init_sql_path = this.class.getResource("mysql_schema.sql").getPath()
        def initStr = "INIT=create schema if not exists test\\;runscript from '$init_sql_path'"
        mysql.setJdbcUrl("jdbc:h2:mem:test;MODE=MySQL;DATABASE_TO_UPPER=FALSE;$initStr")
    }

    def sql(){
        new Sql(mysql)
    }

    def withSql(Closure c){
        Sql sql = null
        try {
            sql = this.sql() //这里为什么一定要用this????
            c.call(sql)
        } finally {
            if (sql != null) sql.close()
        }
    }

}