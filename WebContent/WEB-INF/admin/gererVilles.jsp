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
					type="search" placeholder="Le nom de la ville contient" onkeyup="filtre();">
		</div>
	</div>
	
	<div class="row overflow-auto mx-5 mt-5">
		<table class="table table-striped table-hover table-sm ">
			<thead class="thead-dark">
				<tr>
					<th scope="col"> Ville </th>
					<th scope="col"> Code postal </th>
					<th scope="col"> Actions </th>
				</tr>
			</thead>
			<tbody id="Table">
			
				<c:forEach items="${ sessionScope.villes }" var="ville">
					<tr>
						<td>${ville.nom }</td>
						<td>${ville.codePostal }</td>
						<td>
							<form action="#" method="POST">
								<input type="text" name="nom" placeholder="nouveau nom">
								<input type="text" name="codePostal" placeholder="nouveau code postal">
								<input type="submit" name="btn" class="btn btn-link" value="Modifier">
								
								<input type="submit" name="btn" class="btn btn-link" value="Supprimer">
								<input type="hidden" name="id" value="${ville.idVille }">
							</form> 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<form action="#" method="POST">
			<input type="text" name="nom" placeholder="nom">
			<input type="text" name="codePostal" placeholder="code postal"> 	
			<input type="submit" name="btn" class="btn btn-link" value="Ajouter">
		</form> 
					
	</div>
	
		
	<script type="text/javascript" src="../js/jquery/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/Tri.js"></script>
</body>
</html>