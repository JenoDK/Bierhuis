<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<div id="wrap">
	<ul class="navbar">
		<li><a href="<c:url value='/'/>">Home</a></li>
		<li><a href="<c:url value='/brouwers'/>">Bieren van een brouwer</a></li>
		<li><a href="<c:url value='/mandje'/>">Winkelwagen</a></li>
	</ul>
</div>
<div class="clear"></div>
