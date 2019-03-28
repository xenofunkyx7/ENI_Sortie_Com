<%@ page 	language="java" 	
			contentType="text/html; charset=UTF-8"
    		pageEncoding="UTF-8"
    		errorPage="/WEB-INF/erreur.jsp"
    		isErrorPage="false"
%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<jsp:include page="/WEB-INF/entete.jsp"></jsp:include> 
	
	
	<form action="#" method="POST" class="ml-5">
		<input type="text" name="pseudo" placeholder="pseudo" value="${sessionScope.participant.pseudo }" >
		<input type="text" name="nom" placeholder="nom" value="${sessionScope.participant.nom }">
		<input type="text" name="prenom" placeholder="prenom" value="${sessionScope.participant.prenom }">
		<input type="text" name="telephone" placeholder="telephone" value="${sessionScope.participant.telephone }">
		<input type="text" name="mail" placeholder="mail" value="${sessionScope.participant.mail }">
		<label>Est admin: </label> 
		<input type="checkbox" name="admin" value="admin" 
			<c:if test="${sessionScope.participant.administrateur == true}">checked="checked"</c:if>		
		>
		
		<select name="site" class="form-control" id="Site">
			<c:forEach items="${ sessionScope.sites }" var="site">
			    <c:choose>
        			<c:when test="${site.idSite == sessionScope.participant.site.idSite}" >
        				<option value="${site.idSite }" selected="selected">${site.nom }</option>
        			</c:when>
	         		<c:otherwise>
	         			<option value="${site.idSite }">${site.nom }</option>
	      			</c:otherwise>
     			</c:choose>
			</c:forEach>
		</select>
		
		<input type="hidden" name="id" value="${sessionScope.participant.idParticipant }">
		<input type="submit" name="btn" class="btn btn-link" value="Modifier">
		<a href="/ENI_Sortie_Com/admin/gererParticipants" class="btn btn-link">Annuler</a>
	</form> 
	
</body>
</html>