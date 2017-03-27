<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>确认支付</title>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
</head>
<body>
	<input type="hidden" name="appId" value="${appId}">
	<input type="hidden" name="nonceStr" value="${nonceStr}">
	<input type="hidden" name="prepayId" value="${prepayId}">
	<input type="hidden" name="paySign" value="${paySign}">
	<input type="hidden" name="timeStamp" value="${timeStamp}">
</body>
<script>
	function onBridgeReady() {
		var appId = $("input[name='appId']").val();
		var nonceStr = $("input[name='nonceStr']").val();
		var prepayId = $("input[name='prepayId']").val();
		var paySign = $("input[name='paySign']").val();
		var timeStamp = $("input[name='timeStamp']").val();
		WeixinJSBridge.invoke('getBrandWCPayRequest', {
			"appId" : appId,
			"timeStamp" : timeStamp,
			"nonceStr" : nonceStr,
			"package" : prepayId,
			"signType" : "MD5",
			"paySign" : paySign
		}, function(res) {
			console.log(res)
			if (res.err_msg == "get_brand_wcpay_request:ok") {
				alert("搞定了");
				location.href = "http://wxpay.pes-soft.com/wxpay/";
			} else {//这里支付失败和支付取消统一处理
				alert("支付取消");
				location.href = "http://wxpay.pes-soft.com/wxpay/";
			}
		});
	}

	$(document).ready(
			function() {
				if (typeof WeixinJSBridge == "undefined") {
					if (document.addEventListener) {
						document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
						console.log(1);
					} else if (document.attachEvent) {
						console.log(3);
						document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
						document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
					}
				} else {
					console.log(2);
					onBridgeReady();
				}
			});
</script>
</html>