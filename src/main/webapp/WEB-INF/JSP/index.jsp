<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<link rel='stylesheet' href='<c:url value="/styles/generalStyle.css"/>'>
<link rel='stylesheet' href='<c:url value="/styles/indexStyle.css"/>'>
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<v:menu />
	<div class="login">
		<div class="login-top">
			<img id="logo" src="<c:url value="/images/bierhuis.jpg" />" />
			<h2>BIERHUIS</h2>
			<h3>Welkom in het huis van de Belgische bieren</h3>
			<h3>
				We hebben momenteel
				<spring:message code="aantalBieren" arguments="${aantalBieren}" />
			</h3>
		</div>
	</div>
</body>
</html>