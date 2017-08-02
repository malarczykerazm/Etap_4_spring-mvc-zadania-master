<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Find a book</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Search<br><small>find a book</small></h1>
				<a href="<spring:url value="/" />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> back
					</a>
			</div>
		</div>
	</section>
	<section class="container">
		<form:form modelAttribute="paramsOfBook" class="form-horizontal">
			<fieldset>
				<legend>Find a book</legend>

				<!-- Sample template for some fields in Book Entity -->
				<div class="form-group">
					<label class="control-label col-lg-2" for="name">Title</label>
					<div class="col-lg-10">
						<form:input id="title" path="title" type="text"
							class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="name">Author(s)</label>
					<div class="col-lg-10">
						<form:input id="authors" path="authors" type="text"
							class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="status"></label>
					<div class="col-lg-10">
						<form:checkbox path="status" value="FREE" />
						free books only
					</div>
				</div>
			</fieldset>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<input type="submit" id="btnSearch" class="btn btn-primary"
						value="search" />
				</div>
			</div>
		</form:form>
	</section>
</body>
</html>
