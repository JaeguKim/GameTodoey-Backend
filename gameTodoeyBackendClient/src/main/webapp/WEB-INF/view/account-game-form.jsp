<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Game</title>

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
			<h2>Game Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Game</h3>
	
		<form:form action="saveGame" modelAttribute="game" method="POST">

			<!-- need to associate this data with game id -->
			<form:hidden path="id" />
			<input type="hidden" name="userId" value="${userId}">
			<table>
				<tbody>
					<tr>
						<td><label>title:</label></td>
						<td><form:input path="title" /></td>
					</tr>
				
					<tr>
						<td><label>popularity:</label></td>
						<td><form:input path="popularity" /></td>
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
			<a href="${pageContext.request.contextPath}/game/list">Back to List</a>
		</p>
	
	</div>

</body>

</html>










