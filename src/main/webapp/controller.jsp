<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <title>控制</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link href="${pageContext.request.contextPath }/one/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap3/css/bootstrap.min.css" />

    <link rel="stylesheet" href="${pageContext.request.contextPath }/one/css/sign.css" />

    <script src="${pageContext.request.contextPath }/one/js/jquery-2.1.1.js" type="text/javascript"></script>

    <script src="${pageContext.request.contextPath }/one/js/form.js" type="text/javascript"></script>

    <script src="${pageContext.request.contextPath }/one/js/jQuery.Form.js" type="text/javascript"></script>

    <script src="${pageContext.request.contextPath }/one/js/jquery.validate.js" type="text/javascript"></script>

    <script src="${pageContext.request.contextPath }/one/js/bootstrap.min.js" type="text/javascript"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath }/one/js/canvas-particle.js"></script>
    <style type="text/css">
        .account-container{
            margin-left:450px ;
            margin-top:50px;
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
            <ul class="nav nav-tabs">
                <li role="presentation" ><a href="${pageContext.request.contextPath }/login.jsp">欢迎来到长短链服务器</a></li>
                <form action="/gotorule.do"  method="get"><input type="submit" value="点击此处跳转到管理页面">点击此处跳转到管理页面</form>

            </ul>
        </ul>
    </div>
    <div class="content clearfix">
        <form id="register-form" role="form" class="form-horizontal"
              method="post"
              action="/sign.do"
              align="center">

            <br />
            <div class="form-group" align="center">
                <label class="col-sm-2 control-label" for="question">&nbsp;输入所要跳转的网址：（不要连续点击，否则会加入给黑名单的）</label>
                <div class="col-sm-5">
                    <input class="form-control" id="urlchange" name="question"
                           placeholder="请输入所要跳转的网址" />
                </div>
            </div>
            <br />
            <div class="form-group" align="middle">
                <div class="col-md-offset-2 col-md-5">
                    <button type="button"  id="signin" class="btn btn-success btn-sm">提交</button>
                    <button type="reset" class="btn btn-info btn-sm">重置</button>
                </div>
            </div>
            <br />

        </form>
    </div>
</div>
<script type="text/javascript">
    $("#signin").click(function(){



        var name=$("#urlchange");
        $.ajax({
            url:"/getService.do",
            type:"post",
            dataType:"json",
            cache: false,
            async : true,
            data:{
                "url":name.val()
            },
            success:function(data){
                $("#urlchange").val(data);
            }
        });
    })

</script>
<!-- /account-container -->
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

</body>
</html>