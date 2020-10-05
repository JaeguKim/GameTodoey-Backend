<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>

<html>

<head>

	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>GameTodoey Admin Web Site</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
			<p><a href="${pageContext.request.contextPath}/account/list">Account List</a></p>
			<p><a href="${pageContext.request.contextPath}/game/list">Game List</a></p>
			
	
		</div>
		<!-- Add a logout button -->
		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">
			<input type="submit" value="logout" />
		</form:form>
	</div>
	

</body>

</html>









