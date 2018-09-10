package com.cnpay.payment

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import com.zaxxer.hikari.HikariDataSource
import groovy.sql.Sql
import org.redisson.Redisson
import org.redisson.api.RedissonClient

import javax.sql.DataSource

@Singleton(strict = false) //false自定义构造函数才起作用
class DataSources {
    private DataSource mysql = new HikariDataSource()
    RedissonClient redis = Redisson.create()

    private com.mongodb.async.client.MongoClient mongoClientAsync = com.mongodb.async.client.MongoClients.create()
    com.mongodb.async.client.MongoDatabase mongodbAsync = mongoClientAsync.getDatabase("cnpay")

    private MongoClient mongoClient = MongoClients.create()
    MongoDatabase mongodb=mongoClient.getDatabase("cnpay")

    //单例不可以用公开构造函数
    private DataSources(){
        mysql.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/cnpay?user=root&password=root&useUnicode=yes&characterEncoding=UTF-8&useSSL=false")
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