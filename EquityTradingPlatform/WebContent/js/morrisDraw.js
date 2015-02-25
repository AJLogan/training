$(function() {
	var q = [];
	var nReloads = 0;
	function data(offset) {
		var ret = [];
		for (var x = 0; x <= 360; x += 10) {
			var v = (offset + x) % 360;
			ret.push({
				x : x,
				data : getQuotes()
			});
		}
		return ret;
	}
	var graph = Morris.Line({
		element : 'quotes',
		data : data(0),
		xkey : 'x',
		ykeys : [ 'data' ],
		labels : [ 'sin()' ],
		parseTime : false,
		ymin : -1.0,
		ymax : 1.0,
		hideHover : true
	});
	function update() {
		nReloads++;
		graph.setData(data(5 * nReloads));
		$('#reloadStatus').text(nReloads + ' reloads');
	}
	setInterval(update, 100);
	
	function getQuotes() {
		/*if (q.length) {
			q = q.slice(1);
		}*/
		$.ajax({
	 		url: 'QuoteGraphServlet',
	 		success: function(test){
	 			q = test;
	 		}
			});
		//alert(data[0])
		// data = data.slice(1,maximum);
		var res = [];
		for (var i = 0; i < q.length; ++i) {
			res.push([ i, q[i] ])
		}
		return res;
	}
});