
window.onload = function() {
	
	// dataPoints
	var dataPoints1 = [];
	var dataPoints2 = [];

	var chart = new CanvasJS.Chart("graph", {
		zoomEnabled : true,
		title : {
			text : "Share Value of Two Companies"
		},
		toolTip : {
			shared : true

		},
		legend : {
			verticalAlign : "top",
			horizontalAlign : "center",
			fontSize : 14,
			fontWeight : "bold",
			fontFamily : "calibri",
			fontColor : "dimGrey"
		},
		axisX : {
			title : "chart updates every 3 secs"
		},
		axisY : {
			//prefix : '$',
			includeZero : false
		},
		data : [ {
			// dataSeries1
			type : "line",
			xValueType : "dateTime",
			showInLegend : true,
			name : "Company A",
			dataPoints : dataPoints1
		}/*, {
			// dataSeries2
			type : "line",
			xValueType : "dateTime",
			showInLegend : true,
			name : "Company B",
			dataPoints : dataPoints2
		} */],
		legend : {
			cursor : "pointer",
			itemclick : function(e) {
				if (typeof (e.dataSeries.visible) === "undefined"
						|| e.dataSeries.visible) {
					e.dataSeries.visible = false;
				} else {
					e.dataSeries.visible = true;
				}
				chart.render();
			}
		}
	});

	var updateInterval = 300;
	// initial value
	var yValue1 = 50;
	//var yValue2 = 604;

	var time = new Date;
	time.setHours(9);
	time.setMinutes(30);
	time.setSeconds(00);
	time.setMilliseconds(00);
	// starting at 9.30 am

	var updateChart = function(count) {
		count = count || 1;

		// count is number of times loop runs to generate random dataPoints.

		for (var i = 0; i < count; i++) {

			// add interval duration to time
			time.setTime(time.getTime() + updateInterval);

			// generating random values
			var deltaY1 = getQuotes();//.5 + Math.random() * (-.5 - .5);
			//var deltaY2 = .5 + Math.random() * (-.5 - .5);

			// adding random value and rounding it to two digits.
			yValue1 = deltaY1//Math.round((yValue1 + deltaY1) * 100) / 100;
			//yValue2 = Math.round((yValue2 + deltaY2) * 100) / 100;

			// pushing the new values
			dataPoints1.push({
				x : time.getTime(),
				y : getQuotes()
			});
			/*dataPoints2.push({
				x : time.getTime(),
				y : yValue2
			});*/

		}
		;

		// updating legend text with updated with y Value
		chart.options.data[0].legendText = " Company A  $" + yValue1;
		//chart.options.data[1].legendText = " Company B  $" + yValue2;

		chart.render();

	};

	// generates first set of dataPoints
	updateChart(300);

	// update chart after specified interval
	setInterval(function() {
		updateChart()
	}, updateInterval);
}

var data = [];

var container = $("#quoteGraph");
var debug = $("#debug");

// Determine how many data points to keep based on the placeholder's initial
// size;
// this gives us a nice high-res plot while avoiding more than one point per
// pixel.

var maximum = container.outerWidth();

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