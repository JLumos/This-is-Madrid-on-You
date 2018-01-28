$(document).ready(function () {
	$("#updateForm").submit(function (event) {
		//stop submit the form, we will post it manually.
		event.preventDefault();
		fire_ajax_submit_update();
	});
});

function fire_ajax_submit_update() {

	var search = {}
	search["username"] = $("#usernameUpdate").val();
	search["password"] = $("#passwordUpdate").val();
	search["mail"] = $("#emailUpdate").val();
	search["update"] = new Boolean(true);


	$("#updateButton").prop("disabled", true);

	if (search["username"].length < 3 || search["password"].length < 3)
	{
		$("#updateButton").prop("disabled", false);
		$('.error').addClass('alert alert-danger').html("<b>Credenciales incorrectas</b> " +
				"<br> - El nombre de usuario debe tener más de 3 caracteres. " +
		"<br> - La contraseña debe tener mas de 3 caracteres");
		return (false)

	}

	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(search["mail"]))
	{
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/tryUpdate",
			data: JSON.stringify(search),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success: function (data) {

				if (data.status == "Update OK")
				{
					$('.error').addClass('alert alert-success').html("<center>Datos actualizados</center>");

				}
				else if (data.status == "Ya existe ese nombre de usuario")
				{
					$('.error').addClass('alert alert-danger').html("Ya existe ese nombre de usuario");
				}
				else if (data.status == "Error en los campos de entrada")
				{
					$('.error').addClass('alert alert-danger').html("Error en los campos de entrada");
				}
				//$('input[type="password"]').val('');

				$("#updateButton").prop("disabled", false);

			},
			error: function (e) {

				$('.error').addClass('alert alert-danger').html("Error en la peticion");
				//$('input[type="password"]').val('');
				$("#updateButton").prop("disabled", false);

			}
		});
	}
	else 
	{
		$("#updateButton").prop("disabled", false);
		$('.error').addClass('alert alert-danger').html("La direccion de correo no es válida.");
		return (false)
	}
}