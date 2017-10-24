<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html class="no-js"> 
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath }/two/css/themify-icons.css">
	<!-- Bootstrap -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/two/css/bootstrap.css">
	<!-- Owl Carousel -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/two/css/owl.carousel.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/two/css/owl.theme.default.min.css">
	<!-- Magnific Popup -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/two/css/magnific-popup.css">
	<!-- Superfish -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/two/css/superfish.css">
	<!-- Easy Responsive Tabs -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/two/css/easy-responsive-tabs.css">
	<!-- Animate.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/two/css/animate.css">
	<!-- Theme Style -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/two/css/style.css">

	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	

	</head>
	<body>
		
		<header id="fh5co-header-section" role="header" class="" >
			<div class="container">
				<span class="glyphicon glyphicon-leaf" style="font-size: 40px;margin-top: 30px;"></span>
					<nav id="fh5co-menu-wrap" role="navigation">
						<ul class="sf-menu" id="fh5co-primary-menu">
							<li class="active">
								<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
							</li>

						</ul>
					</nav>
			</div>
		</header>
		
		<%
			response.setHeader("refresh", "3;url=login.jsp");
		%>
		<div id="fh5co-hero" style="background-image: url(/two/images/slide_2.jpg);">
			<div class="overlay"></div>
			<div class="container">
				<div class="col-md-12">
					<div class="fh5co-hero-wrap">
						<div class="fh5co-hero-intro">
							<div class="page-header">
                              <h1>
                              	<span class="glyphicon glyphicon-remove" style="font-size: 45px;color:#D9534F">失败 </span>
                              	<small>3秒后自动跳转到登陆页面...</small>
                              </h1>
                           </div>
						</div>
					</div>
				</div>
			</div>		
		</div>

			
			
		<!-- jQuery -->
		<script src="js/jquery-1.10.2.min.js"></script>
		<!-- jQuery Easing -->
		<script src="js/jquery.easing.1.3.js"></script>
		<!-- Bootstrap -->
		<script src="js/bootstrap.js"></script>
		<!-- Owl carousel -->
		<script src="js/owl.carousel.min.js"></script>
		<!-- Magnific Popup -->
		<script src="js/jquery.magnific-popup.min.js"></script>
		<!-- Superfish -->
		<script src="js/hoverIntent.js"></script>
		<script src="js/superfish.js"></script>
		<!-- Easy Responsive Tabs -->
		<script src="js/easyResponsiveTabs.js"></script>
		<!-- FastClick for Mobile/Tablets -->
		<script src="js/fastclick.js"></script>
		<!-- Parallax -->
		<script src="js/jquery.parallax-scroll.min.js"></script>
		<!-- Waypoints -->
		<script src="js/jquery.waypoints.min.js"></script>
		<!-- Main JS -->
		<script src="js/main.js"></script>

	</body>
</html>