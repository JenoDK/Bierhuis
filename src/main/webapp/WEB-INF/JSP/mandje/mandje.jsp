<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
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
			<h2>Mandje</h2>
		</div>
		<div class="login-bottom">
			<c:choose>
				<c:when test="${not empty bierenInMandje}">
					<table>
						<thead>
							<tr>
								<th>Bier</th>
								<th>Prijs</th>
								<th>Aantal</th>
								<th>Te betalen</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items='${bierenInMandje}' var='bierInMandje'>
								<tr>
									<td>${bierInMandje.bier.naam}</td>
									<td>${bierInMandje.bier.prijs}</td>
									<td>${bierInMandje.aantal}</td>
									<td>${bierInMandje.totaal}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<p>Totaal: ${mandjeTotaal}</p>
				</c:when>
				<c:otherwise>
					<h2>Mandje is leeg</h2>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>