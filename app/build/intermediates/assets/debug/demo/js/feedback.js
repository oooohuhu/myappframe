var usersId = "";
function getUserId(userId) {
		usersId = userId;
	}
(function($, doc) {
	$.init(); // mui初始化
	mui(document.body).on('tap', '.mui-btn', function(e) {
	document.activeElement.blur();
		if(checkNotNull(usersId)) {
			var content = document.getElementById("textarea").value;
			if(checkNotNull(content)) {
				mui(document.getElementById("submit")).button('loading');
				setTimeout(function() {
					$.ajax({
						url: 'http://192.168.212.67:8080/social-hefi/static/index/api/userFeedbackInfo/saveUserFeedbackInfo',
						type: "post",
						xhrFields: {
							withCredentials: true // 设置运行跨域操作  
						},
						data: {
							"userId": usersId,
							"remarks": "测试",
							"content": content
						},
						success: function(result) {
							mui(document.getElementById("submit")).button('reset');
							if(result.code == "0") {
								mui.alert('提交成功', '提示', function() {
									history.back(-1);
								});
							} else {
								mui.alert('提交失败，请稍后重试', '提示');
							}
						}
					});
				}.bind(this), 1000);
			} else {
				mui.toast("请输入反馈内容");
			}
		}else{
			mui.toast("请先进行登录");
		}
	});
})(mui, document);