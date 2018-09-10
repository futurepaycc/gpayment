package com.cnpay.payment.template.B2C_PC

import groovy.text.SimpleTemplateEngine

//@Canonical 转换不适用于不适用于动态加载
class Mock_B2C_PC {

    Map message

    Mock_B2C_PC(Map message) {
        this.message = message
    }

    String formTemplate = """
    <form action="http://localhost:8000" name="doPay">
        <input type="hidden" name="merchantId" value="$message.userId">
        <input type="hidden" name="amount" value="$message.amount">
        <input type="hidden" name="hash" value="$message.hash">
    </form>
    <script>
        document.onload=document.forms[0].submit()
    </script>
    """

    def buildHash() {
        String str = message.userId + message.amount
        str.md5()
    }

    def buildForm() {
        message.put("hash", buildHash())
        SimpleTemplateEngine.newInstance().createTemplate(formTemplate).make(message)
    }

}