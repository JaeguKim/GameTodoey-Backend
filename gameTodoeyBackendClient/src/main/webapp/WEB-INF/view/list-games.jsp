<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Games</title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Game Manager</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Game -->
			<input type="button" value="Add Game"
				   onclick="window.location.href='${pageContext.request.contextPath}/game/showFormForAdd/${userId}'; return false;"
				   class="add-button"
			/>
		
			<!--  add our html table here -->
		
			<table>
				<tr>
					<th>title</th>
					<th>popularity</th>
					<th>Action</th>
				</tr>
				
				<!-- loop over and print our customers -->
				<c:forEach var="tempGame" items="${games}">
				
					<!-- construct an "update" link with game id -->
					<c:url var="updateLink" value="/game/showFormForUpdate">
						<c:param name="gameId" value="${tempGame.id}" />
					</c:url>					

					<!-- construct an "delete" link with game id -->
					<c:url var="deleteLink" value="/game/delete">
						<c:param name="gameId" value="${tempGame.id}" />
					</c:url>					
					
					<!-- construct an "review" link with game id -->
					<c:url var="reviewLink" value="/review/${tempGame.id}">
					</c:url>					
					
					
					<tr>
						<td> ${tempGame.title} </td>
						<td> ${tempGame.popularity} </td>
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
							   onclick="if (!(confirm('Are you sure you want to delete this game?'))) return false">Delete</a>
							|
							<a href="${reviewLink}">Reviews</a>
						</td>
						
					</tr>
				
				</c:forEach>
						
			</table>
				
		</div>
		<p><a href="${pageContext.request.contextPath}/">Back To Home</a></p>
	</div>
	

</body>

</html>









