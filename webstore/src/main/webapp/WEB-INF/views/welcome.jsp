<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Welcome</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>
					<small>Welcome to</small><br>${company}</h1>
			<sec:authorize access="isAuthenticated()">
					<a href="<c:url value="/j_spring_security_logout" />"
						class="btn btn-danger btn-mini pull-left"><span
						class="glyphicon glyphicon-remove"></span> logout</a>
			</sec:authorize>
			<sec:authorize access="isAnonymous()">
				<a href="<c:url value="/login" />"
					class="btn btn-success btn-mini pull-left"><span
					class="glyphicon glyphicon-user"></span> login</a>
			</sec:authorize>
			</div>
		</div>

	</section>
	<section class="container">
		<div class="row">

			<div class="col-lg-3">
				<section class="container">
					<div class="col-lg-10" style="padding-bottom: 15px">
						<div class="thumbnail">
							<div class="caption">
								<h3>
									Show all<br> <small>books</small>
								</h3>
								<p>
									<a href="/webstore/books" class="btn btn-default"> <span
										class="glyphicon-info-sign glyphicon" /></span> show all books
									</a>
								</p>
							</div>
						</div>
					</div>

				</section>
			</div>
			<div class="col-lg-3">
				<section class="container">
					<div class="col-lg-11" style="padding-bottom: 15px">
						<div class="thumbnail">
							<div class="caption">
								<h3>
									Search<br> <small>for books</small>
								</h3>
								<p>
									<a href="/webstore/books/find" class="btn btn-default"> <span
										class="glyphicon-search glyphicon" /></span> search for books
									</a>
								</p>
							</div>
						</div>
					</div>
				</section>
			</div>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<div class="col-lg-3">
					<section class="container">
						<div class="col-lg-9" style="padding-bottom: 15px">
							<div class="thumbnail">
								<div class="caption">
									<h3>
										Add<br> <small>a book</small>
									</h3>
									<p>
										<a href="/webstore/books/add" class="btn btn-success"> <span
											class="glyphicon-plus glyphicon" /></span> add a book
										</a>
									</p>
								</div>
							</div>
						</div>
					</section>
				</div>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_LIBRARIAN')">
				<div class="col-lg-3">
					<section class="container">
						<div class="col-lg-10" style="padding-bottom: 15px">
							<div class="thumbnail">
								<div class="caption">
									<h3>
										Delete<br> <small>a book</small>
									</h3>
									<p>
										<a href="/webstore/books/delete" class="btn btn-danger"> <span
											class="glyphicon-trash glyphicon" /></span> delete a book
										</a>
									</p>
								</div>
							</div>
						</div>
					</section>
				</div>
			</sec:authorize>
		</div>
	</section>
</body>
</html>
