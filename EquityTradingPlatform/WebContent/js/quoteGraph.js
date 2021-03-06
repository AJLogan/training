var askPrice;
var bidPrice;

function getData() {
	$.get('PopulateGraph', function(responseJson) {
		if (responseJson != null) {
			$.each(responseJson, function(key, value) {
				askPrice = value['askPrice'];
				bidPrice = value['bidPrice'];
			});
		}
	});
};

$(document).ready(
		function() {
			Highcharts.setOptions({
				global : {
					useUTC : false
				}
			});

			$('#quoteGraph').highcharts(
					{
						chart : {
							type : 'line',
							animation : Highcharts.svg, // don't animate
							// in old IE
							marginRight : 10,
							events : {
								load : function() {

									// set up the updating of the chart
									// each second
									var series = this.series[0];
									var series2 = this.series[1];

									setInterval(function() {
										getData();
										var x = (new Date()).getTime(), // current
										// time
										y = askPrice;
										z = bidPrice;
										series.addPoint([ x, y ], false, true);
										series2.addPoint([ x, z ], true, true);
									}, 500);

								}
							}
						},
						title : {
							text : 'Quote Data'
						},
						xAxis : {
							type : 'datetime',
							tickPixelInterval : 150
						},
						yAxis : [ {
							title : {
								text : 'Value'
							},
							plotLines : [ {
								value : 0,
								width : 1,
								color : '#808080'
							} ]
						}, {
							title : {
								text : ''
							},
							plotLines : [ {
								value : 0,
								width : 1,
								color : '#808080'
							} ]
						} ],
						tooltip : {

							crosshairs : [ true, true ],

							formatter : function() {
								return '<b>' + this.series.name + '<br/>'
										+ Highcharts.numberFormat(this.y, 2);
							}
						},
						legend : {
							enabled : true
						},
						exporting : {
							enabled : false
						},
						series : [
								{
									name : 'Ask Price',
									data : (function() {
										// generate an array of random data
										var data = [], time = (new Date())
												.getTime(), i;

										for (i = -19; i <= 0; i += 1) {
											data.push({
												x : time + i * 1000,
												y : askPrice
											});
										}
										return data;
									}())
								},
								{
									name : 'Bid Price',
									data : (function() {
										var data = [], time = (new Date())
												.getTime(), i;

										for (i = -19; i <= 0; i += 1) {
											data.push({
												x : time + i * 1000,

												y : bidPrice
											});
										}
										return data;
									}())
								} ]
					});
		});