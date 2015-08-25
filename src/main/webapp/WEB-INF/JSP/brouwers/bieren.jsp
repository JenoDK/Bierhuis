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
		<c:choose>
			<c:when test="${not empty brouwer}">
				<div class="login-top">
					<h2>${brouwer.naam}(${brouwer.adres.gemeente})</h2>
				</div>
				<div class="login-bottom">
					<c:choose>
						<c:when test="${not empty brouwer.bieren}">
							<c:forEach items='${brouwer.bieren}' var='bier'>
								<p>
									<spring:url var='url' value='/bieren/{id}'>
										<spring:param name='id' value='${bier.bierNr}' />
									</spring:url>
									<a href='${url}'>${bier.naam}</a>
								</p>

							</c:forEach>
						</c:when>
						<c:otherwise>
							<p>Geen bieren gevonden</p>
						</c:otherwise>
					</c:choose>

				</div>
			</c:when>
			<c:otherwise>
				<div class="login-top">
					<h2>Brouwer niet gevonden</h2>
				</div>
			</c:otherwise>
		</c:choose>

	</div>
</body>