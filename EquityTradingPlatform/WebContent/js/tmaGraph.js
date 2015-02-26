var sma;
var lma;

function getTMAData() {
	$.get('PopulateTMAGraph', function(responseJson) {
		if (responseJson != null) {
			$.each(responseJson, function(key, value) {
				sma = value['sma'];
				lma = value['lma'];
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

			$('#tmaGraph').highcharts(
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
										getTMAData();
										var x = (new Date()).getTime(), // current
										// time
										y = sma;
										z = lma;
										series.addPoint([ x, y ], false, true);
										series2.addPoint([ x, z ], true, true);
									}, 500);

								}
							}
						},
						title : {
							text : 'Two Moving Averages'
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
								color : '#d9534f'
							} ]
						}, {
							title : {
								text : ''
							},
							plotLines : [ {
								value : 0,
								width : 1,
								color : '#5cb85c'
							} ]
						} ],
						tooltip : {
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
									name : 'SMA',
									data : (function() {
										// generate an array of random data
										var data = [], time = (new Date())
												.getTime(), i;

										for (i = -19; i <= 0; i += 1) {
											data.push({
												x : time + i * 1000,
												y : sma
											});
										}
										return data;
									}())
								},
								{
									name : 'LMA',
									data : (function() {
										var data = [], time = (new Date())
												.getTime(), i;

										for (i = -19; i <= 0; i += 1) {
											data.push({
												x : time + i * 1000,
												y : lma
											});
										}
										return data;
									}())
								} ]
					});
		});