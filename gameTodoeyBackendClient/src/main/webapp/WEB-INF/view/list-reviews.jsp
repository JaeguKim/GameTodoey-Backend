<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Reviews</title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Review</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Customer -->
		
			<input type="button" value="Add Review"
				   onclick="window.location.href='showFormForAdd/${title}/${gameId}'; return false;"
				   class="add-button"
			/>
		
			<!--  add our html table here -->
		
			<table>
				<tr>
					<th>rating</th>
					<th>comment</th>
					<th>Action</th>
				</tr>
				
				<!-- loop over and print our customers -->
				<c:forEach var="tempReview" items="${reviews}">
				
					<!-- construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/review/delete/${gameId}">
						<c:param name="reviewId" value="${tempReview.id}" />
					</c:url>					
					
					<tr>
						<td> ${tempReview.rating} </td>
						<td> ${tempReview.comment} </td>
						<td>
					
							<a href="${deleteLink}"
							   onclick="if (!(confirm('Are you sure you want to delete this review?'))) return false">Delete</a>
						</td>
						
					</tr>
				
				</c:forEach>
						
			</table>
			
			<p>
				<a href="${pageContext.request.contextPath}/game/list">Back to Game List</a>
			</p>
		</div>
	
	</div>
	

</body>

</html>









