<%@page session="false"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<h:head>
	<sec:authorize access="isAuthenticated()">
		<meta http-equiv="refresh" content="0;url=home" />
	</sec:authorize>
</h:head>
<body>
	<h1>This is the public page which can be accessable to every one.</h1>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>
	<a href="/home">login</a>
</body>
</html>