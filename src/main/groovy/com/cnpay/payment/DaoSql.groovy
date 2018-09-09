package com.cnpay.payment

import groovy.sql.Sql
import org.bson.Document

@Singleton
class DaoSql {

    protected String insert_order_sql = "insert into fp_order(id,amount,userId,status,created) values(:id,:amount,:userId,:status,:created)"

    def createAccount(Map message) {

    }

    def createOrder(Map message) {//同步进mysql,异步进mongo/redis
        //TODO: 1. mysql订单入库
        message.put("status", OrderStatus.CREATED)
        message.put("created", Date.newInstance().format("yyyyMMddHHmmss"))
        DataSources.instance.withSql { Sql sql ->
            sql.withTransaction {
                sql.executeInsert(message, insert_order_sql)
            }
        }
        //TODO: 2. mongo异步入库
        def collection = DataSources.instance.mongodb.getCollection("order")
        collection.insertOne(new Document(message))
        //TODO: 3. 今日订单,redisson异步入库
        //TODO: 4. rabbitmq异步对帐消息

    }

    def createAccountLine(Map message) {

    }

}