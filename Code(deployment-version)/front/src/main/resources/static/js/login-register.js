/*
 *
 * login-register modal
 * Autor: Creative Tim
 * Web-autor: creative.tim
 * Web script: http://creative-tim.com
 * 
 */
function showRegisterForm(){
	$('.loginBox').fadeOut('fast',function(){
		$('.registerBox').fadeIn('fast');
		$('.login-footer').fadeOut('fast',function(){
			$('.register-footer').fadeIn('fast');
		});
		$('.modal-title').html('Crear cuenta');
	}); 
	$('.error').removeClass('alert alert-danger').html('');

}
function showLoginForm(){
	$('#loginModal .registerBox').fadeOut('fast',function(){
		$('.loginBox').fadeIn('fast');
		$('.register-footer').fadeOut('fast',function(){
			$('.login-footer').fadeIn('fast');    
		});

		$('.modal-title').html('Iniciar sesion');
	});       
	$('.error').removeClass('alert alert-danger').html(''); 
}

function openLoginModal(){
	showLoginForm();
	setTimeout(function(){
		$('#loginModal').modal('show');    
	}, 230);

}
function openRegisterModal(){
	showRegisterForm();
	setTimeout(function(){
		$('#loginModal').modal('show');    
	}, 230);

}

function loginAjax(){}

function shakeModal(){
	$('#loginModal .modal-dialog').addClass('shake');
}

$(document).ready(function () {
	$("#loginForm").submit(function (event) {
		//stop submit the form, we will post it manually.
		event.preventDefault();
		fire_ajax_submit_login();
	});
});

$(document).ready(function () {
	$("#registerForm").submit(function (event) {
		//stop submit the form, we will post it manually.
		event.preventDefault();
		fire_ajax_submit_register();
	});
});

function fire_ajax_submit_login() {

	var search = {}
	search["username"] = $("#emailLogin").val();
	search["password"] = $("#passwordLogin").val();

	$("#loginButton").prop("disabled", true);

	if (search["username"].length > 3 && search["password"].length > 3)
	{
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/tryLogin",
			data: JSON.stringify(search),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success: function (data) {

				if (data.status == "Login OK")
				{
					//$('.error').addClass('alert alert-success').html("Credenciales correctas");
					window.location.href = "http://localhost:8080/users/" + search["username"] + "/home";

				}
				else
				{
					$('.error').addClass('alert alert-danger').html("Nombre de usuario o contraseña incorrectos");
					shakeModal();
				}
				//$('input[type="password"]').val('');
				setTimeout( function(){ 
					$('#loginModal .modal-dialog').removeClass('shake'); 
				}, 1000 ); 
				$("#loginButton").prop("disabled", false);

			},
			error: function (e) {

				$('.error').addClass('alert alert-danger').html("Error en la peticion");
				//$('input[type="password"]').val('');
				setTimeout( function(){ 
					$('#loginModal .modal-dialog').removeClass('shake'); 
				}, 1000 ); 
				$("#loginButton").prop("disabled", false);

			}
		});
	}
	else 
	{
		$("#loginButton").prop("disabled", false);
		shakeModal();
		$('.error').addClass('alert alert-danger').html("<b>Credenciales incorrectas</b> " +
				"<br> - El nombre de usuario debe tener más de 3 caracteres. " +
		"<br> - La contraseña debe tener mas de 3 caracteres");
		return (false)
	}
}

function fire_ajax_submit_register() {

	var search = {}
	search["username"] = $("#usernameRegister").val();
	search["password"] = $("#passwordRegister").val();
	search["mail"] = $("#emailRegister").val();
	search["update"] = false;

	$("#registerButton").prop("disabled", true);

	if (search["username"].length < 3 || search["password"].length < 3)
	{
		$("#registerButton").prop("disabled", false);
		shakeModal();
		$('.error').addClass('alert alert-danger').html("<b>Credenciales incorrectas</b> " +
				"<br> - El nombre de usuario debe tener más de 3 caracteres. " +
		"<br> - La contraseña debe tener mas de 3 caracteres");
		return (false)

	}

	if (search["password"] != $("#password_confirmationRegister").val())
	{
		$("#registerButton").prop("disabled", false);
		shakeModal();
		$('.error').addClass('alert alert-danger').html("Las contraseñas no coinciden");
		return (false)
	}
	
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(search["mail"]))
	{
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/tryRegister",
			data: JSON.stringify(search),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success: function (data) {

				if (data.status == "Register OK")
				{
					//$('.error').addClass('alert alert-success').html("Credenciales correctas");
					window.location.href = "http://localhost:8080/users/" + search["username"] + "/home";

				}
				else if (data.status == "Ya existe ese nombre de usuario")
				{
					$('.error').addClass('alert alert-danger').html("Ya existe ese nombre de usuario");
					shakeModal();
				}
				else if (data.status == "Error en los campos de entrada")
				{
					$('.error').addClass('alert alert-danger').html("Error en los campos de entrada");
					shakeModal();
				}
				//$('input[type="password"]').val('');
				setTimeout( function(){ 
					$('#loginModal .modal-dialog').removeClass('shake'); 
				}, 1000 ); 
				$("#registerButton").prop("disabled", false);

			},
			error: function (e) {

				$('.error').addClass('alert alert-danger').html("Error en la peticion");
				//$('input[type="password"]').val('');
				setTimeout( function(){ 
					$('#loginModal .modal-dialog').removeClass('shake'); 
				}, 1000 ); 
				$("#registerButton").prop("disabled", false);

			}
		});
	}
	else 
	{
		$("#registerButton").prop("disabled", false);
		shakeModal();
		$('.error').addClass('alert alert-danger').html("La direccion de correo no es válida.");
		return (false)

	}

}

