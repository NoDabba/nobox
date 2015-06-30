
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title><tiles:insertAttribute name="title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta content="" name="description" />
<meta content="" name="author" />

<!-- no dabba styles -->

<jsp:include page="/WEB-INF/views/common/cssInclude.jsp" />
<tiles:insertAttribute name="inLineCssStyle" />

</head>

<body>
	<div class="page-container">
		<div class="container noDabbaNav">
			<tiles:insertAttribute name="header" />
			<tiles:insertAttribute name="navigation" />
			
			<tiles:insertAttribute name="socialuser" />
		</div>

		<tiles:insertAttribute name="banner" />
		
		<!-- middle block -->
		<div class="container">
			<tiles:insertAttribute name="body" />
			
			<!-- footer -->
			<tiles:insertAttribute name="footer" />
			<!-- footer end -->
		</div>

	</div>

	<!-- nodabba javascript -->
	<jsp:include page="/WEB-INF/views/common/jsInclude.jsp" />


</body>
</html>