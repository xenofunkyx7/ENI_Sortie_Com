/**
 * 
 */

// "main"
$(document).ready(function(e) {
	filtre();
    
    }
);


function filtre() {
	  var inputSortie, filtreSortie, table, tr, td, i, txtValue, selectSite;
	  
	  
	  
	  inputSortie = document.getElementById("Sortie");
	  selectSite = document.getElementById("Site");
	  
	  console.log(selectSite.value);
	  
	  
	  filtreSortie = inputSortie.value.toUpperCase();
	  
	  
	  table = document.getElementById("TableSortie");
	  tr = table.getElementsByTagName("tr");

	  // boucle pour cacher ceux qui sont pas bon
	  
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[0];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      
	      if (txtValue.toUpperCase().indexOf(filtreSortie) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    } 
	  }
	}