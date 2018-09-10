package com.cnpay.payment

import groovy.json.JsonSlurper
import spock.lang.Specification
/**
 * https://www.baeldung.com/groovy-spock
 */
class DaoSpec extends Specification {

    def setupSpec(){
        println "before first"
    }

    def cleanupSpec(){
        println "after last"
    }


    def "sql"(){
        given:
            def sql =  DataSources.instance.sql()
        when:
            sql.execute("drop table if exists person")
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
                sql.execute("drop table  if exists person2")
                sql.execute("create table person2(id int auto_increment primary key,name varchar(64),age integer)  ")
                sql.execute("insert into person2(name,age) values(?,?)",["liunix",35])
                result = sql.firstRow("select count(*) as cnt from person2")
                println result
            }
        then:
            result.cnt == 1
    }
}