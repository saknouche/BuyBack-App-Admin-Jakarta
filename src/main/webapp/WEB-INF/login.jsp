<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign in</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="./assets/css/styles.css">
</head>
<body class="bg-main">
	<div class="container">
		<div class="row justify-content-center align-items-center"
			style="height: 100vh">
			<div class="col-md-5 p-5 bg-box text-light rounded bg-box">
				<form action="login" method="POST">
					<div class="d-flex justify-content-center mb-3">
						<img alt="sign in logo" src="assets/img/admin.png"
							class="img-fluid w-50 rounded-circle">
					</div>
					<h2 class="mb-3 text-center">Please sign in</h2>
					<input type="text" name="email" class="form-control mb-2"
						placeholder="email"> <input type="password"
						name="password" class="form-control mb-2" placeholder="password">
					<div class="d-flex justify-content-end">
						<button class="btn btn-primary">Sign in</button>
					</div>
					<c:if test="${!empty message }">
						<div class="alert alert-danger mt-4"><c:out value="${message}"/></div>
					</c:if>
				</form>
			</div>
		</div>
	</div>
</body>
</html>