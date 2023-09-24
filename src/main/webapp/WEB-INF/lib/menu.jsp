<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="./assets/css/styles.css">
</head>
<body>
	<div class="container-fluid bg-box mb-4 py-2">
		<div class="row">
			<div class="col d-flex justify-content-between align-items-center">
				<a href="dashboard">
					<img alt="logo" src="assets/img/logo_circle.svg" class="img-fluid logo">
				</a>
			<c:choose>
				<c:when test="${!empty sessionScope.user }">
				<div>
					<c:forEach var="r" items="${sessionScope.user.roles }">
						<c:if test="${r == 'SUPER' }">
							<a href="add" class="text-decoration-none me-3 fw-bold btn btn-outline-light">Add Admin</a>
						</c:if>
					</c:forEach>
					<a class="btn btn-danger fw-bold" href="disconnect">Logout</a>
				</div>
				</c:when>
			</c:choose>
			</div>
		</div>
	</div>
</body>
</html>