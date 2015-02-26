var askPrice;
var bidPrice;

// $(document).ready(function() {
//
// // $("#showTable").click(function(event){
// // setInterval("function();",10000);
// $.get('PopulateGraph', function(responseJson) {
// if (responseJson != null) {
// // $("#countrytable").find("tr:gt(0)").remove();
// // var table1 = $("#countrytable");
// $.each(responseJson, function(key, value) {
//
// openPrice = value['openPR'];
// });
//
// }
// });
// });

$(document).ready(function() {
	$.get('PopulateGraph', function(responseJson) {
		if (responseJson != null) {
			$.each(responseJson, function(key, value) {

				askPrice = value['askPrice'];
				bidPrice = value['bidPrice'];

				var element = document.getElementById("test");
				element.innerHTML = askPrice;
			});
		}
	});

});

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
												var x = (new Date()).getTime(), // current
												// time
												y = askPrice;
												// y = bidprice;
												series.addPoint([ x, y ], true,
														true);
											}, 1000);
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
									enabled : false
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
							});
		});