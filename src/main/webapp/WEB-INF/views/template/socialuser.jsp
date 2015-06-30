<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty socialUserProfileImage}">
	<!-- logo image -->
	<div class="logo">
		<img
			src="data:image/png;base64,<c:out value="${socialUserProfileImage}"/>"
			class="img-responsive pull-left" width="160px" height="160px" />
	</div>
	<br />
</c:if>

<c:if test="${not empty socialUserName}">
	<h4 class="char">
		&nbsp;Welcome
		<c:out value="${socialUserName}" />
	</h4>
</c:if>

<!-- right side image -->
<div class="rightImage pull-right">
	<img src="<c:url value="/assets/images/logo1.png" />"
		class="img-responsive" />
</div>
