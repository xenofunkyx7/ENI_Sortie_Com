<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/erreur.jsp" isErrorPage="true"%>
<jsp:include page="/WEB-INF/entete.jsp"></jsp:include> 
<section class="container">
<section class="row">
<h1>Traitement des erreurs</h1>
<h3>Veuillez contacter votre support informatique et communiquer les informations suivantes :</h3>
<h4>Je suis un utilisateur et j'ai tout cassé.</h4>
</section>
<section class="row">
<%=exception.getMessage()%>
</section>
</section>
</body>
</html>