<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit user</title>
</head>
<body class="bg-main">
	<c:import url="./lib/menu.jsp" />
	<div class="container">
		<div class="row justify-content-center">
			<h2 class="mb-3 text-center text-light mb-3">Edit user</h2>
			<div class="col-md-8 border p-5 bg-light text-light rounded">
				<form action="edit" method="POST">
					<input type="hidden" name="idUser"  value="<c:out value="${user.id }"/>">
					<input type="text" name="firstname" class="form-control mb-2" value="<c:out value="${user.firstname }"/>" placeholder="Enter firstname">
					<input type="text" name="lastname" class="form-control mb-2" value="<c:out value="${user.lastname }"/>" placeholder="Enter lastname">
					<input type="email" name="email" class="form-control mb-2" value="<c:out value="${user.email }"/>" placeholder="Enter email">
					<input type="password" name="password" class="form-control mb-2" placeholder="Enter password">
					<input type="password" name="passwordConfirmation" class="form-control mb-2" placeholder="Confirm password">
					<select name="roles" class="form-control">
						<option>--- Choose role ---</option>
						<c:forEach var="role" items="${roles }">
							<option value="<c:out value="${role }"/>"><c:out value="${role }"/></option>
						</c:forEach>
					</select>
					<div class="d-flex justify-content-end mt-3">
						<button class="btn btn-primary">Edit</button>
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