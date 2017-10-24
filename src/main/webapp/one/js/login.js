//显示注册成功或失败的提示信息，显示时间总共1.5s 
	$(function() {
		if ($("#msgBoard").html() != null && $("#msgBoard").html() != "") {
			var $board = $("#msgBoard");
			$board.fadeIn(200);
			setTimeout(function() {
				$board.slideUp(800);
			}, 1500);
		}
	})
	//点击验证码图片更换验证码 
	/* $(function() {
		$("#codeImg").click(
				function() {
					$("#codeImg").prop(
							"src",
							"${pageContext.request.contextPath }/codeTest?randomNum"
									+ Math.random());
				})
	}) */
	// 判断是否记录了用户名
	$(function() {

		var rmbuser = "${cookie.rmbUser.value}";
		if (rmbuser != "") {
			$("#userName").val("${cookie.rmbUser.value }");
		}

	})
	
	
	$(function() {
		$("#login-form")
				.validate(
						{
							rules : {
								userame : {
									required : true, //验证非空
									maxlength : 12
								},
								password : {
									required : true
								},
								codeTest : "required"
							},
							messages : {
								userame : {
									required : "请输入用户名",
									maxlength : "用户名不能大于12个字符"
								},
								password : {
									required : "请输入密码"
								},
							/* codeTest : {
								required : "请输入验证码"
							} */
							},
							errorPlacement : function(error, element) {
								element.next().remove();
								element
										.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
								element.closest('.form-group').append(error);
							},
							highlight : function(element) {
								$(element).closest('.form-group').addClass(
										'has-error has-feedback');
							},
							success : function(label) {
								var el = label.closest('.form-group').find(
										"input");
								el.next().remove();
								el
										.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
								label.closest('.form-group').removeClass(
										'has-error').addClass(
										"has-feedback has-success");
								label.remove();
							},

						/* 							submitHandler : function(form) { //验证通过后的执行方法
						 //当前的form通过ajax方式提交（用到jQuery.Form文件）
						 alert("submitted!");
						 $(form).ajaxSubmit({
						 dataType : "json",
						 success : function(jsondata) {
						 if (jsondata.code = 200) {
						 alert("success");
						 } else {
						 alert("fail");
						 }
						 }
						 });
						 } */
						})
	})
	
