<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/erreur.jsp" isErrorPage="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/entete.jsp"></jsp:include> 
<section class="container">
	<section class="row">
		<h1>Traitement des erreurs</h1>
		<h3>Veuillez contacter votre support informatique et communiquer les informations suivantes :</h3>
		<h5>Je suis un utilisateur et j'ai tout cassé.</h5>
	</section>
	<section class="row">
		<h3>Erreurs Servlet</h3>
		<p><%= exception.getClass().getName() %></p>
		<p><%=exception.getMessage()%></p>
	</section>
	<section class="row">
		<h3>Erreur autres</h3>
		<p>${requestScope.exception[0].className}</p>
		<p>${requestScope.exception[0].methodName}</p>
		<p>${requestScope.exception[0].fileName}</p>
		<p>${requestScope.exception[0].lineNumber}</p>
		<p>${requestScope.exception[1].className}</p>
		<p>${requestScope.exception[1].methodName}</p>
		<p>${requestScope.exception[1].fileName}</p>
		<p>${requestScope.exception[1].lineNumber}</p>
		
		<c:if test="${! empty sessionScope.erreur }">
			<p>${sessionScope.erreur }</p>
		</c:if>
	</section>
	
	
</section>
</body>
</html>