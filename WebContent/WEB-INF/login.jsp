<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/entete.jsp"></jsp:include> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<body class="bodyConnect">
	<div class="containerLogin">
		<form class="formConnect" method="post" action="seConnecter" >
			<div class="input-group mb-3">
				<div class="input-group-prepend">
	    			<span id="login" class="input-group-text">Identifiant :</span>
	  			</div>
				<input class="id form-control" id='txtLogin' name='identifiant' type="text" placeholder="Pseudo/Email" value='' />
			</div>
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">Mot de passe :</span>
					<input class="pwd form-control" id='pwd' name='password' type="password" value=''/>
				</div>
			</div>
			<br/>
			<c:if test ="${requestScope.erreur == true}">
	         	<p class='text-danger'>Mot de passe/Identifiant Incorrect<p>
	     	</c:if>
			<div class="container1">
				<input name='btnConnection' type="submit" class="left btn btn-outline-info btn-lg" value="Connexion" /> 
				<br/>
				<div class=' ml-4'>
									<c:if test="${not empty identifiant}">
					<input class="right" type="checkbox" id="" name="seSouvenirDeMoi" checked="checked">
					<label class="right " for="rememberCheck">Se souvenir de moi</label><br/>
				</c:if>
				<c:if test="${identifiant == null}">
					<input class="right" type="checkbox" id="" name="seSouvenirDeMoi">
					<label class="right " for="rememberCheck">Se souvenir de moi</label><br/>
				</c:if>
				
					<a href="" class=" text-primary " data-toggle="modal" data-target="#mdpLost">Mot de passe oublié</a>
				</div>
			</div>
			<br/><br/>	
		</form>
	</div>
<!-- Modal -->
		<form class="formConnect" method="post" action="reinitialisation" >

	<div class="modal fade" id="mdpLost" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
	    	<div class="modal-content">
	      		<div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">Réinitialisation du mot de passe</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
		      	</div>
		      	
			    <div class="modal-body">
			    <span>Envoyez "MPD LOST" au 666 pour récupérer votre mot de passe, SMS surtaxé 700€/SMS</span>
			    <br/>
			    <span>Pour les plus démunis, entrez simplement votre adresse ci dessous : </span>
			    
			    	<div class="input-group-prepend">
		    			<span id="login" class="input-group-text">Entrez votre email :</span>
				  	</div>
					<input class="id form-control" id='txtLogin' name='identifiant' type="text" placeholder="@" value='' />
				</div>
	      </div>
	      	<div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Quitter</button>
		        <a href="reinitialisation"><button type="button" class="btn btn-primary">Envoyer</button></a>
			</div>
			
	    </div>
	    
	</div>
	</form>			
</body>