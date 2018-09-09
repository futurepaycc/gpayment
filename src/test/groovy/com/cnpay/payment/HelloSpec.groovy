package com.cnpay.payment

import groovy.json.JsonSlurper
import spock.lang.Specification
/**
 * https://www.baeldung.com/groovy-spock
 */
class HelloSpec extends Specification {

    //TODO: 用docker命令启动中间件容器
    //TODO: 利用junit suite组织spock spec
    def setupSpec(){
        println "before first"
    }

    def cleanupSpec(){
        println "after last"
    }

    def "B2C_PC"() {
        given:
            def reqBody='{"payType":"B2C_PC","userID":"0001","amount":"100.00","hash":"asdf"}'
            def message = JsonSlurper.newInstance().parseText(reqBody) //message是map
        when:
            def result = PayApiService.instance."$message.payType"(message)
        then:
            println result
            message.amount == "100.00"
    }

    def "sql"(){
        given:
            def sql =  DataSources.instance.sql()
        when:
            sql.execute("create table person(id int auto_increment primary key,name varchar(64),age integer)  ")
            sql.execute("insert into person(name,age) values(?,?)",["liunix",35])
            def result = sql.firstRow("select count(*) as cnt from person")
            println result
        then:
            result.cnt == 1
            sql.close()
    }

    def "withSql"(){
        given:
            def result = -1
        when:
            DataSources.instance.withSql { sql->
                sql.execute("create table person2(id int auto_increment primary key,name varchar(64),age integer)  ")
                sql.execute("insert into person2(name,age) values(?,?)",["liunix",35])
                result = sql.firstRow("select count(*) as cnt from person2")
                println result
            }
        then:
            result.cnt == 1
    }
}