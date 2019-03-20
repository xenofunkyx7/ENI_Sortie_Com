<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<jsp:include page="/WEB-INF/entete.jsp"></jsp:include> 
	
	
	<div class="row ml-5">
	
		<!--===================-->
		<!--======Filtres======-->
		<!--===================-->
		<h3 class="ml-5">Filtrer les sorties</h3>
		
		<div class="row">
			<!--===================-->
			<!--======GAUCHE=======-->
			<!--===================-->
			<div class="col-6">
				<div class="row"> <!--======select site=======-->
					<div class="col-2">
						<label for="Site">Site :</label>
					</div>
					
					<div class="col-10">
						<select name="select" class="form-control" id="selectCategorie">
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
						<input name="Sortie" class="form-control form-control-lg form-control-borderless" 
								type="search" placeholder="Le nom de la sortie contient">
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
			
			<div class="col-6">
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
					
			</div>
		</div>
	</div>
	
	
</body>
</html>