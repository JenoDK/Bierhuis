<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<link rel='stylesheet' href='<c:url value="/styles/generalStyle.css"/>'>
<link rel='stylesheet' href='<c:url value="/styles/bierStyle.css"/>'>
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<v:menu />
	<div class="login"><c:choose>
			<c:when test="${not empty bier}">
				<div class="login-top">
					<h2>${bier.naam}</h2>
				</div>
				<div class="login-bottom">
					<p class="artikelField"><span class="artikelFieldBeschrijving">Alcohol:
					</span>${bier.alcohol}</p>
					<p class="artikelField"><span class="artikelFieldBeschrijving">Prijs:
					</span>&euro; ${bier.prijs}</p>
					<p class="artikelField"><span class="artikelFieldBeschrijving">Soort:
					</span>${bier.soort.naam}</p>
					<p class="artikelField"><span class="artikelFieldBeschrijving">Brouwer:
					</span>${bier.brouwer.naam}</p> <spring:url var='url'
						value='/bieren/toevoegen' /> <form:form action='${url}'
						method='post' commandName='bierInMandje' id='toevoegform'>

						<form:input path='aantal' name='aantal' placeholder="Aantal"
							type="number" required='required' />
						<form:errors cssClass="error" path="aantal" delimiter=', ' />

						<form:input path='bier' type="hidden" value="${bier.bierNr}" />
						<c:if test="${bierAlInMandje}">
							<p><strong>Dit bier zit al in het mandje, kies een
									optie</strong></p>
						</c:if>
						<div class='keepme'><c:choose>
								<c:when test="${bierAlInMandje}">
									<div class="keep-registrerenbutton"><input type='submit'
										name="bierAantalVervangen" value='Aantal toevoegen'
										id='toevoegknop'></div>
									<div class="keep-registrerenbutton tweede"><input
										name="bierAantalToevoegen" type='submit'
										value='Aantal vervangen' id='toevoegknop'></div>
								</c:when>
								<c:otherwise>
									<div class="keep-registrerenbutton"><input type='submit'
										value='Toevoegen' id='toevoegknop'></div>
								</c:otherwise>
							</c:choose>

							<div class="clear"></div></div>
					</form:form>
				</div>
			</c:when>
			<c:otherwise>
				<div class="login-top">
					<h2>Bier niet gevonden</h2>
				</div>
			</c:otherwise>
		</c:choose></div>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>