<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Admin</title>
</head>
<body class="bg-main">
	<c:import url="./lib/menu.jsp" />
	<div class="container">
		<div class="row justify-content-center">
			<h2 class="text-center text-light mt-5 mb-3">Add a Admin User</h2>
			<div class="col-md-8 border p-5 bg-light text-light rounded">
				<form action="add" method="POST">
					<input type="text" name="firstname" class="form-control mb-2"  placeholder="Enter firstname">
					<input type="text" name="lastname" class="form-control mb-2"  placeholder="Enter lastname">
					<input type="email" name="email" class="form-control mb-2"  placeholder="Enter email">
					<input type="password" name="password" class="form-control mb-2"  placeholder="Enter password">
					<input type="password" name="passwordConfirmation" class="form-control mb-2"  placeholder="Confirm password">
					<div class="d-flex justify-content-end mt-3">
						<button class="btn btn-primary">Add</button>
					</div>
					<c:choose>
						<c:when test="${!empty error }">
							<div class="alert alert-danger mt-3"><c:out value="${error }"/></div>
						</c:when>
						<c:when test="${!empty success }">
							<div class="alert alert-success mt-3"><c:out value="${success }"/></div>
						</c:when>
					</c:choose>
				</form>
			</div>
		</div>
	</div>
</body>
</html>