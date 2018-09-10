package com.cnpay.payment

import groovy.json.JsonSlurper
import spock.lang.Specification

//http请求的集成测试: restclient
//https://github.com/jgritman/httpbuilder/wiki/RESTClient
//https://www.blazemeter.com/blog/testing-your-api-assertions-with-the-spock-framework

class PayApiServiceSpec extends Specification {

    def "hello2_test"(){
        when:
        def (a,b) = [1,2] //多变量同时赋值的语法
        then:
        println "this is hello2 test"
        assert a+b == 3
    }

    def "B2C_PC"() {
        given:
        def reqBody='{"payType":"B2C_PC","userId":"ab5cb8448d6422d52836a9a7a0a5a15e","amount":"100.00","hash":"asdf"}'
        def message = JsonSlurper.newInstance().parseText(reqBody) //message是map
        when:
        def result = PayApiService.instance."$message.payType"(message)
        then:
        println result
        message.amount == "100.00"
    }

}
