function actorSelected(select){
	let index = select.selectedIndex;
	let option = select.options[index];
	let id = option.value;
	let nombre = option.text;
	let urlImagen = option.dataset.url;
	
	option.disable = "disabled";
	selec.selectedIndex = 0;
	agregarActor(id,nombre,urlImagen)
	let ids = $("#ids").val();
	
	if(ids == ""){
		$("#ids").val(id);
	}else {
		$("#ids").val(ids + "," + id);
	}
}


function agregarActor(id,nombre,urlImagen){
	let htmlString = `
		<div class="card col-md-3 m-2" style="width: 10rem">
		<img src ="{URL-IMAGEN}" class="card-img-top">
		<dov class="card-body">
		<p class="card-text" >{NOMBRE} </p>
		<buton class="btn btn-danger" data-id="{ID}" onclick="eliminarActor(this); return false;> Eliminar</button>
		</div>`;
		
		htmlString = htmlString.replace("{ID}",id);
		htmlString = htmlString.replace("{NOMBRE}",nombre);
		htmlString = htmlString.replace("{URL-IMAGEN}",urlImagen);
		
		$("#protagonistas_container").append(htmlString);
}


function eliminarActor(btn){
	let id = btn.dataset.id;
	let node = btn.parentnElment;
	let ArrayIds = $("#ids").val().split(",").filter(idActor => idActor != id);
	
	$("#id").val(arrayId.join(","));
	
	// 1,2,3,10 => [1,2,3,10] => [1,2,10]
	
	$("#protagonistas option[value='"+ id +"']").prop("disabled",false);
	
	$(node).remove();
}

function previsualizar(){
	let reader = new FileReader();

	reader.readAsDataURL(document.getElementById("imagen").files[0]);
	reader.onload = function (e){
	let vista = document.getElementById("vista_previa");
	vista.classList.remove("d-none");
	vista.style.backgroundImage =  'url("' +e.target.result+ '")';
	}
}