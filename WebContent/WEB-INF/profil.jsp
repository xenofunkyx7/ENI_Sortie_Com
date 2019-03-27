<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<jsp:include page="/WEB-INF/entete.jsp"></jsp:include>     

	<div class="text-center">
		<fieldset>
	    <legend>${membre.pseudo }</legend>
	    <section class="container ">
	    <div class="row">
	        <div class="col-4">
	            <img src="../image/${membre.image }" class="img-fluid rounded" alt="Avatar">
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