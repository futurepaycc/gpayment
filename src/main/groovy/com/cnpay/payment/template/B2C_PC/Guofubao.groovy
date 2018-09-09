package com.cnpay.payment.template.B2C_PC

import groovy.transform.Canonical

@Canonical
class Guofubao{

    Map messsage

    String formTemplate="""
    <form action="$action_url" name="doPay">
        <input type="hidden" name="merchantId" value="$message.userId">
        <input type="hidden" name="amount" value="$message.amount">
    </form>
    <script>
        document.onload=document.forms[0].submit()
    </script>
    """

    //TODO: hash
    def buildForm(){
        message.put("hash",buildHash())
        return groovy.text.TemplateEngine.newInstance().createTemplate(formTemplate).make(message)
    }

}