package com.cnpay.payment

import com.mongodb.async.SingleResultCallback
import org.bson.Document
import static com.mongodb.client.model.Filters.* //过滤相关的指令
//数据库操作抽象类，操作redis/msyq/mongo，未来要业务无感知

//覆辙mongo/redis的存储操作
@Singleton
class DaoNosql {
    /**
     * with 和 tap的区别: tap返回使用的实例对象,tap == with(true)
     * http://mrhaki.blogspot.com/2018/06/groovy-goodness-easy-object-creation.html
     */
    def getUser(String id){
        DataSources.instance.with{
            def collection = mongodb.getCollection("user")
            return collection.find(eq("userId",id)).first()
        }
    }

    def getChannel(Map message){

    }

    def createOrder(Map message){
        def collection = DataSources.instance.mongodbAsync.getCollection("order")
        collection.insertOne(new Document(message),{println it} as SingleResultCallback)
    }
}