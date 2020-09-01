<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Customer</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>Review Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Review</h3>
	
		<form:form action="saveReview" modelAttribute="review" method="POST">

			<!-- need to associate this data with game id -->
			<form:hidden path="id" />
					
			<table>
				<tbody>
					<tr>
						<td><label>rating:</label></td>
						<td><form:input path="rating" /></td>
					</tr>
				
					<tr>
						<td><label>comment:</label></td>
						<td><form:input path="comment" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				
				</tbody>
			</table>
		
		
		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/review/list">Back to List</a>
		</p>
	
	</div>

</body>

</html>










