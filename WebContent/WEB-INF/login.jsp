<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/entete.jsp"></jsp:include> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  


<body>

<div class="container">
	<div class="row">
		<div class="col-2 mt-5"> </div>
		
		<form class="container col-8 mt-5" method="post" action="login" >
			
			<div class="row mt-5">
				<div class="col-3">
					<label for="identifiant">Identifiant: </label>
				</div>
				<div class="col-9">
					<input type="text" placeholder="Pseudonyme" name="identifiant" value='<c:if test="${not empty sessionScope.identifiant}">${sessionScope.identifiant}</c:if>' class="form-control form-control-lg form-control-borderless" >
				</div>
			</div>
			
			<div class="row mt-2">
				<div class="col-3">
					<label for="password">Mot de passe:</label>
				</div>
				<div class="col-9">
					<input type="password" name="password" value='' class="form-control form-control-lg form-control-borderless" >
				</div>
			</div>
			
			<c:if test ="${sessionScope.erreur == true}">
				<div class="row mt-2">
					<div class="col-12">
				         <p class='text-danger'>Mot de passe/Identifiant Incorrect<p>
					</div>
				</div>
			</c:if>
			
			<div class="row mt-2">
				<div class="col-6">
					<input name='btnConnection' type="submit" class="btn btn-outline-info btn-lg" value="Connexion" /> 
				</div>
				
				<div class="col-6">
					<input type="checkbox" id="" name="seSouvenirDeMoi"
						<c:if test="${not empty sessionScope.identifiant}">
							checked="checked"
						</c:if>
					>
					<label for="rememberCheck">Se souvenir de moi</label><br/>
					<a href="" class=" text-primary " data-toggle="modal" data-target="#mdpLost">Mot de passe oublié</a>
				</div>
			</div>
			
		</form>
			
		<div class="col-2 mt-5"> </div>
	</div>
</div>
</body>