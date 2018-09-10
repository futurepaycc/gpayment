package com.cnpay.payment

@Singleton
class PayApiService {
    def B2C_PC(Map message){
        def b2c_pc_message = message as B2C_PC_Message_Format
        //1. 格式化验证(判错返回代码需要抽取共通)
        if (!b2c_pc_message.validate()) return b2c_pc_message.errors
        //2. 业务规则验证(TODO: 取出类的属性)
        // 可以做成一个trait获取properties(通过反射获取类)
//        def b2c_pc_message_business = message.subMap(["userID","amount"]) as B2C_PC_Message_Business
        def b2c_pc_message_business = message.subMap(B2C_PC_Message_Business.props()) as B2C_PC_Message_Business
        if (!b2c_pc_message_business.validate()) return b2c_pc_message_business.errors
        //3. 业务处理
        //3.1 订单入库(为了极限tps，这里甚至可以异步)
        DaoSql.instance.createOrder(message)
        //3.2 获取chanel/group(每个channel有个groupname,默认是自己,还有grouporder,limitAmountDay)
        def channelInfo = DaoNosql.instance.getChannel(message)
        //3.3 制作channel表单(每个b2c channel的form表单模板存在库里)
        def instance = getClass().classLoader.loadClass( 'com.cnpay.payment.template.B2C_PC.Mock_B2C_PC').newInstance(message)
        return instance.buildForm()
    }
}

