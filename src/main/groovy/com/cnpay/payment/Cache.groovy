package com.cnpay.payment

//模拟 全局缓存，用于业务规则判断
//常用对象： user,account

/*原型实现：
 * https://github.com/google/guava/wiki/CachesExplained
 * https://www.baeldung.com/guava-cache
 * https://blog.csdn.net/u012881904/article/details/79263787
 */

@Singleton
class Cache{

    def getUser(String id){
        //TODO: 真实实现
        return ["userID":"0001","balance":"10000"]
    }

}