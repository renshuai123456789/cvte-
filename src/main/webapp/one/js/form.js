$(document).ready(function() {
	// 手机号码验证
	jQuery.validator.addMethod("isPhone", function(value, element) {
		var length = value.length;
		return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));
	}, "请正确填写您的手机号码。");

//	// 电话号码验证
//	jQuery.validator.addMethod("isTel", function(value, element) {
//		var tel = /^(\d{3,4}-)?\d{7,8}$/g; // 区号－3、4位 号码－7、8位
//		return this.optional(element) || (tel.test(value));
//	}, "请正确填写您的电话号码。");
	
	//用户名验证
	jQuery.validator.addMethod("stringCheck", function(value, element) {       
    	return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);       
	}, "只能包括中文字、英文字母、数字和下划线");  
	
//	// 匹配密码，以字母开头，长度在6-12之间，只能包含字符、数字和下划线。      
//    jQuery.validator.addMethod("isPwd", function(value, element) {       
//         return this.optional(element) || /^[a-zA-Z]\\w{6,12}$/.test(value);       
//    }, "以字母开头，长度在6-12之间，只能包含字符、数字和下划线。");  
    
  //校验密码是否含有空格
    jQuery.validator.addMethod("notblank", function(value, element) {
           var pwdblank = /^\S*$/;
           return this.optional(element) ||(pwdblank.test(value));
      }, "密码不可包含空格");
       //用户名必须需包含数字和大小写字母中至少两种
       jQuery.validator.addMethod("userrule", function(value, element) {
           var userblank = /^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)[0-9A-Za-z]{6,12}$/;
           return this.optional(element) ||(userblank.test(value));
       }, "需包含数字和大小写字母中至少两种字符的6-12位字符");

	$("#register-form").validate({
		errorElement: 'span',
		errorClass: 'help-block',
		focusInvalid: true, //验证提示时，鼠标光标指向提示的input

		rules: {
			username: {
				required: true, //验证非空
				maxlength: 12,
				stringCheck : true,
				remote: {                                            //远程ajax验证
					url: "/softlab1010_website/user/distinctUserName.action" ,    //检查是否存在账号，存在则返回true   后台处理程序
					type: "POST",                                 //数据发送方式  post or get
					dataType: "json" ,                     //接受数据格式 
					data: {                                     //要传递的数据
						account: function() {
							return $("#firstname").val(); //这个是取要验证的用户名
						}
					},
					dataFilter: function(data) { //判断控制器返回的内容
						var notice = eval("(" + data + ")");
						if(notice) {
							return false;
						} else {
							return true;
						}
					}
				}
			},
			email: {
				email: true
			},
			password: {
				required: true,
//				isPwd: true
				notblank: true,
				userrule: true
			},
			confirm_password: {
				required: true,
				notblank: true,
				userrule: true,
				equalTo: "#password"
			},
			phone: {
				required: true,
				isPhone: true
			},
			number: {
				digits: true,
				maxlength: 10
			},
			question: {
				required: true,
			},
			answer: {
				required: true,
			}
			
		},
		messages: {
			username: {
				required:"请输入用户名",
				maxlength:"用户名不能大于12个字符",
				stringCheck:"用户名只能包括中文字、英文字母、数字和下划线",
				remote: "用户名已存在！"  //如果不写的话，是自带的提示内容
			},
			email: {
				email: "请输入正确的email地址"
			},
			password: {
				required: "请输入密码",
//				isPwd:"以字母开头，长度在6-12之间，只能包含字符、数字和下划线"
				notblank:"密码不可包含空格",
				userrule: "需包含数字和大小写字母中至少两种字符的6-12位字符"
			},
			confirm_password: {
				required: "请输入确认密码",
//				isPwd:"以字母开头，长度在6-12之间，只能包含字符、数字和下划线",
				notblank:"密码不可包含空格",
				userrule: "需包含数字和大小写字母中至少两种字符的6-12位字符",
				equalTo: "两次输入密码不一致"
			},
			phone: {
				required: "手机号码不能为空",
				isPhone: "请正确填写您的手机号码"
			},
			question:{
				required: "问题设置不能为空"
			},
			answer:{
				required: "问题答案不能为空"
			}
		},
		errorPlacement: function(error, element) {
			element.next().remove();
			element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
			element.closest('.form-group').append(error);
		},
		highlight: function(element) {
			$(element).closest('.form-group').addClass('has-error has-feedback');
		},
		success: function(label) {
			var el = label.closest('.form-group').find("input");
			el.next().remove();
			el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
			label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
			label.remove();
		},
		
		/*submitHandler: function(form) { //验证通过后的执行方法
			//当前的form通过ajax方式提交（用到jQuery.Form文件）
			$(form).ajaxSubmit({
				dataType: "json",
				success: function(jsondata) {
					if(jsondata.code = 200) {
						window.location.href="/softlab1010_website/welcome/index.action";
					} else {
						alert("fail");
					}
				}
			});
		}*/
	})
});