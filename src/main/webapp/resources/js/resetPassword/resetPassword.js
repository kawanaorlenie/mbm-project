$(document).ready(function() {
	var form = $('#passwordsForm');
	form.submit(function(event) {
		event.preventDefault();

		$('.alert').remove();

		var url = contextPath + form.attr('action');
		var data = {};

		form.serializeArray().map(function(item) {
			data[item.name] = item.value;
		});

		$.ajax({
			url : url,
			type : 'post',
			contentType : 'application/json',
			data : JSON.stringify(data)
		}).done(function(data) {
			form.before('<div class="alert alert-info">' + data.message + '</div>')
		}).error(function(jqXHR, textStatus, errorThrown) {
			form.printErrors(jqXHR.responseJSON);
		});
	});
});

$(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});

$.fn.printErrors = function(json) {
	var form = this;
	var fieldErrors = json.fieldErrors;
	var globalErrors = json.globalErrors;
	$.each(fieldErrors, function(i, item) {
		form.before('<div for="' + item.field + '" class="alert alert-danger">'
				+ item.field + ' error: ' + item.message + '</div>')
	});
	$.each(globalErrors, function(i, item) {
		form.before('<div for="' + item.field + '" class="alert alert-danger">'
				+ item.field + ' error: ' + item.message + '</div>')
	});
};