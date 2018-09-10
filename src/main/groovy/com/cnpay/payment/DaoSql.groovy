package com.cnpay.payment

import groovy.sql.Sql

@Singleton
class DaoSql {

    protected String insert_order_sql = "insert into fp_order(id,amount,userId,status,created) values(:id,:amount,:userId,:status,:created)"

    def createOrder(Map message) {//同步进mysql,异步进mongo/redis
        //TODO: 1. mysql订单入库
        message.put("id",UUID.randomUUID().toString())
        message.put("status", OrderStatus.CREATED.name())
        message.put("created", Date.newInstance().format("yyyyMMddHHmmss"))
        DataSources.instance.withSql { Sql sql ->
            sql.withTransaction {
                sql.executeInsert(message, insert_order_sql)
            }
        }
        //TODO: 2. mongo异步入库
        DaoNosql.instance.createOrder(message)
        //TODO: 3. 今日订单,redisson异步入库
        //TODO: 4. rabbitmq异步对帐消息

    }

    def createAccount(Map message) {

    }

    def createAccountLine(Map message) {

    }

}