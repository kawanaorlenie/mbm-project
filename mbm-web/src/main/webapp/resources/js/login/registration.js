$(document).ready(function() {
	$("#registrationSuccessDialog").dialog();
	$("#registrationSuccessDialog").dialog('close');
	$('#registerForm').enableValidation();
});

$(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});

$.fn.enableValidation = function() {
	this.submit(function(event) {
		event.preventDefault();
		$(this).sendForm();
	});

	this.find(":input[type=text],:input[type=password]").each(function() {
		$(this).blur(function() {
			validate($(this));
		});
	});
};

$.fn.sendForm = function() {
	var url = contextPath + this.attr('action');
	var data = {};

	this.serializeArray().map(function(item) {
		data[item.name] = item.value;
	});

	$.ajax({
		url : url,
		type : 'post',
		contentType : 'application/json',
		data : JSON.stringify(data)
	}).done(function(data) {
		if (data.code != 200) {
			console.log(data.message)
		} else
			$("#registrationSuccessDialog").dialog('open');
	});
	;
};

function validate(input) {
	var name = input.attr('name');
	var formId = input.closest('form').attr('id');
	var buttonId = $("#" + formId + " button").attr('id');
	var currentUrl = window.location.href;
	var jsonURL = contextPath + "/validate/" + formId + "/" + name;
	var data = {};

	input.closest('form').serializeArray().map(function(item) {
		data[item.name] = item.value;
	});

	$.ajax(jsonURL, {
		data : JSON.stringify(data),
		contentType : 'application/json',
		type : 'POST',
	}).done(function(data) {
		$('div[for=' + name + ']').remove();
		if (data.code != 200) {
			styleAsInvalid(input);
			showValidationMessages(input, data.message);
		} else
			styleAsValid(input);
	});
}

function styleAsValid(input) {
	input.css('border-color', 'green');
};

function styleAsInvalid(input) {
	input.css('border-color', 'red');
};

function showValidationMessages(input, message) {
	var inputName = input.attr('name');
	var label = $('label[for=' + inputName + ']');
	label.before('<div for="' + inputName + '" class="alert alert-danger">'
			+ message + '</div>');
}