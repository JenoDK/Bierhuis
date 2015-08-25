<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype html>
<html lang='nl'>
<head>
<link rel='stylesheet' href='<c:url value="/styles/generalStyle.css"/>'>
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<v:menu />
	<div class="login">
		<div class="login-top">
			<h2>
				<spring:message code="aantalBrouwers" arguments="${aantalBrouwers}" />
			</h2>
		</div>
		<div class="login-bottom">
			<c:forEach items='${brouwers}' var='brouwer'>
				<p>
					<spring:url var='url' value='/brouwers/{id}'>
						<spring:param name='id' value='${brouwer.brouwerNr}' />
					</spring:url>
					<a href='${url}'>${brouwer.naam}</a> (${brouwer.adres.gemeente})</p>
				
			</c:forEach>
		</div>
	</div>
</body>
</html>