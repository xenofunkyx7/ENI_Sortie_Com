<%@ page 	language="java" 	
			contentType="text/html; charset=UTF-8"
    		pageEncoding="UTF-8"
    		errorPage="/WEB-INF/erreur.jsp"
    		isErrorPage="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<jsp:include page="/WEB-INF/entete.jsp"></jsp:include> 

    <h2 class="text-align">Créer une une sortie</h2>

	
	<div class="text-align">
		<c:forEach items="${erreurs}" var="erreur" >
			<p class="text-danger">${erreur.value}</p>
		</c:forEach>
	</div>
    <c:choose>
    	<c:when test="${!empty requestScope.sortie}">
    		<form class="form-horizontal" action="${pageContext.request.contextPath}/membre/ModifierSortie/?id= ${sortie.id}" method="POST">
    	</c:when>
    	<c:otherwise>
    		<form class="form-horizontal" action="#" method="POST">
    	</c:otherwise>
    </c:choose>
    
        <div class="d-flex p-2">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <label class="control-label" for="idNom">Nom de la sortie :</label>
                    </div>
                    <div class="col">
                        <input type="text" id="idNom" name="nom" class="form-control" value="${sortie.nom}" required >
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="idDateHeureDebut">Date et heure de la sortie :</label>
                    </div>
                    <div class="col">
                        <input type="datetime-local" class="form-control" id="idDateHeureDebut" name="dateHeureDebut" value="${sortie.dateHeureDebut}" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="idDatetime-local" >Date limite d'inscription :</label>
                    </div>
                    <div class="col">
                        <input type="datetime-local" class="form-control" id="idDatetime-local" name="dateLimiteInscription" value="${sortie.dateLimiteInscription}" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="idNbInscriptionMax" >Nombre de places :</label>
                    </div>
                    <div class="col">
                        <input type="text" id="idNbInscriptionMax" name="nbInscriptionMax" class="form-control" value="${sortie.nbInscriptionMax}" required >
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="idDuree" >Durée :</label>
                    </div>
                    <div class="col">
                        <input type="number" id="idDuree" name="duree" class="form-control"  step="1" value="${sortie.duree}" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="idInfoSortie" >Description et infos :</label>
                    </div>
                    <div class="col">
                        <textarea id="idInfoSortie" name="infoSortie" class="form-control"  required>${sortie.infoSortie}</textarea>
                    </div>
                </div>
            </div>

            <div class="container">
                <div class="row">
                    <div class="col">
                        <p>Ville organisatrice :</p>
                    </div>
                    <div class="col">
                        <p>${sessionScope.utilisateur.site.nom}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="idVille" >Ville :</label>
                    </div>
                    <!-- ============================================== -->
                    <!-- ================combobox villes=============== -->
                    <!-- ============================================== -->
                     <select class="form-control" id="idVille" name="idVille" >
                     	<c:forEach var="ville" items="${villes}">
                     		<c:choose>
                     			<c:when test="${sortie != null && ville.idVille == sortie.lieu.ville.idVille} " >
                     				<option value="${ville.idVille}" selected="selected">${ville.nom}</option>
                     			</c:when>
                     		
	                     		<c:otherwise>
	                     			<option value="${ville.idVille}">${ville.nom}</option>
	                  			</c:otherwise>
                  			</c:choose>
                     	</c:forEach>
                     </select>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="idLieu" >Lieu :</label>
                    </div>
                    <div class="col">
                        <select class="form-control" id="idLieu" name="idLieu"   >
                            <c:forEach var="lieu" items="${lieux}">
                                <option value="${lieu.id}">${lieu.nom}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <p>Rue :</p>
                    </div>
                    <div class="col">
                        <p>PLACEHOLDER</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <p>Code postal :</p>
                    </div>
                    <div class="col">
                        <p>PLACEHOLDER</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <p>Latitude :</p>
                    </div>
                    <div class="col">
                        <p>PLACEHOLDER</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <p>Longitude : </p>
                    </div>
                    <div class="col">
                        <p>PLACEHOLDER</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <c:choose>
                <c:when test="${!empty requestScope.sortie}">
                    <div class="row">
                        <button type="submit" class=" form-control col-3" value="enregistrer" name="etat">Enregistrer</button>
                        <button type="submit" class="form-control col-3" value="publier" name="etat">Publier la sortie</button>
                        <a class="form-control  col-3" href="${pageContext.request.contextPath}/membre/annulerSortie?id=${sortie.id}">Supprimer la sortie</a>
                        <a class="form-control  col-3" href="${pageContext.request.contextPath}/membre/accueil">Annuler</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="row">
                        <button type="submit" class=" form-control col-4" value="enregistrer" name="etat">Enregistrer</button>
                        <button type="submit" class="form-control col-4" value="publier" name="etat">Publier la sortie</button>
                        <a class="form-control  col-4" href="${pageContext.request.contextPath}/membre/accueil">Annuler</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </form>
    </body>
</html>