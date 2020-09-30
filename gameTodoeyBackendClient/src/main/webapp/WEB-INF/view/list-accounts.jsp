<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Users</title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Account Manager</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Customer -->
		
			<input type="button" value="Add Account"
				   onclick="window.location.href='showFormForAdd'; return false;"
				   class="add-button"
			/>
		
			<!--  add our html table here -->
		
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<!-- loop over and print our customers -->
				<c:forEach var="tempAccount" items="${accounts}">
				
					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="/user/showFormForUpdate">
						<c:param name="accountId" value="${tempAccount.id}" />
					</c:url>					

					<!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/user/delete">
						<c:param name="accountId" value="${tempAccount.id}" />
					</c:url>					
					
					<!-- construct an "delete" link with customer id -->
					<c:url var="gamesLink" value="/account/game/${tempAccount.id}"></c:url>
					
					<tr>
						<td> ${tempAccount.firstName} </td>
						<td> ${tempAccount.lastName} </td>
						<td> ${tempAccount.email} </td>
						
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
							   onclick="if (!(confirm('Are you sure you want to delete this account?'))) return false">Delete</a>
							|
							<a href="${gamesLink}">Games</a>
						</td>
						
					</tr>
				
				</c:forEach>
						
			</table>
			
		</div>
		<p><a href="${pageContext.request.contextPath}/">Back To Home</a></p>
	</div>
	

</body>

</html>









