package com.cnpay.payment

import groovy.json.JsonSlurper

import static spark.Spark.post

//web接口要'薄'，便于service进地单元测试
post("/B2C_PC",{req,res->
    def message =  JsonSlurper.newInstance().parseText(req.body())
    def result = PayApiService.instance.B2C_PC(message)
    return result.toString()
})

