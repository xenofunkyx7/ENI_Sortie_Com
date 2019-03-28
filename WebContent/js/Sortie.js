/**
 * 
 */
var lieux = {};
var lieuxNonTrie = {};


$(document).ready(function(e) {
	var selectLieux = document.getElementById("idLieu");
	
	var options = selectLieux.getElementsByTagName("option");
	
	for (i = 0; i < options.length; i++) {
		
		var id = options[i].id.slice(11);
		
		var idLieu = options[i].value;
		
		var inner = options[i].textContent || options[i].innerText;
		var split = inner.split("®ªšÐ");

		var nomLieu = split[0];
		var rue = split[1];
		var codePostal = split[2];
		var latitude = split[3];
		var longitude = split[4];
		
		
		var lieu = {};
		lieu.id = idLieu;
		lieu.nom = nomLieu;
		lieu.rue = rue;
		lieu.codePostal = codePostal;
		lieu.latitude = latitude;
		lieu.longitude = longitude;
		
		if (! lieux[id]){
			lieux[id] = new Array();
		}
		lieux[id].push(lieu);
		lieuxNonTrie[idLieu] = lieu;
	}
	select();
    }
);

function select(){
	
	var selectSites = document.getElementById("idVille");
	var selectLieux = document.getElementById("idLieu");
	
	while (selectLieux.firstChild) {
		selectLieux.removeChild(selectLieux.firstChild);
	}
	
	var idVille = selectSites.options[selectSites.selectedIndex].id;
	
	for (i = 0; i < lieux[idVille].length; i++) {
		var idLieu = lieux[idVille][i].id;
		var nomLieu = lieux[idVille][i].nom;
		
		var option = document.createElement('option');
		
		option.id = "idVilleLieu" + idVille;
		option.value = idLieu;
		option.innerText = nomLieu;
		
		selectLieux.appendChild(option);
		
	}
	selectLieux.selectedIndex = 0;
	setText();
}


function setText(){
	var txtRue = document.getElementById("txtRue");
	var txtCodePostal = document.getElementById("txtCodePostal");
	var txtLatitude = document.getElementById("txtLatitude");
	var txtLongitude = document.getElementById("txtLongitude");
	
	var selectLieux = document.getElementById("idLieu");
	var selectSites = document.getElementById("idVille");
	
	var idVille = selectSites.options[selectSites.selectedIndex].id;
	var idlieu = selectLieux.options[selectLieux.selectedIndex].value;
	
	var lieu = lieuxNonTrie[idlieu];
	
	
	txtRue.innerText = lieu.rue;
	txtCodePostal.innerText = lieu.codePostal;
	txtLatitude.innerText = lieu.latitude;
	txtLongitude.innerText = lieu.longitude;
	
}