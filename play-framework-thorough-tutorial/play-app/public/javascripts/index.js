$(function() {
	$("#sendbtn").click(function() {
		var jsondata = {
			'input': $("#input").val()
		};
		$.post("/ajax", jsondata, function(result) {
			var nm = result.getElementsByTagName("name").item(0);
			var nm_txt = nm.childNodes[0].data;
			var ml = result.getElementsByTagName("mail").item(0);
			var ml = nm.childNodes[0].data;
			var tl = result.getElementsByTagName("tel").item(0);
			var tl_txt = nm.childNodes[0].data;
			var res = "name:" + nm_txt + ", mail:" + ml_txt + ", tel:" + tl_txt;
			$("#message").text(res);
		}, "xml");
	})
})