

function filtre() {

	  var inputSearch, filtre, table, tr, td, isGood, txtValue;
	  
	  inputSearch = document.getElementById("search");
	  filtre = inputSearch.value.toUpperCase();
	  
	  table = document.getElementById("Table");
	  tr = table.getElementsByTagName("tr");

	  for (i = 0; i < tr.length; i++) {
		
		  // Filtre
		  
		  td = tr[i].getElementsByTagName("td")[0];
		  
		  if (td) {
			  	txtValue = td.textContent || td.innerText;
			  		isGood = txtValue.toUpperCase().indexOf(filtre) > -1;
		  }else {
			  isGood = false;
		  }
	    
		  
		  // PURGE DE L'HÉRÉSIE !
		  if (isGood) {
			  tr[i].style.display = "";
		  } else {
			  tr[i].style.display = "none";
		  }
	    
	  }
	}
