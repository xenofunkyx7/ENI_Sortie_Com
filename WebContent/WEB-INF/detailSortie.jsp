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
				<p>Loongitude : </p> 
			</div> 
			<div class="col-3"> 
				<p>${sortie.nom}</p> 
				<p>${sortie.dateHeureDebut}</p> 
				<p>${sortie.dateLimiteInscription}</p> 
				<p>${sortie.nbInscriptionMax} </p> 
				<p>${sortie.duree} </p> 
				<p>${sortie.infoSortie} </p> 
			</div>		 
		</div> 
	</div> 
	<h3>Liste des participants inscrits</h3> 
	<table> 
		<tr> 
			<th style="background-color: silver ">Pseudo</td> 
			<th style="background-color: silver ">Nom</td> 
		</tr> 
		<c:forEach var="participant" items="${participants}"> 
			<td>${participant.nom}</td> 
			<td>${participant.prenom}</td> 
		</c:forEach>	 
	</table> 
</body> 
</html> 