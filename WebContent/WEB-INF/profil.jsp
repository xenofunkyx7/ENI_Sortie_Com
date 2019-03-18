<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<fieldset>
    <legend><h3>${membre.pseudo }</h3></legend>
    <section class="container">
    <div class="row">
        <div class="col-4">
            <img src="${membre.image }" class="img-rounded" alt="Cinque Terre">
        </div>
        <div class="col-8">
            <p>Prénom : ${membre.prenom }</p>
            <p>Nom : ${membre.nom }</p>
            <p>Téléphone : ${membre.telephone }</p>
            <p>E-mail : ${membre.mail }</p>
            <p>Ville de rattachement : ${membre.site.nom } </p>
        </div>
    </div>
    </section>
</fieldset>
</body>
</html>