<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<jsp:include page="/WEB-INF/entete.jsp"></jsp:include> 

    <h2 class="text-align">Créer une une sortie</h2>

    <form class="form-horizontal" action="#" method="POST">

        <div class="d-flex p-2">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <label class="control-label" for="idNom">Nom de la sortie :</label>
                    </div>
                    <div class="col">
                        <input type="text" id="idNom" name="nom" class="form-control" required >
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="idDateHeureDebut">Date et heure de la sortie :</label>
                    </div>
                    <div class="col">
                        <input type="datetime-local" class="form-control" id="idDateHeureDebut" name="dateHeureDebut" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="idDatetime-local" >Date limite d'inscription :</label>
                    </div>
                    <div class="col">
                        <input type="datetime-local" class="form-control" id="idDatetime-local" name="dateLimiteInscription" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="idNbInscriptionMax" >Nombre de places :</label>
                    </div>
                    <div class="col">
                        <input type="text" id="idNbInscriptionMax" name="nbInscriptionMax" class="form-control" required >
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="idDuree" >Durée :</label>
                    </div>
                    <div class="col">
                        <input type="number" id="idDuree" name="duree" class="form-control"  step="1" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="idInfoSortie" >Description et infos :</label>
                    </div>
                    <div class="col">
                        <textarea id="idInfoSortie" name="infoSortie" class="form-control" required> test</textarea>
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
                     <select class="form-control" id="idVille">
                     	<c:forEach var="ville" items="${villes}">
                        	<option value="${ville.idVille}">${ville.nom}</option>
                     	</c:forEach>
                     </select>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="idLieu" >Lieu :</label>
                    </div>
                    <div class="col">
                        <select class="form-control" id="idLieu" onchange="myFunction()" >
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
                <c:when test="${!empty id}">
                    <div class="row">
                        <button type="submit" class=" form-control col-3">Enregistrer</button>
                        <button type="submit" class="form-control col-3">Publier la sortie</button>
                        <a class="form-control  col-3" href="#">Supprimer la sortie</a>
                        <a class="form-control  col-3" href="#">Annuler</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="row">
                        <button type="submit" class=" form-control col-4">Enregistrer</button>
                        <button type="submit" class="form-control col-4">Publier la sortie</button>
                        <a class="form-control  col-4" href="#">Annuler</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </form>
    </body>
</html>