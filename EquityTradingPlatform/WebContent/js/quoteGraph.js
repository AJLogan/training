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

			$('#quoteGraph')
					.highcharts(
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

											setInterval(function() {
												getData();
												var x = (new Date()).getTime(), // current
																				// time
												y = askPrice;
												series.addPoint([ x, y ], true,
														true);
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
								yAxis : {
									title : {
										text : 'Value'
									},
									plotLines : [ {
										value : 0,
										width : 1,
										color : '#808080'
									} ]
								},
								tooltip : {

									crosshairs : [ true, true ],

									formatter : function() {
										return '<b>'
												+ this.series.name
												+ '</b><br/>'
												+ Highcharts.dateFormat(
														'%Y-%m-%d %H:%M:%S',
														this.x)
												+ '<br/>'
												+ Highcharts.numberFormat(
														this.y, 2);
									}
								},
								legend : {
									enabled : true
								},
								exporting : {
									enabled : false
								},
								series : [ {
									name : 'Quote Data',
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
								} ]
							// ,
							// series : [ {
							// name : 'Bid Price',
							// data : (function() {
							// // generate an array of random data
							// var data2 = [];
							// data2.push(bidPrice);
							// return data2;
							// }())
							// } ]
							});
		});