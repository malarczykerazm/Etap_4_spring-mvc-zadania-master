<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Deleted book</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>The book has been deleted</h1>
				<p>
					<a href="<spring:url value="/books" />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> back
					</a>
					<a href="/webstore/books/delete" class="btn btn-default"> <span
						class="glyphicon-remove glyphicon" /></span> Delete another book
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
