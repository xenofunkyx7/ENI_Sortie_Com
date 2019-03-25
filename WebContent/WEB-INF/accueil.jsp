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
						<select name="Site" class="form-control" id="Site" onChange="filtre();">
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
								type="search" placeholder="Le nom de la sortie contient" onkeyup="filtre();">
					</div>
				</div>
				
				<div class="row"> <!--====== dates =======-->
					<div class="row col">
						<div class="col-3">
							<label for="DateInf">Entre :</label>
						</div>
						
						<div class="col-9">
							<input id="DateInf" name="DateInf" class="form-control form-control-lg form-control-borderless" type="datetime-local" onChange="filtre();">
						</div>
					</div>
					
					<div class="row col">
						<div class="col-3">
							<label for="DateSup"> et :</label>
						</div>
						
						<div class="col-9">
							<input id="DateSup" name="DateSup" class="form-control form-control-lg form-control-borderless" type="datetime-local" onChange="filtre();">
						</div>
					</div>
				</div>
		
			</div>
			
			<!--===================-->
			<!--======DROITE=======-->
			<!--===================-->
			
			<div class="col-5 ml-5">
					<div class="col">
						<input class="form-check-input" type="checkbox" name="IsOrganisateur"  id="IsOrganisateur" onclick="checkboxExclisif();filtre();">
						<label for="IsOrganisateur"> Sorties dont je suis l'organisateur/trice</label>
					</div>
					
					<div class="col">
						<input class="form-check-input" type="checkbox" name="IsInscrit"  id="IsInscrit" onclick="checkboxExclisif();filtre();" checked="checked">
						<label for="IsInscrit"> Sorties auquelles je suis inscrit/e </label>
					</div>
					
					<div class="col">
						<input class="form-check-input" type="checkbox" name="IsNotInscrit"  id="IsNotInscrit" onclick="checkboxExclisif();filtre();" checked="checked">
						<label for="IsNotInscrit"> Sorties auquelles je suis ne suis pas inscrit/e</label>
					</div>
					
					<div class="col">
						<input class="form-check-input" type="checkbox" name="IsPassee"  id="IsPassee" onclick="filtre()">
						<label for="IsPassee"> Sorties passées</label>
					</div>
					<!--===================
					<div>
						<button class="mt-2" type="button" id="search" class="btn btn-outline-success" onclick="filtre()"> Rechercher </button>
					</div>
					===================-->
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
						<td> ${sortie.etat.name } </td>
						
						<c:set var="estInscrit" value="bad"/>
						<c:forEach items="${ sortie.participants }" var="participant">
							<c:if test='${estInscrit != "good" && participant.idParticipant == sessionScope.utilisateur.idParticipant }'>
								<c:set var="estInscrit" value="good"/>
							</c:if>
						</c:forEach>
						
						<td><c:if test='${estInscrit != null && estInscrit == "good" }'>X</c:if></td>
						
						<td><a href="/ENI_Sortie_Com/membre/profil?pseudo=${sortie.organisateur.pseudo }" class="btn btn-link">${sortie.organisateur.pseudo }</a></td>
						<td><a href="/ENI_Sortie_Com/membre/detailSortie?id=${sortie.id }" class="btn btn-link">Afficher</a> <br/>
							
							<!-- ============================= -->
							<!-- ===Merdier des affichages==== -->
							<!-- ============================= -->
							<form action="accueil" method="POST">
								<input type="hidden" name="idSortie" value="${sortie.id }" >
								
								<c:if test="${sortie.organisateur.pseudo != sessionScope.utilisateur.pseudo }"> <!-- est pas l'orga, pas cloturé, pas fermé, pas plus de place-->
									
									<c:if test='${estInscrit != null && estInscrit == "bad" }'> <!-- pas déja inscrit <a href="/ENI_Sortie_Com/membre/detailSortie?id=${sortie.id }">S'incrire</a> <br/> -->
										<input type="submit" name="btnAction" value="S'inscrire" class="btn btn-link"> <br/>
										
									</c:if>
									
									<c:if test='${estInscrit != null && estInscrit == "good" }'> <!-- déja inscrit <a href="/ENI_Sortie_Com/membre/detailSortie?id=${sortie.id }">Se désister</a> <br/> -->
										<input type="submit" name="btnAction" value="Se désister" class="btn btn-link"> <br/>
										
									</c:if>
									
								</c:if>
								
									
								<c:if test="${sortie.organisateur.pseudo == sessionScope.utilisateur.pseudo }"> <!-- est l'orga -->
									
										<c:if test='${sortie.etat.name == "Créée" }'>
											<a href="/ENI_Sortie_Com/membre/ModifierSortie?id=${sortie.id }" class="btn btn-link">Modifier</a> <br/>
											<input type="submit" name="btnAction" value="Publier" class="btn btn-link"> <br/>
										</c:if>
										
										<c:if test='${sortie.etat.name == "Ouverte" }'>
											<input type="submit" name="btnAction" value="Annuler" class="btn btn-link"> <br/>
										</c:if>
									
								</c:if>
							</form>
						</td>
						<td style="display: none;">${sessionScope.utilisateur.pseudo }</td>
						<td style="display: none;">${sortie.site.nom }</td>
						
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<a href="/ENI_Sortie_Com/membre/Sortie" class="btn btn-outline-info ml-5 mt-3" id="CreerSortie" name="CreerSortie"> 
			Créer une sortie
		</a>
	</div>
	
	<script type="text/javascript" src="../js/jquery/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/Accueil.js"></script>
	
</body>
</html>