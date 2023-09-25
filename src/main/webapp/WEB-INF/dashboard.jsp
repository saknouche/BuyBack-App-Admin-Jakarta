<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link rel="stylesheet" href="./assets/css/styles.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body class="bg-main">
	<c:import url="./lib/menu.jsp" />
	<c:choose>
		<c:when test="${!empty users }">
			<div class="container pt-5">
				<h1 class="text-center text-light my-5">BuyBack Admin</h1>
				<div class="row justify-content-center">
					<div class="col-md-8 mb-4">
						<div class="table-responsive bg-main rounded">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Firstname</th>
										<th scope="col">Lastname</th>
										<th scope="col">Email</th>
										<th scope="col">Roles</th>
										<th scope="col">Actions</th>
										<th scope="col"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="u" items="${users }" varStatus="status">
										<tr>
											<th scope="row"><c:out value="${status.count }" /></th>
											<td><c:out value="${u.firstname }" /></td>
											<td><c:out value="${u.lastname }" /></td>
											<td><c:out value="${u.email }" /></td>
											<td><c:forEach var="role" items="${u.roles }">
													<c:choose>
														<c:when
															test="${role.name == 'ADMIN' || role.name == 'SUPER'}">
															<span><i class="bi bi-circle-fill text-success"></i>
																<c:out value="${role.name } "></c:out></span>
														</c:when>
														<c:otherwise>
															<span><i class="bi bi-circle-fill text-danger"></i>
																<c:out value="${role.name } "></c:out></span>
														</c:otherwise>
													</c:choose>
												</c:forEach></td>
											<c:forEach var="role" items="${u.roles }">
												<c:choose>
													<c:when test="${role.name == 'SUPER'}">
														<td></td>
														<td></td>
													</c:when>
													<c:otherwise>
														<c:forEach var="role" items="${sessionScope.user.roles }">
															<c:if test="${role == 'SUPER' }">
																<td><a class="btn btn-info w-100"
																	href="edit?idUser=<c:out value="${u.id }"/>"><i
																		class="bi bi-pencil-square"></i></a>
																</td>
															</c:if>
														<td><a
															onclick="return confirm('Are you sur you want to delete this user? ')"
															class="btn btn-danger w-100"
															href="delete?idUser=<c:out value="${u.id }"/>"><i
																class="bi bi-trash-fill"></i></a>
														</td>
														<c:if test="${role != 'SUPER' }">
															<td></td>
														</c:if>
														</c:forEach>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="container">
				<div class="d-flex justify-content-center">
					<div class="alert alert-danger col-md-6 text-center mt-5 ">No
						available users</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>