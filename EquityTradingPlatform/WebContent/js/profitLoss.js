$(document).ready(function() {
	setInterval(function() {
		var value = "<%=profitLoss%>";
		var pl = Math.floor(Math.random() * 100);
		$('#pandl').text('£' + pl);
	}, 1000);
});