<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/entete.jsp"></jsp:include>  
	<h1>Logo Sortir.com</h1> 
	<h2>Afficher une sortie</h2> 
	<div class="container"> 
		<div class="row"> 
			<div class="col-3 "> 
				<p>Nom de la sortie : </p> 
				<p>Date et heure de la sortie : </p> 
				<p>Date limite d'inscription : </p> 
				<p>Nombre de places : </p> 
				<p>Dur�e : </p> 
				<p>Description et infos : </p> 
			</div> 
			<div class="col-3"> 
				<p>${sortie.nom}</p> 
				<p>${sortie.dateHeureDebut}</p> 
				<p>${sortie.dateLimiteInscription}</p> 
				<p>${sortie.nbInscriptionMax} </p> 
				<p>${sortie.duree} </p> 
				<p>${sortie.infoSortie} </p> 
			</div> 
			<div class="col-3 "> 
				<p>Ville organisatrice : </p> 
				<p>Lieu : </p> 
				<p>Rue : </p> 
				<p>Code Postal : </p> 
				<p>Latitude : </p> 
				<p>Longitude : </p> 
			</div> 
			<div class="col-3"> 
				<p>${sortie.lieu.ville.nom}</p> 
				<p>${sortie.lieu.nom}</p> 
				<p>${sortie.lieu.rue}</p> 
				<p>${sortie.lieu.ville.codePostal}</p> 
				<p>${sortie.lieu.latitude}</p> 
				<p>${sortie.lieu.longitude}</p> 
			</div>		 
		</div> 
	</div> 
	<h3>Liste des participants inscrits</h3> 
	<div class="row">
		<table class="table col-3 ml-3 table-striped table-hover table-sm"> 
			<tr> 
				<th style="background-color: silver ">Pseudo</th> 
				<th style="background-color: silver ">Nom</th> 
			</tr>
			<c:forEach var="participant" items="${requestScope.sortie.participants}"> 
				<td>${participant.nom} </td> 
				<td>${participant.prenom}</td> 
			</c:forEach>	 
		</table> 
	</div>
</body> 
</html> 