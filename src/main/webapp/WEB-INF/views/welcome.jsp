<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Welcome</title>
</head>
<body>
<h1>
	Welcome <c:out value="${name}"></c:out>!
</h1>

	
	<h2>
		You have <c:out value="${numFriends}"></c:out> friends!
	</h2>

</body>
</html>