package com.cnpay.payment

import grails.validation.Validateable
import groovy.transform.Canonical
import org.codehaus.groovy.reflection.ReflectionUtils

//返回类的声明属性
trait Props{
    static List props(){
        def klass = ReflectionUtils.getCallingClass(0)
        klass.declaredFields.findAll{!it.synthetic}.collect{it.name}
    }
}

@Canonical
class B2C_PC_Message_Business implements Validateable,Props{
    String userId
    String amount
    static constraints = {
        userId blank:false,validator:{val,object,errors->
            //用户存在
            if (null == DaoNosql.instance.getUser(val)) errors.rejectValue("userId","user.not.found")
            //TODO:用户状态
        }
        amount blank:false,validator:{val,object,errors->
            //todo: 单笔限额，每日限额
        }
    }
}