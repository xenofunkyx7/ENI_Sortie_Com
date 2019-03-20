<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<jsp:include page="/WEB-INF/entete.jsp"></jsp:include>     


<form action="${pageContext.request.contextPath}/monProfil" method="POST" class="was-validated">
    <fieldset>
        <legend><h3>Mon profil</h3></legend>
        <section class="container">
        <div class="row">
            <div class="col-4">
                <div>
                <img src="ici url base de donn�" class="img-rounded" alt="Andre le Nain">
                </div>


            </div>

            <div class="col-8">
                <div class="form-group">
                    <label for="pseudo">Pseudo :</label>
                    <input type="text" class="form-control" id="pseudo" value="${sessionScope.utilisateur.pseudo}" name="pseudo" required>
                    <div class="valid-feedback">Pseudo valide</div>
                    <div class="invalid-feedback">Veuillez saisir votre pseudo</div>
                </div>
                <div class="form-group">
                    <label for="prenom">Prenom :</label>
                    <input type="text" class="form-control" id="prenom" value="${sessionScope.utilisateur.prenom }" name="prenom" required>
                    <div class="valid-feedback">Prenom valide</div>
                    <div class="invalid-feedback">Veuillez saisir votre prenom</div>
                </div>
                <div class="form-group">
                    <label for="nom">Nom :</label>
                    <input type="text" class="form-control" id="nom" value="${sessionScope.utilisateur.nom}" name="nom" required>
                    <div class="valid-feedback">Nom valide</div>
                    <div class="invalid-feedback">Veuillez saisir votre Nom</div>
                </div>
                <div class="form-group">
                    <label for="telephone">Telephone :</label>
                    <input type="text" class="form-control" id="telephone" value="${sessionScope.utilisateur.telephone}" name="telephone" required>
                    <div class="valid-feedback">telephone valide</div>
                    <div class="invalid-feedback">Veuillez saisir votre telephone</div>
                </div>
                <div class="form-group">
                    <label for="email">Email address :</label>
                    <input type="text" class="form-control" id="email" value="${sessionScope.utilisateur.mail}" name="email" required>
                    <div class="valid-feedback">e-mail valide</div>
                    <div class="invalid-feedback">Veuillez saisir votre e-mail</div>
                </div>
                <div class="form-group">
                    <label for="mdp">Mot de passe :</label>
                    <input type="password" class="form-control" id="mdp" placeholder="Enter mot de passe" name="mdp" required>
                    <div class="valid-feedback">Mot de passe valide</div>
                    <div class="invalid-feedback">Veuillez saisir un mot de passe .</div>
                </div>
                <div class="form-group">
                    <label for="cmdp">Confirmation Mot de passe :</label>
                    <input type="password" class="form-control" id="cmdp" placeholder="confirmer mot de passe" name="cmdp" required>
                    <div class="valid-feedback">Mot de passe valide</div>
                    <div class="invalid-feedback">Veuillez saisir le m�me mot de passe .</div>
                </div>
                <div class="form-group">
                    <label for="site">Votre Site :</label>
                    <select class="form-control" id="site" name="site">
                    	<c:forEach var="site" items="${sites}">
                        <option value="${site.idSite}">${site.nom}</option>
                       	</c:forEach>
                    </select>
                </div>
                <div class="row">
                <button type="submit" class="btn btn-primary mb-2">Modifier</button>
                <a href="http://localhost:8080/ENI_Sortie_Com/membre/accueil">
                    <button type="button" class="btn btn-warning">Annuler</button></a>
                </div>
            </div>
        </div>
        </section>
    </fieldset>
</form>
</body>
</html>