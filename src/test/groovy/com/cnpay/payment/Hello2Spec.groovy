package com.cnpay.payment

import spock.lang.Specification

class Hello2Spec extends Specification {

    def "hello2_test"(){
        when:
            def a = 1
            def b = 2
        then:
            println "this is hello2 test"
            assert a+b == 3
    }

}
