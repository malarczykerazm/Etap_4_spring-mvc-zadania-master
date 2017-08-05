<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Books</title>
</head>
<body>
	<section>
			<div class="jumbotron">
				<div class="container" style="margin-bottom: -40px; height: 190px">
					<h1>
						Books<br> <small>info</small>
					</h1>
					<p>
						<a href="<spring:url value="/" />" class="btn btn-default"> <span
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
				<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
					<%
						i = i + 1;
					%>
					<div class="thumbnail"
						style="width: 100%; margin-bottom: 20px; margin-top: 0px; padding-bottom: 0px">
						<div class="caption"
							style="width: 100%; padding-top: 0px; margin-bottom: 0px">
							<h2
								style="height: 20px; margin-top: 5px; margin-bottom: 15px; color: gray">${book.id}</h2>
							<h2 style="margin-top: 0px">${book.title}</h2>
							<p>${book.authors}</p>
							<p>Status: ${book.status}</p>
							<p>
								<a href=" <spring:url value="/books/book?id=${book.id}" /> "
									class="btn btn-primary" style="margin-bottom: -10px"> <span
									class="glyphicon-info-sign glyphicon"></span> details
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

