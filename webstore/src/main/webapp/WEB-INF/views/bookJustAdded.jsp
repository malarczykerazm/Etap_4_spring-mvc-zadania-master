<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Book</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Book info</h1>
				<p>Here you can see the chosen book info</p>
				<p>
					<a href="<spring:url value="/books" />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> back
					</a>
					<a href="/webstore/books/add" class="btn btn-default"> <span
						class="glyphicon-info-sign glyphicon" /></span> add another book
					</a>
				</p>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<div class="col-md-5">
				<div class="thumbnail">
					<h3>&nbsp;&nbsp;${book.id}</h3>
					<p>
						<strong>&nbsp;&nbsp;Book title: </strong>${book.title}
					</p>
					<p>
						<strong>&nbsp;&nbsp;Wrote by: </strong>${book.authors}
					</p>
					<p>
						<strong>&nbsp;&nbsp;Current status: </strong>${book.status}
					</p>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
