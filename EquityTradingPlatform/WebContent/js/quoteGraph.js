// Flot Chart Dynamic Chart

$(function() {
	//$.get('QuoteGraphServlet',function(responseJson) {
		//$('#quoteGraph').text(responseText);
   // var items = responseText;

	var container = $("#quoteGraph");
	var debug = $("#debug");

	// Determine how many data points to keep based on the placeholder's initial
	// size;
	// this gives us a nice high-res plot while avoiding more than one point per
	// pixel.

	var maximum = container.outerWidth();

	//

	var data = [];

	function getRandomData() {

		if (data.length) {
			data = data.slice(1);
		}

		while (data.length < maximum) {
			var previous = data.length ? data[data.length - 1] : 50;
			var y = previous + Math.random() * 10 - 5;
			data.push(y < 0 ? 0 : y > 100 ? 100 : y);
		}

		// zip the generated y values with the x values

		var res = [];
		for (var i = 0; i < data.length; ++i) {
			res.push([ i, data[i] ])
		}

		return res;
	}
	
	function getQuotes() {
		if (data.length) {
			data = data.slice(1);
		}
		$.ajax({
	 		url: 'QuoteGraphServlet',
	 		success: function(test){
	 			data = test;
	 		}
			});
		//alert(data[0])
		data = data.slice(1,maximum);
		var res = [];
		for (var i = 0; i < data.length; ++i) {
			res.push([ i, data[i] ])
		}

		return res;
	
	}

	series = [ {
		data : getQuotes(),
		lines : {
			fill : true
		}
	} ];

	//

	var plot = $
			.plot(
					container,
					series,
					{
						grid : {
							borderWidth : 1,
							minBorderMargin : 20,
							labelMargin : 10,
							backgroundColor : {
								colors : [ "#fff", "#e4f4f4" ]
							},
							margin : {
								top : 8,
								bottom : 20,
								left : 20
							},
							markings : function(axes) {
								var markings = [];
								var xaxis = axes.xaxis;
								for (var x = Math.floor(xaxis.min); x < xaxis.max; x += xaxis.tickSize * 2) {
									markings.push({
										xaxis : {
											from : x,
											to : x + xaxis.tickSizex
										},
										color : "rgba(232, 232, 255, 0.2)"
									});
								}
								return markings;
							}
						},
						xaxis : {
							tickFormatter : function() {
								return "";
							}
						},
						yaxis : {
							min : 125,
							max : 135
						},
						legend : {
							show : true
						}
					});

	// Update the random dataset at 25FPS for a smoothly-animating chart

	setInterval(function updateRandom() {
		series[0].data = getQuotes();
		//series= getQuotes();
		plot.setData(series);
		//plot.setData(getQuotes);
		plot.draw();
	}, 1000000);

	});

