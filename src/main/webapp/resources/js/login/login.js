$(document).ready(function() {

	$("#tabs").tabs({
		activate : function(event, ui) {
			$.cookie("tabs_selected", $("#tabs").tabs("option", "active"));
		},
		active : $("#tabs").tabs({
			active : $.cookie("tabs_selected")
		}),
	});

});


