$(document).ready(function() {
	$('#passwordRecoveryForm').submit(function(event) {
		event.preventDefault();
		$(this).sendForm2();
	});
});

$.fn.sendForm2 = function() {
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
		alert(data);
	});
	;
};
