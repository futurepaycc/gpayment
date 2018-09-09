package com.cnpay.payment

@Singleton
class PayApiService {
    def B2C_PC(Map message){
        def b2c_pc_message = message as B2C_PC_Message_Format
        //1. 格式化验证(判错返回代码需要抽取共通)
        if (!b2c_pc_message.validate()) return b2c_pc_message.errors
        //2. 业务规则验证（TODO：IP白名单,入款不需要）
        def b2c_pc_message_business = message.subMap(["userID","amount"]) as B2C_PC_Message_Business
        if (!b2c_pc_message_business.validate()) return b2c_pc_message_business.errors
        //3. 业务处理
        //3.1 订单入库(为了极限tps，这里甚至可以异步)
        DaoSql.instance.createOrder(message)
        //3.2 获取chanel/group(每个channel有个groupname,默认是自己,还有grouporder,limitAmountDay)
        def channelInfo = ChannelService.instance.getChannel(message)
        //3.3 制作channel表单(每个b2c channel的form表单模板存在库里)
        def instance = getClass().classLoader.loadClass( 'com.liunix.payment.template.B2C_PC.Guofubao').newInstance(message:message)
        return instance.buildForm()
    }
}

