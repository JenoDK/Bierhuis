<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
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
			<h2>Bestelbon</h2>
	</div>
		<div class="login-bottom"><c:choose>
				<c:when test="${not empty bestelbon}"><h2>Je winkelwagentje is bevestigd als bestelbon ${bestelbon.bonNr}</h2></c:when>
				<c:otherwise>
					<c:if test='${not empty fout}'>
						<div class='fout'><h2>${fout}</h2></div>
					</c:if>
				</c:otherwise>
			</c:choose></div>
</div>
</body>
</html>