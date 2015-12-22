<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Basic Header</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header-basic.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-basic.css">
<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
</head>

<body>

<div id="wrapper">

	<!-- The content of your page would go here. -->
	
		<tiles:insertAttribute name="header" />
		<!-- Body -->
		<div id="content">
		<tiles:insertAttribute name="body" />
		</div>
		<!-- Footer -->
		<tiles:insertAttribute name="footer" />


	<!-- Demo ads. Please ignore and remove. -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

</div>
</body>

</html>