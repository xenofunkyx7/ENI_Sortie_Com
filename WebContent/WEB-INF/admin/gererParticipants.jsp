<%@ page 	language="java" 	
			contentType="text/html; charset=UTF-8"
    		pageEncoding="UTF-8"
    		errorPage="/WEB-INF/erreur.jsp"
    		isErrorPage="false"
%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<jsp:include page="/WEB-INF/entete.jsp"></jsp:include> 
	
	<div class="row col-6"> <!--====== search =======-->
		<div class="col-2">
			<label for="Ville">Ville :</label>
		</div>
		
		<div class="col-10">
			<input id="search" name="search" class="form-control form-control-lg form-control-borderless" 
					type="search" placeholder="Le pseudo du participant contient" onkeyup="filtre();">
		</div>
	</div>
	
	<div class="row overflow-auto mx-5 mt-5">
		<table class="table table-striped table-hover table-sm ">
			<thead class="thead-dark">
				<tr>
					<th scope="col"> Pseudo </th>
					<th scope="col"> Nom </th>
					<th scope="col"> Prénom </th>
					<th scope="col"> Téléphone </th>
					<th scope="col"> Mail </th>
					<th scope="col"> Est administrateur ?</th>
					<th scope="col"> Est actif ?</th>
					<th scope="col"> Site </th>
					<th scope="col"> Actions </th>
				</tr>
			</thead>
			<tbody id="Table">
			
				<c:forEach items="${ sessionScope.participants }" var="participant">
					<tr>
						<td>${participant.pseudo }</td>
						<td>${participant.nom }</td>
						<td>${participant.prenom }</td>
						<td>${participant.telephone }</td>
						<td>${participant.mail }</td>
						<td>${participant.administrateur }</td>
						<td>${participant.actif }</td>
						<td>${participant.site.nom }</td>
						<td>
							<form action="#" method="POST">
								<a href="/ENI_Sortie_Com/admin/modifierParticipant?id=${participant.idParticipant }" 
									class="btn btn-link">Modifier</a>
								<input type="submit" name="btn" class="btn btn-link" value="Supprimer">
								<input type="hidden" name="id" value="${participant.idParticipant }">
							</form> 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<form action="#" method="POST">
			<input type="text" name="pseudo" placeholder="pseudo">
			<input type="text" name="nom" placeholder="nom">
			<input type="text" name="prenom" placeholder="prenom">
			<input type="text" name="telephone" placeholder="telephone">
			<input type="text" name="mail" placeholder="mail">
			<input type="text" name="mdp" placeholder="mot de passe">
			<select name="site" class="form-control" id="Site">
				<c:forEach items="${ sessionScope.sites }" var="site">
				    <option value="${site.idSite }">${site.nom }</option>
				</c:forEach>
			</select>
				
			<input type="submit" name="btn" class="btn btn-link" value="Ajouter">
		</form> 
					
	</div>
	
		
	<script type="text/javascript" src="../js/jquery/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/Tri.js"></script>
</body>
</html>