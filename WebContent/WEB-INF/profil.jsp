<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<jsp:include page="/WEB-INF/entete.jsp"></jsp:include>     

	<div class="text-center">
		<fieldset>
	    <legend><h3>${membre.pseudo }</h3></legend>
	    <section class="container ">
	    <div class="row">
	        <div class="col-4">
	            <img src="${membre.image }" class="img-rounded" alt="Andre le nain">
	        </div>
	        <div class="col-8 text-left"> 
	            <p>Prénom : 				${membre.prenom }</p>
	            <p>Nom : 					${membre.nom }</p>
	            <p>Téléphone : 				${membre.telephone }</p>
	            <p>E-mail : 				${membre.mail }</p>
	            <p>Ville de rattachement : 	${membre.site.nom } </p>
	        </div>
	    </div>
	    </section>
	</fieldset>
	</div>
</body>
</html>