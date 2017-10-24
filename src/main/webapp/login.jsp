<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">


<link rel="stylesheet" href="${pageContext.request.contextPath }/one/font-awesome/css/font-awesome.min.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap3/css/bootstrap.min.css" />

<link href="${pageContext.request.contextPath }/one/css/drag.css" rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath }/one/css/login.css" rel="stylesheet" type="text/css">

<script src="${pageContext.request.contextPath }/one/js/modernizr.js"></script>

<script src="${pageContext.request.contextPath }/one/js/jquery-2.1.1.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath }/one/js/form.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath }/one/js/jQuery.Form.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath }/one/js/jquery.validate.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath }/one/js/bootstrap.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath }/one/js/login.js" type="text/javascript"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/one/js/canvas-particle.js"></script>


<style type="text/css">
.account-container{
margin-left:450px ;
margin-top:150px;
}
.header{
padding-left:100px;
width:500px;
}
</style>
</head>

<body>
	

	<div class="account-container">
	<!--胶囊式导航栏-->
		<div class="header">
			<ul class="nav nav-tabs">
			  <li role="presentation" class="active"><a href="${pageContext.request.contextPath }/login.jsp">登录</a></li>
			  <li role="presentation"><a href="${pageContext.request.contextPath }/sign.jsp">注册</a></li>
			  <li role="presentation"><a href="${pageContext.request.contextPath }/index.jsp">首页</a></li>
			</ul>
		</div>
		<div class="content clearfix">
		
			<form id="login-form" role="form" class="form-horizontal"
				method="post"
				>

				<div class="form-group" align="center">
					<label class="col-sm-2 control-label" for="username">用户名：</label>
					<div class="col-sm-5">
						<input class="form-control" id="firstname" name="username"
							placeholder="请输入用户名" value="" />
					</div>
				</div>

				<br />
				<div class="form-group" align="center">
					<label class="col-sm-2 control-label" for="password">密码：</label>
					<div class="col-sm-5">
						<input class="form-control" id="password" name="password"
							type="password" placeholder="请输入密码" />
					</div>
				</div>

				<div id="msgBoard" align="center"
					style="color: red; font-family: '楷体'; font-size: 16px"
					class="col-sm-9">${userMsg}</div>
				<br /> <br />

				<!-- 滑动滑块进行验证，验证成功解锁登陆按钮 -->
				<div class="form-group">
					<div class="col-sm-9" align="center">
						<div id="drag" align="center"></div>
					</div>
					<br />



					<br />
					<div class="form-group" align="center">
						<div class="col-md-offset-2 col-sm-5">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-success "
								disabled="disabled" id="btnLogin">登录</button>
						</div>
					</div>
			</form>
		</div>
		<!-- /content -->
	</div>
	<!-- /account-container -->
	</div>

	

	<!-- 滑动验证所需文件 -->
	<script src="${pageContext.request.contextPath }/one/js/drag.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		$('#drag').drag();
	</script>
	<script type="text/javascript">
		window.onload = function() {
			var config = {
				vx : 4,
				vy : 4,
				height : 2,
				width : 2,
				count : 100,
				color : "121, 162, 185",
				stroke : "100, 200, 180",
				dist : 6000,
				e_dist : 20000,
				max_conn : 10
			}
			CanvasParticle(config);
		}
	</script>
	<script type="text/javascript">
        $("#btnLogin").click(function(){
            var name=$("#firstname");
            var password=$("#password");
            $.ajax({
                url:"/login.do",
                type:"post",
                dataType:"json",
                cache: false,
                async : true,
                data:{
                    "username":name.val(),
                    "password":password.val()
                },
                success:function(data){
                	if(data){
                        $(window).attr('location','/success.jsp');
					}else{

                        $(window).attr('location','/login.jsp');
					}
            	}
			});
        })

	</script>
</body>

</html>