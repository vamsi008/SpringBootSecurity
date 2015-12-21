<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
	<head>
		<title><tiles:getAsString name="title" /></title>
		<link href="${pageContext.request.contextPath}/css/appstyle.css" rel="stylesheet" type="text/css">
	</head>
	<body class="body">
		<!-- Header -->
		<tiles:insertAttribute name="header" />
		<!-- Body -->
		<tiles:insertAttribute name="body" />
		<!-- Footer -->
		<tiles:insertAttribute name="footer" />
	</body>
</html>