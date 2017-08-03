<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Error 404</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container" style="margin-bottom: -40px; height: 190px">
				<h1>
					Error 404<br> <small>File not found or incomplete file provided</small>
				</h1>
				<p>${errorMessage}</p>
			</div>
		</div>
	</section>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please come back to the home page</h3>
					</div>
					<fieldset>
						 <a href=" <spring:url value="/" /> "
							class="btn btn-lg btn-primary btn-block"> <span
							class="glyphicon-hand-left glyphicon" /></span> back to home page
						</a>

					</fieldset>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
