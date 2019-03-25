<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<jsp:include page="/WEB-INF/entete.jsp"></jsp:include>     


<form action="${pageContext.request.contextPath}/membre/monProfil" method="POST" class="was-validated">
	
    <fieldset>
        <legend>Mon profil</legend>
        <section class="container">
        <div class="row">
            <div class="col-4">
                <div>
                <img src="ici url base de donné" class="img-rounded" alt="Andre le Nain">
                </div>


            </div>

            <div class="col-8">
            
            <fieldset>
	            <legend>Informations Personnels</legend>
	            	<c:if test="${modification >= 1 }">
	            		<div class="alert alert-success">
						  <strong>Succès !</strong> Modification effectué !
						</div>
	            	</c:if>	            	
	                <div class="form-group row">
	                    <label for="pseudo" class="col-3">Pseudo :</label>
	                    <input type="text" class="form-control col" id="pseudo" value="${sessionScope.utilisateur.pseudo}" name="pseudo" required>
	                    <div class="valid-feedback">Pseudo valide</div>
	                    <div class="invalid-feedback">Veuillez saisir votre pseudo</div>
	                </div>
	                <div class="form-group row">
	                    <label for="prenom" class="col-3">Prenom :</label>
	                    <input type="text" class="form-control col" id="prenom" value="${sessionScope.utilisateur.prenom }" name="prenom" required>
	                    <div class="valid-feedback">Prenom valide</div>
	                    <div class="invalid-feedback">Veuillez saisir votre prenom</div>
	                </div>
	                <div class="form-group row">
	                    <label for="nom" class="col-3">Nom :</label>
	                    <input type="text" class="form-control col" id="nom" value="${sessionScope.utilisateur.nom}" name="nom" required>
	                    <div class="valid-feedback">Nom valide</div>
	                    <div class="invalid-feedback">Veuillez saisir votre Nom</div> 
	                </div>
	                <div class="form-group row">
	                    <label for="telephone" class="col-3">Telephone :</label>
	                    <input type="text" pattern="[0-9]{8,15}+" class="form-control col" id="telephone"  value="${sessionScope.utilisateur.telephone}" name="telephone" required>
	                    <div class="valid-feedback">telephone valide</div>
	                    <div class="invalid-feedback">Veuillez saisir votre telephone (que des chiffres)</div>
	                </div>
	                <div class="form-group row">
	                    <label for="email" class="col-3">Email address :</label>
	                    <input type="text" class="form-control col" id="email" value="${sessionScope.utilisateur.mail}" name="email" required>
	                    <div class="valid-feedback">e-mail valide</div>
	                    <div class="invalid-feedback">Veuillez saisir votre e-mail</div>
	                </div>
	                <div class="form-group row">
	                    <label for="site" class="col-3">Votre Site :</label>
	                    <select class="form-control col" id="site" name="site" disabled>                    	
	                    <option value="${sessionScope.utilisateur.site.nom}">${sessionScope.utilisateur.site.nom}</option>
                    </select>
                </div>
                </fieldset>
                <fieldset>
                	<legend>Changement de mot de passe</legend>
	                <div class="form-group row" >
	                    <label for="nmdp" class="col-3">Nouveau mot de passe :</label>
	                    <input type="password" class="form-control col" id="nmdp" placeholder="Enter mot de passe" name="nmdp" >
	                </div>
	                <div class="form-group  row">
	                    <label for="cmdp" class="col-3">Confirmation nouveau mot de passe :</label>
	                    <input type="password" class="form-control col" id="cmdp" placeholder="confirmer mot de passe" name="cmdp">
	                </div>
	                
                </fieldset>
                
				<fieldset>
                <legend>Confirmer modifications(s)</legend>
                <div class="form-group row">
                    <label for="mdpa" class="col-3">Mot de passe actuel :</label>
                    <input type="password" class="form-control col" id="nmdp" placeholder="Enter mot de passe" name="mdpa" required>
                    <div class="valid-feedback">Mot de passe valide</div>
                    <div class="invalid-feedback">Veuillez saisir votre mot de passe.</div>
                </div>
                <div class="row">
                
                <button type="submit" class="btn btn-primary mb-2">Modifier</button>
                <a href="${pageContext.request.contextPath}/membre/accueil">
                    <button type="button" class="btn btn-warning">Annuler</button></a>
                </div>
                </fieldset>
            </div>
        </div>
        </section>
    </fieldset>
</form>
</body>
</html>