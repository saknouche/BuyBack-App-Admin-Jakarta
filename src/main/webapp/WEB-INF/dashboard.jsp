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
				<div class="alert alert-info fw-bold text-center my-3"><c:out value="${sessionScope.user.firstname} ${sessionScope.user.lastname}, welcome to the BuyBack Administrator Access."/></div>
				<div class="row justify-content-center mt-5">
					<div class="col mb-4">
						<div class="table-responsive bg-main rounded pt-5">
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
														<td>
															<img alt="superadmin-icon" src="./assets/img/admin-icon.png" height="30">
															<i class="bi bi-check2-circle"></i>
														</td>
														<td>
														</td>
													</c:when>
													<c:otherwise>
														<c:forEach var="role" items="${sessionScope.user.roles }">
															<c:choose>
																<c:when test="${role == 'SUPER'}">
																	<td><a class="btn btn-info w-100 btn-sm"
																		href="edit?idUser=<c:out value="${u.id }"/>"><i
																			class="bi bi-pencil-square"></i></a></td>
																	<td><a
																		onclick="return confirm('Are you sur you want to delete this user? ')"
																		class="btn btn-danger w-100 btn-sm"
																		href="delete?idUser=<c:out value="${u.id }"/>"><i
																			class="bi bi-trash-fill"></i></a></td>
																</c:when>
																<c:when test="${role == 'ADMIN'}">
																	<c:forEach var="r" items="${u.roles }">
																		<c:if test="${r.name == 'USER' }">
																			<td><a
																				onclick="return confirm('Are you sur you want to delete this user? ')"
																				class="btn btn-danger w-100 btn-sm"
																				href="delete?idUser=<c:out value="${u.id }"/>"><i
																					class="bi bi-trash-fill"></i></a></td>
																			<td></td>
																		</c:if>
																		<c:if test="${r.name != 'USER' }">
																			<td>
																				<img alt="admin-icon" src="./assets/img/superadmin-icon.png" height="30">
																				<i class="bi bi-check2-circle"></i>
																			</td>
																			<td></td>
																		</c:if>
																	</c:forEach>
																</c:when>
															</c:choose>
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