<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Welcome!  
</h1>

	<form action="<c:url value="/signin/facebook" />" method="POST">
		<button type="submit">Sign in with Facebook</button>
		<input type="hidden" name="scope"
			value="email,user_likes,friends_likes,publish_stream" />
	</form>
	
	<a href="<c:url value="/connect/facebook" />">Connect to Facebook</a>
	
	<form action="<c:url value="/connect/facebook" />" method="POST">
    <input type="hidden" name="scope" value="publish_stream,offline_access" />
    <p>You haven't created any connections with Twitter yet. Click the button to create
       a connection between your account and your Twitter profile. 
       (You'll be redirected to Twitter where you'll be asked to authorize the connection.)</p>
    <p><button type="submit">
    </button></p>
</form>

</body>
</html>