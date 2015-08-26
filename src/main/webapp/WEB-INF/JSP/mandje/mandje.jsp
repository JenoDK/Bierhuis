<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<link rel='stylesheet' href='<c:url value="/styles/generalStyle.css"/>'>
<link rel='stylesheet' href='<c:url value="/styles/mandjeStyle.css"/>'>
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<v:menu />
	<div class="login">

		<div class="login-top">
			<h2>Mandje</h2>
	</div>
		<div class="login-bottom"><c:choose>
				<c:when test="${not empty bierenInMandje}">
					<div class="mandjeTable">
						<table>
							<tbody>
								<tr>
									<td>Bier</td>
									<td>Prijs</td>
									<td>Aantal</td>
									<td>Te betalen</td>
							</tr>
								<c:forEach items='${bierenInMandje}' var='bierInMandje'>
									<tr>
										<td>${bierInMandje.bier.naam}</td>
										<td>&euro;${bierInMandje.bier.prijs}</td>
										<td>${bierInMandje.aantal}</td>
										<td>&euro;${bierInMandje.totaal}</td>
									</tr>
								</c:forEach>
						</tbody>
					</table>
					</div>
					<p class="totaalPrijs">Totaal: <strong>&euro;${mandjeTotaal}</strong></p>
					<div class="clear"></div>
					<c:url value='/mandje/bestelbon' var='url' />
					<form:form action='${url}' commandName='bestelbon' id='toevoegform'>
						<jsp:include page='mandjeformfields.jsp' />
						<div class='keepme'>
							<div class="keep-registrerenbutton"><input type='submit'
								value='Als bestelbon bevestigen' id='toevoegknop'></div>
							<div class="clear"></div>
						</div>
					</form:form>
				</c:when>
				<c:otherwise>
					<h2>Mandje is leeg</h2>
				</c:otherwise>
			</c:choose></div>
</div>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>