<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ENI-Sortir</title>
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="css/styles.css">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
		
	</head>
	<body>
		<nav class="navbar navbar-expand-sm bg-light navbar-light justify-content-end">
		    <a class="navbar-brand" href="#">
		    	<img class="img-logo" src="image/logoExit.png" width="100px" height="100px">
		    </a>
		    <div class="ml-auto"></div>
		    <div class="collapse navbar-collapse flex-grow-0" id="navbarSupportedContent">
		        <ul class="navbar-nav text-right">
		        
		        	<jsp:include page="/WEB-INF/admin/enteteAdmin.jsp"></jsp:include>
		        
		        	<li class="nav-item active">
		                <a class="nav-link" href="membre/accueil">Acceuil</a>
		            </li>
		            <li class="nav-item active">
		                <a class="nav-link" href="membre/monProfil">Mon profil</a>
		            </li>
		            <li class="nav-item active">
		                <a class="nav-link" href="deconnexion">Se déconnecter</a>
		            </li>
		        </ul>
		    </div>
		</nav>