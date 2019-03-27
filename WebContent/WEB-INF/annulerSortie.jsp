<%@ page 	language="java" 	
			contentType="text/html; charset=UTF-8"
    		pageEncoding="UTF-8"
    		errorPage="/WEB-INF/erreur.jsp"
    		isErrorPage="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<jsp:include page="/WEB-INF/entete.jsp"></jsp:include> 
	
	<h2 class="text-align">Annuler une sortie</h2>
	<form class="form-horizontal" action="${pageContext.request.contextPath}/membre/annulerSortie?id=${sortie.id}" method="POST">
		<div class="d-flex p-2">
			<div class="container">
				<div class="row">
					<div class="col-3">
						<p>Nom de la sortie : </p>
						<p>Date de la sortie : </p>
						<p>Ville organisatrice : </p>
						<p>lieu : </p>
						<label>Motif :</label>>
	             	</div>
	                <div class="col-3">
                       <p>${sortie.nom}</p>
                       <p>${sortie.dateHeureDebut}</p>  
                       <p>${sortie.lieu.ville.nom}</p>
                       <p>${sortie.lieu.nom}</p>
                       <textarea id="idMotif" name="motif" class="form-control" required></textarea>
					</div>
				</div>
			</div>
		</div>
	<button type="submit" class=" form-control col-3" value="enregistrer" name="etat">Enregistrer</button>
	<a class="form-control  col-3" href="${pageContext.request.contextPath}/membre/accueil">Annuler</a>
		
	</form>






  </body>
</html>