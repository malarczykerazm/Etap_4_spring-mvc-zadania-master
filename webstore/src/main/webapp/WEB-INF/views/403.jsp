<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Error 403</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Error 403<br><small>Access denied</small></h1>
				<p>${errorMessage}</p>
				<a href="<spring:url value="/" />" class="btn btn-default"> <span
					class="glyphicon-hand-left glyphicon"></span> back to home page
				</a>
			</div>
		</div>
	</section>
</body>
</html>
