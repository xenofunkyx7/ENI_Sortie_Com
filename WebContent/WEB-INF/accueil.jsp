<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<input type="hidden" name="utilisateur" value="${sessionScope.utilisateur}" />


	<jsp:include page="/WEB-INF/entete.jsp"></jsp:include> 
	
	
	<div class="row">
	
		<!--===================-->
		<!--======Filtres======-->
		<!--===================-->
		<h3 class="ml-5">Filtrer les sorties</h3>
		
		<div class="row">
			<!--===================-->
			<!--======GAUCHE=======-->
			<!--===================-->
			<div class="col-5 ml-5">
				<div class="row"> <!--======select site=======-->
					<div class="col-2">
						<label for="Site">Site :</label>
					</div>
					<div class="col-10">
						<select name="Site" class="form-control" id="Site">
							<option>Tous</option>
							<c:forEach items="${ sessionScope.sites }" var="site">
							    <option> ${site.nom }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="row"> <!--====== search sorties=======-->
					<div class="col-2">
						<label for="Sortie">Sortie :</label>
					</div>
					
					<div class="col-10">
						<input id="Sortie" name="Sortie" class="form-control form-control-lg form-control-borderless" 
								type="search" placeholder="Le nom de la sortie contient" onkeyup="filtre()">
					</div>
				</div>
				
				<div class="row"> <!--====== dates =======-->
					<div class="row col">
						<div class="col-3">
							<label for="DateInf">Entre :</label>
						</div>
						
						<div class="col-9">
							<input name="DateInf" class="form-control form-control-lg form-control-borderless" type="datetime-local">
						</div>
					</div>
					
					<div class="row col">
						<div class="col-3">
							<label for="DateSup"> et :</label>
						</div>
						
						<div class="col-9">
							<input name="DateSup" class="form-control form-control-lg form-control-borderless" type="datetime-local">
						</div>
					</div>
				</div>
		
			</div>
			
			<!--===================-->
			<!--======DROITE=======-->
			<!--===================-->
			
			<div class="col-5 ml-5">
					<div class="col">
						<input class="form-check-input" type="checkbox" name="IsOrganisateur"  id="IsOrganisateur">
						<label for="IsOrganisateur"> Sorties dont je suis l'organisateur/trice</label>
					</div>
					
					<div class="col">
						<input class="form-check-input" type="checkbox" name="IsInscrit"  id="IsInscrit">
						<label for="IsInscrit"> Sorties auquelles je suis inscrit/e </label>
					</div>
					
					<div class="col">
						<input class="form-check-input" type="checkbox" name="IsNotInscrit"  id="IsNotInscrit">
						<label for="IsNotInscrit"> Sorties auquelles je suis ne suis pas inscrit/e</label>
					</div>
					
					<div class="col">
						<input class="form-check-input" type="checkbox" name="IsPassee"  id="IsPassee">
						<label for="IsPassee"> Sorties passées</label>
					</div>
					
					<div>
						<button class="mt-2" type="button" id="search" class="btn btn-outline-success"> Rechercher </button>
					</div>
					
			</div>
		</div>
	</div>
	
	<div class="row overflow-auto mx-5 mt-5">
		<table class="table table-striped table-hover table-sm ">
			<thead class="thead-dark">
				<tr>
					<th scope="col"> Nom de la sortie </th>
					<th scope="col"> Date de la sortie </th>
					<th scope="col"> Clôture </th>
					<th scope="col"> Inscrit / Place</th>
					<th scope="col"> Etat </th>
					<th scope="col"> Inscrit </th>
					<th scope="col"> Organisateur </th>
					<th scope="col"> Actions </th>
				</tr>
			</thead>
			<tbody id="TableSortie">
			
				<c:forEach items="${ sessionScope.sorties }" var="sortie">
					<tr>
						<td> ${sortie.nom } </td>
						<td> ${sortie.dateHeureDebut } </td>
						<td> ${sortie.dateLimiteInscription }</td>
						<td> ${ fn:length(sortie.participants) } / ${sortie.nbInscriptionMax } </td>
						<td> ${sortie.etat } </td>
						<td>  
							<c:forEach items="${ sortie.participants }" var="participant">
								<c:if test="${participant.idParticipant == sessionScope.utilisateur.idParticipant }">
									X
								</c:if>
							</c:forEach>
						</td>
						<td> <a href="/ENI_Sortie_Com/membre/profil?pseudo=${sortie.organisateur.pseudo }">${sortie.organisateur.pseudo }</a> </td>
						<td> <a href="/ENI_Sortie_Com/membre/detailSortie?id=${sortie.id }">Afficher</a> </td>
						<td style="display: none;"> ${sortie.organisateur.pseudo } </td>
						<td style="display: none;"> ${sortie.site } </td>
						
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<button type="button" id="CreerSortie" name="CreerSortie" class="btn btn-outline-info ml-5 mt-3">
			Créer une sortie
		</button>
	</div>
	
	<script type="text/javascript" src="../js/jquery/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/Accueil.js"></script>
	
</body>
</html>