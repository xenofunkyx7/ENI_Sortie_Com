<%@ page 	language="java" 	
			contentType="text/html; charset=UTF-8"
    		pageEncoding="UTF-8"
    		errorPage="/WEB-INF/erreur.jsp"
    		isErrorPage="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<html>
	<head>
		<meta charset="UTF-8">
		<title>ENI-Sortir</title>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="../css/styles.css">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
		
	</head>
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
					<input type="text" placeholder="Pseudonyme" name="identifiant" 
						value='<c:if test="${not empty sessionScope.identifiant}">${sessionScope.identifiant}</c:if>' 
						class="form-control form-control-lg form-control-borderless" >
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