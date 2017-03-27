<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="x5-orientation" content="portrait">
    <link rel="stylesheet" href="/wxpay/css/weui.min.css">
    <title>微信公众号支付测试</title>
</head>
<body>
<div class="container" id="container">
    <a href="${payURL}" class="weui-btn weui-btn_primary" style="font-size:40px; 100px;">
    	立即支付
    </a>
    <br>
    <a href="http://wxpay.pes-soft.com/wxpay/m/weChat/refund" class="weui-btn weui-btn_primary" style="font-size:40px; 100px;">
    	申请退款
    </a>
</div>
</body>
</html>