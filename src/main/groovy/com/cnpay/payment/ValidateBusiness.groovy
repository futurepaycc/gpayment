package com.cnpay.payment

import grails.validation.Validateable
import groovy.transform.Canonical

@Canonical
class B2C_PC_Message_Business implements Validateable{
    String userID
    String amount
    static constraints = {
        userID blank:false,validator:{val,object,errors->
            //用户存在
            if (null == Cache.instance.getUser(val)) errors.rejectValue("userID","user.not.found")
            //TODO:用户状态
        }
        amount blank:false,validator:{val,object,errors->
            //todo: 单笔限额，每日限额
        }
    }
}