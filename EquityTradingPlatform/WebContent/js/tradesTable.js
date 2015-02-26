$(document).ready(
		function() {
			setInterval(function() {
				$('#tradeupdate').html(
						'<table class="table table-bordered table-hover table-striped">'
								+ '<tr>' + '<th>Order #</th>'
								+ '<th>Ticker</th>' + '<th>Volume</th>'
								+ '<th>Price</th>' + '<th>Dealer</th>'
								+ '</tr>')
			}, 3000);
		});
