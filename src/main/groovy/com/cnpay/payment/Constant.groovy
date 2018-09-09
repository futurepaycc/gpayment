package com.cnpay.payment

//支付方式(网关、二维码、p2p)
enum PayMode{
    B2C_PC,
    B2C_H5,
    QRCODE_WEIXIN,
    QRCODE_ALIAPY,
    QRCODE_QQ,
    P2P_WEIXIN,
    P2P_ALIPAY
}

//订单状态
enum OrderStatus{
    CREATED, //已创建
    SENDED,  //已发送
    SUCCESS, //已成功
    FAIL     //已失败
}

//结算类型(未来需要屏蔽上游结算和商户结算周期)
enum SettMode{
    D0,
    D1,
    T1
}