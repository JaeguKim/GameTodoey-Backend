<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

		<c:choose>
			<c:when test="${userId == 0}">
   				<c:url var="saveGameLink" value="/game/saveGame"></c:url>
			</c:when>
			<c:otherwise>	
 				<c:url var="saveGameLink" value="/user/addGame/${userId}"></c:url>
			</c:otherwise>
		</c:choose>
		
		<form:form action="${saveGameLink}" modelAttribute="game" method="POST">

			<!-- need to associate this data with game id -->
			<form:hidden path="id" />
			<table>
				<tbody>
					<c:choose>
						<c:when test="${userId == 0}">
							<tr>
								<td><label>title:</label></td>
								<td><form:input path="title" /></td>
							</tr>
						</c:when>
						<c:otherwise>
							<select name="category">
								<option value="category_id">game1</option>
								<option value="category_id">game2</option>
								<option value="category_id">game3</option>
							</select>
						</c:otherwise>
					</c:choose>
					
					
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










