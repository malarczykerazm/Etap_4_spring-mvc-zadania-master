<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Searching results</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Searching results<br><small>found books info</small></h1>
				<p>
					<a href="<spring:url value="/books/find" />"
						class="btn btn-default"> <span
						class="glyphicon-hand-left glyphicon"></span> back
					</a>
				</p>

			</div>
		</div>
	</section>

	<section class="container">
		<%
			int i = 0;
		%>
		<div class="row">
			<c:forEach items="${bookList}" var="book">
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					<%
						i = i + 1;
					%>
					<div class="thumbnail">
						<div class="caption">
							<h3>${book.id}</h3>
							<h2>${book.title}</h2>
							<p>${book.authors}</p>
							<p>Status: ${book.status}</p>
							<p>
								<a href=" <spring:url value="/books/book?id=${book.id}" /> "
									class="btn btn-primary"> <span
									class="glyphicon-info-sign glyphicon" /></span> details
								</a>
							</p>

						</div>
					</div>
					<%=i % 4 == 0 ? "</div><div class=\"row\">" : ""%>
				</div>
			</c:forEach>
		</div>

	</section>
</body>
</html>
