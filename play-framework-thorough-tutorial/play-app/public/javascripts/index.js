$(function() {
	$("#sendbtn").click(function() {
		var jsondata = {
			'input': $("#input").val()
		};
		$.post("/ajax", jsondata, function(result) {
			var res = "status:" + result.message;
			$("#message").text(res);
		}, "json");
	})
})