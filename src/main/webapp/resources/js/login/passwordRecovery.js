$(document).ready(function() {
	var form = $('#passwordRecoveryForm');
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
			form.printErrors(jqXHR.responseJSON.fieldErrors);
		});
	});
});

$.fn.printErrors = function(fieldErrors) {
	var form = this;
	$.each(fieldErrors, function(i, item) {
		form.before('<div for="' + item.field + '" class="alert alert-danger">'
				+ item.field + ' error: ' + item.message + '</div>')
	});
};
