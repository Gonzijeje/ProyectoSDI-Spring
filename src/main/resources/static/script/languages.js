$(document).ready(function() {
	$("#languageDropdownMenuButton a").click(function(e) {
		e.preventDefault();
		var languageSelectedText = $(this).text();
		var languageSelectedValue = $(this).attr("value");
		
		$("#btnLanguage").text(languageSelectedText);
		window.location.replace('?lang=' + languageSelectedValue);
		return false;
	});
});