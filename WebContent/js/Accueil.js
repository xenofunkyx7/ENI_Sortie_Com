/**
 * 
 */

// "main"
$(document).ready(function(e) {
	filtre();
    
    }
);

function checkboxExclisif() {
	var ckOrga, ckInscrit, ckNotInscrit;
	
	ckOrga = document.getElementById("IsOrganisateur");
	ckInscrit = document.getElementById("IsInscrit");
	ckNotInscrit = document.getElementById("IsNotInscrit");
	
	// TODO griser les valeurs non valable et afficher message d'erreur
	
	 if ( ckOrga.checked ){
		 ckInscrit.disabled = true;
		 ckNotInscrit.disabled = true;
	 }else{
		 ckInscrit.disabled = false;
		 ckNotInscrit.disabled = false;
		 
		 if (!ckInscrit.checked && !ckNotInscrit.checked){
			 console.log("erreur à afficher TODO");
		 }
	 }
	
}


function filtre() {
	  var inputSortie, filtreSortie, table, tr, td, i, txtValue, selectSite, 
	  		dateDebut, cloture, ckOrga, ckInscrit, ckNotInscrit, ckPassed, 
	  		isSortieGood, isSiteGood, isDateInfGood, isDateSupGood, isOrgaGood, 
	  		isInscritGood, isNotInscritGood, isPassedGood;
	  
	  // index dans le tableau
	  var iNom = 0;
	  var iDateDebut = 1;
	  var iCloture = 2;
	  var iX = 5;
	  var iOrganisateur = 6;
	  var iUtilisateur = 8;
	  var iSite = 9;
	  
	  
	  inputSortie = document.getElementById("Sortie");
	  selectSite = document.getElementById("Site");
	  
	  dateDebut = document.getElementById("DateInf");
	  cloture = document.getElementById("DateSup");
	  
	  ckOrga = document.getElementById("IsOrganisateur");
	  ckInscrit = document.getElementById("IsInscrit");
	  ckNotInscrit = document.getElementById("IsNotInscrit");
	  ckPassed = document.getElementById("IsPassee");
	  
	  filtreSortie = inputSortie.value.toUpperCase();
	  
	  
	  table = document.getElementById("TableSortie");
	  tr = table.getElementsByTagName("tr");

	  // boucle pour cacher ceux qui sont pas bon
	  
	  for (i = 0; i < tr.length; i++) {
		
		  // Filtre Sortie
		  
		  td = tr[i].getElementsByTagName("td")[iNom];
		  if (td) {
			  	txtValue = td.textContent || td.innerText;
			  		isSortieGood = txtValue.toUpperCase().indexOf(filtreSortie) > -1;
		  }else {
			  	isSortieGood = false;
		  }
	    
		  // comboBox Site
	    
		  td = tr[i].getElementsByTagName("td")[iSite];
	    
		  if (td) {
			  	txtValue = td.textContent || td.innerText;
			  		isSiteGood = selectSite.value == txtValue;
		  }else {
			  	isSiteGood = false;
		  }
		  
		  // Je suis l'organiasateur
		  
		  if ( ckOrga.checked ){
			  isInscritGood = true;
			  isNotInscritGood = true;
			  
			  td = tr[i].getElementsByTagName("td")[iOrganisateur];
			  var user = tr[i].getElementsByTagName("td")[iUtilisateur];
			  
			  if (td) {
				  txtValue = td.textContent || td.innerText;
				  var txtValue2 = user.textContent || user.innerText;
				  isOrgaGood = txtValue2 == txtValue;
			  }else {
				  isOrgaGood = false;
			  }
			  
			  
		  }else {
			  isOrgaGood = true;
			  td = tr[i].getElementsByTagName("td")[iX];
			  
			  if (td && !(ckInscrit.checked && ckNotInscrit.checked) ){
				  txtValue = td.textContent || td.innerText;
				  
				  var doubleUncheck = !ckInscrit.checked && !ckNotInscrit.checked;
				  
				  if (doubleUncheck){
					  isInscritGood = false;
					  isNotInscritGood = false;
				  }else {
					  // je suis incesruit
					  
					  isInscritGood = ckInscrit.checked && txtValue == "" || !ckInscrit.checked;
					  
					  // je suis pas incesruit ckInscrit, ckNotInscrit
					  
					  isNotInscritGood = ckNotInscrit.checked && txtValue == "X" || !ckNotInscrit.checked;
				  }
			  }else {
				  isInscritGood = true;
				  isNotInscritGood = true;
			  }
		  }
		  
		  // DATES :O
		  var maintenant = Date.now();
		  
	      // dateDebut
		  
		  td = tr[i].getElementsByTagName("td")[iDateDebut];
		  
		  if (td){
			  txtValue = td.textContent || td.innerText;
			  var date = new Date(txtValue) - 0;
			  txtValue2 = dateDebut.value;
				 
			  if(txtValue2 && txtValue2 != ""){
				  var dateDebutValue = new Date(dateDebut.value) - 0; // le -0 est pour mettre en ms
				  
				  isDateInfGood = date < dateDebutValue;
				  
			  }else{
				  isDateInfGood = true;
			  }
			  
		  }else{
			  isDateInfGood = true;
		  }
		  
		  // dateFin
		  
		  td = tr[i].getElementsByTagName("td")[iCloture];
		  
		  if (td){
			  txtValue = td.textContent || td.innerText;
			  var date2 = new Date(txtValue) - 0;
			  txtValue2 = cloture.value;
				 
			  if(txtValue2 && txtValue2 != ""){
				  var clotureValue = new Date(cloture.value) - 0; // le -0 est pour mettre en ms
				  
				  isDateSupGood = date2 > clotureValue;
				  
				  
			  }else{
				  isDateSupGood = true;
			  }
			  
			  // passée
			  
			  if(ckPassed.checked){
				  isPassedGood = maintenant > date2;
			  }else{
				  isPassedGood = true;
			  }
			  
		  }else{
			  isDateSupGood = true;
			  isPassedGood = true;
		  }
		  
		  // PURGE DE L'HÉRÉSIE !
		  if (isSortieGood && isSiteGood && isOrgaGood && isInscritGood && isNotInscritGood 
				  && isDateInfGood && isDateSupGood && isPassedGood) {
			  tr[i].style.display = "";
		  } else {
			  tr[i].style.display = "none";
		  }
	    
	  }
	}