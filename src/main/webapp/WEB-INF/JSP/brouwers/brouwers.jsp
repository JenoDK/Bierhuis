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
			<h2><spring:message code="aantalBrouwers"
					arguments="${aantalBrouwers}" /></h2>
	</div>
		<div class="login-bottom"><c:forEach items='${page.content}'
				var='brouwer'>
				<p><spring:url var='url' value='/brouwers/{id}'>
						<spring:param name='id' value='${brouwer.brouwerNr}' />
					</spring:url> <a href='${url}'>${brouwer.naam}</a> (${brouwer.adres.gemeente})</p>

			</c:forEach>
			<p class='pagineren'><c:choose>
					<c:when test="${page.hasPrevious()}">
						<c:url value="" var="url">
							<c:param name="page" value="${page.number-1}" />
						</c:url>
						<a href="${url}"><img class="paginaImg" src="<c:url value="/images/arrow_left.png" />" /></a>
					</c:when>
					<c:otherwise>
						<img class="paginaImg" src="<c:url value="/images/arrow_left2.png" />" />
					</c:otherwise>
				</c:choose> <span class="paginaCijfers"><c:forEach var="pageNr" begin="1" end="${page.totalPages}">
					<c:choose>
						<c:when test="${pageNr-1 == page.number}">
							${pageNr}
						</c:when>
						<c:otherwise>
							<c:url value="" var="url">
								<c:param name="page" value="${pageNr-1}" />
							</c:url>
							<a href="${url}">${pageNr}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach> </span><c:choose>
					<c:when test="${page.hasNext()}">
						<c:url value="" var="url">
							<c:param name="page" value="${page.number+1}" />
						</c:url>
						<a href="${url}"><img class="paginaImg" src="<c:url value="/images/arrow_right.png" />" /></a>
					</c:when>
					<c:otherwise>
						<img class="paginaImg" src="<c:url value="/images/arrow_right2.png" />" />
					</c:otherwise>
				</c:choose></p></div>
</div>
</body>
</html>