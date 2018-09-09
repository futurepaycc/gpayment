package com.cnpay.payment

import grails.validation.Validateable
import groovy.transform.Canonical

@Canonical
class Message_Format implements Validateable{
    String payType
    String userID
    String amount
    String hash

    static constraints = {
        payType size: 5..15, blank: false
        userID size: 4..4, blank: false
        amount size: 4..7, blank:false, validator:{val,obj,errors->
            if( !(val ==~ /^\d+\.\d{2}$/) ) errors.rejectValue("amount","regexp.error")
        }
        hash size: 4..4
    }
}

@Canonical
class B2C_PC_Message_Format implements Validateable{
    String payType
    String userID
    String amount
    String hash
    String created
    static constraints = {
        importFrom Message_Format  //约束单独继承

        payType matches: "^B2C_PC\$" //这个和importFrom综合起作用
        created nullable:true
    }
}