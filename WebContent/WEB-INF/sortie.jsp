<jsp:include page="/WEB-INF/entete.jsp"></jsp:include> 

	<h1>Logo Sortir.com</h1>
	<h2>Créer une sortie</h2>
	<form action="#" method="POST">
		<div class="container">
			<div class="row">
				<div class="col-3 ">
					<p>Nom de la sortie : </p>
					<p>Date et heure de la sortie : </p>
					<p>Date limite d'inscription : </p>
					<p>Nombre de places : </p>
					<p>Durée : </p>
					<p>Description et infos : </p>
				</div>
				<div class="col-3">
					<input type="text" id="" class="form-control" required >
					<input class="date form-control" name="" required>        
					//date2
					<input type="text" id="" class="form-control" required >
					<input type="number" step="1">
					<textarea id="infoSortie" name="infoSortie" class="form-control" required> test</textarea>
				</div>
				<div class="col-3 ">
					<p>Ville organisatrice : </p>
					<p>Lieu : </p>
					<p>Rue : </p>
					<p>Code Postal : </p>
					<p>Latitude : </p>
					<p>Loongitude : </p>
				</div>
				<div class="col-3">
					
				</div>		
			</div>
		</div>
	</form>
	<script type="text/javascript">
	    $('.date').datepicker({
	       format: 'yyyy-mm-dd'
	     });
	</script>
</body>
</html> 