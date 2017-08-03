<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
				<h1>
					Error 403<br> <small>Access denied</small>
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
						<h3 class="panel-title">What would you like to do now?</h3>
					</div>
					<fieldset>
						<a href=" <spring:url value="/login" /> "
							class="btn btn-lg btn-default btn-block"> <span
							class="glyphicon-user glyphicon"></span> switch to another account
						</a> <a href=" <spring:url value="/" /> "
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
