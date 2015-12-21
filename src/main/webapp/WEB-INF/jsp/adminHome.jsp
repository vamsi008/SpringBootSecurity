<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<body>
	<h1>
		Hello
		<sec:authentication property="principal.username" />
	</h1>
	<h1> This is an admin screen</h1>
	<form name='loginForm' action="/logout" method='POST'>
		<input type="submit" value="Sign Out" /> <input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>