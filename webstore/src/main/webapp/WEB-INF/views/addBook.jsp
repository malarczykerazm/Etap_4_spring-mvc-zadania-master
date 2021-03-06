<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Add a book</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container" style="margin-bottom: -40px; height: 190px">
				<h1>
					Add<br> <small>a new book</small>
				</h1>
				<a
					href="<spring:url value="/" />" class="btn btn-default pull-left" style="margin-right: 2px"> <span
					class="glyphicon-hand-left glyphicon"></span> back
				</a>
				<a href="<c:url value="/j_spring_security_logout" />"
					class="btn btn-danger btn-mini pull-right" style="margin-left: 2px"> <span
					class="glyphicon glyphicon-remove"></span> logout
				</a>
			</div>
		</div>
	</section>
	<section class="container">
		<form:form modelAttribute="newBook" class="form-horizontal">
			<fieldset>
				<legend>Add a new book</legend>

				<!-- Sample template for some fields in Book Entity -->
				<div class="form-group">
					<label class="control-label col-lg-2" for="name">Title</label>
					<div class="col-lg-10">
						<form:input id="title" path="title" type="text"
							class="form:input-large" required="required" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="name">Author(s)</label>
					<div class="col-lg-10">
						<form:input id="authors" path="authors" type="text"
							class="form:input-large" required="required" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="status">Status</label>
					<div class="col-lg-10">
						<form:radiobutton path="status" value="FREE" required="required" />
						Free
						<form:radiobutton path="status" value="LOAN" required="required" />
						Loan
						<form:radiobutton path="status" value="MISSING"
							required="required" />
						Missing
					</div>
				</div>
			</fieldset>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<input type="submit" id="btnAdd" class="btn btn-primary"
						value="add book" />
				</div>
			</div>
		</form:form>
	</section>
</body>
</html>
