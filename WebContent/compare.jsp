<%@page import="muthu.richy.utility.Constants"%>
<%@page import="muthu.richy.utility.Datawarehouse"%>
<%@page import="muthu.richy.model.Stock"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="data:image/x-icon;base64,AAABAAEAEBAAAAAAAABoBQAAFgAAACgAAAAQAAAAIAAAAAEACAAAAAAAAAEAAAAAAAAAAAAAAAEAAAAAAAAAAAAA////AN4sHwAUFPUAYescAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQABAQEBAQEBAQEBAQEBAQAAAAAAAAAAAAAAAAAAAAABAAEBAQEBAQEBAQEBAQEBAQADAwEBAQEBAQEBAQEBAQEAAQEDAQEBAQEBAQEBAQEBAAEBAQMDAwEDAwEBAQEBAQABAQEBAQEDAQEDAQEBBAEAAQEBAQEBAQEBAQMBBAEBAAQBAQEBAQEBBAQBAwQBAQABBAEBAQEBBAEBBAQDAQEAAQEEAQEBBAEBAQEBAwEBAAEBAQQBBAEBAQEBAQMBAQABAQEBBAEBAQICAgEBAwEAAQECAQECAgIBAQECAQEBAAECAQICAQEBAQEBAQIBAQACAQEBAQEBAQEBAQEBAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=" rel="icon" type="image/x-icon" />
<script type='text/javascript' src='//code.jquery.com/jquery-1.9.1.js'></script>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="http://blueimp.github.io/jQuery-File-Upload/css/jquery.fileupload.css">
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow' rel='stylesheet' type='text/css'>
<link href='css/style.css' rel='stylesheet' type='text/css'>
<title>Bull and Bear</title>
</head>
<body>
<%
List<Stock> bulls0 = Datawarehouse.getBullStocks(0);
List<Stock> bulls1 = Datawarehouse.getBullStocks(1);
List<Stock> bulls3 = Datawarehouse.getBullStocks(3);
List<Stock> bulls6 = Datawarehouse.getBullStocks(6);
List<Stock> bears0 = Datawarehouse.getBearStocks(0);
List<Stock> bears1 = Datawarehouse.getBearStocks(1);
List<Stock> bears3 = Datawarehouse.getBearStocks(3);
List<Stock> bears6 = Datawarehouse.getBearStocks(6);
%>
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
      <ul class="nav navbar-nav">
      	<li>
	      <a href="bullbear?period=0">Last 1 Week</a>
      	</li>
      	<li>
	      <a href="bullbear?period=1">Last 1 Month</a>
      	</li>
      	<li>
	      <a href="bullbear?period=3">Last 3 Months</a>
      	</li>
      	<li>
	      <a href="bullbear?period=6">Last 6 Months</a>
      	</li>
      </ul>
    </div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="bullbear">Home</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid">
	<div class="row text-center">
		<img src="images/12385288-bulls-and-bears.jpg" /><br/>
		<h1>Bull and Bear</h1>
		<p>Comparison</p>
		<div style="height:30px;">
			<img src="images/ajax-loader.gif" id="spinner" style="display:none;"/>
		</div>
	</div>
	<h1>Bulls</h1>
	<div class="row">
		<div class="col-md-3">
			<ul class="list-group" id="bulls">
				<li class="list-group-item active">Last 1 Week</li>
			  <%for(Stock bull : bulls0){%>
				  <li class="list-group-item"><a href="#" onclick="javascript:getHistoryOfSymbol('<%=bull.getSymbol()%>');return false;"><%=bull.getSymbol() %></a> <span class="badge"><%=bull.getPrice()%> </span></li>
			  <%}%>
			</ul>
		</div>
		<div class="col-md-3">
			<ul class="list-group" id="bulls">
			<li class="list-group-item active">Last 1 Month</li>
			  <%for(Stock bull : bulls1){%>
				  <li class="list-group-item"><a href="#" onclick="javascript:getHistoryOfSymbol('<%=bull.getSymbol()%>');return false;"><%=bull.getSymbol() %></a> <span class="badge"><%=bull.getPrice()%> </span></li>
			  <%}%>
			</ul>
		</div>
		<div class="col-md-3">
			<ul class="list-group" id="bulls">
			<li class="list-group-item active">Last 3 Months</li>
			  <%for(Stock bull : bulls3){%>
				  <li class="list-group-item"><a href="#" onclick="javascript:getHistoryOfSymbol('<%=bull.getSymbol()%>');return false;"><%=bull.getSymbol() %></a> <span class="badge"><%=bull.getPrice()%> </span></li>
			  <%}%>
			</ul>
		</div>
		<div class="col-md-3">
			<ul class="list-group" id="bulls">
			<li class="list-group-item active">Last 6 Months</li>
			  <%for(Stock bull : bulls6){%>
				  <li class="list-group-item"><a href="#" onclick="javascript:getHistoryOfSymbol('<%=bull.getSymbol()%>');return false;"><%=bull.getSymbol() %></a> <span class="badge"><%=bull.getPrice()%> </span></li>
			  <%}%>
			</ul>
		</div>
	</div>
	<h1>Bears</h1>
	<div class="row">
		<div class="col-md-3">
			<ul class="list-group" id="bears">
			<li class="list-group-item active">Last 1 Week</li>
			  <%for(Stock bear : bears0){%>
				  <li class="list-group-item"><a href="#" onclick="javascript:getHistoryOfSymbol('<%=bear.getSymbol()%>');return false;"><%=bear.getSymbol() %></a> <span class="badge"><%=bear.getPrice()%> </span></li>
			  <%}%>
			</ul>
		</div>
		<div class="col-md-3">
			<ul class="list-group" id="bears">
			<li class="list-group-item active">Last 1 Month</li>
			  <%for(Stock bear : bears1){%>
				  <li class="list-group-item"><a href="#" onclick="javascript:getHistoryOfSymbol('<%=bear.getSymbol()%>');return false;"><%=bear.getSymbol() %></a> <span class="badge"><%=bear.getPrice()%> </span></li>
			  <%}%>
			</ul>
		</div>
		<div class="col-md-3">
			<ul class="list-group" id="bears">
			<li class="list-group-item active">Last 3 Months</li>
			  <%for(Stock bear : bears3){%>
				  <li class="list-group-item"><a href="#" onclick="javascript:getHistoryOfSymbol('<%=bear.getSymbol()%>');return false;"><%=bear.getSymbol() %></a> <span class="badge"><%=bear.getPrice()%> </span></li>
			  <%}%>
			</ul>
		</div>
		<div class="col-md-3">
			<ul class="list-group" id="bears">
			<li class="list-group-item active">Last 6 Months</li>
			  <%for(Stock bear : bears6){%>
				  <li class="list-group-item"><a href="#" onclick="javascript:getHistoryOfSymbol('<%=bear.getSymbol()%>');return false;"><%=bear.getSymbol() %></a> <span class="badge"><%=bear.getPrice()%> </span></li>
			  <%}%>
			</ul>
		</div>
	</div>
</div>
<input type="hidden" value="" id="stockid"  />
<script type="text/javascript" src="js/angular.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript">
var options = {
		 chart: {
            zoomType: 'x'
        },
		title: {
           text: 'Stock History',
           
           x: -20 //center
       },
       subtitle: {
       	
           text: document.ontouchstart === undefined ?
                          'Click and drag in the plot area to zoom in' :
                          'Pinch the chart to zoom in',
           x: -20
       },
       yAxis: {
           title: {
               text: 'Stock Value'
           },
         
           plotLines: [{
               value: 0,
               width: 1,
               color: '#808080'
           }]
       },
       xAxis: {
       	title : {
       		text : 'Time (In Weeks)'
       	}
       },
       tooltip: {
           valueSuffix: 'INR'
       },
       legend: {
           layout: 'vertical',
           align: 'right',
           verticalAlign: 'middle',
           borderWidth: 0
       },
       series: [ {name: 'Tokyo',
                 data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]} ]	
};
var resdata = new Object();
function getHistoryOfSymbol(sym){
	$("#stockid").val(sym);
	$("#spinner").show();
	$.ajax({
		  url: "GetStock?fromdb=true&stockid="+sym,
		  contentType: "application/json" 
		  })
		  .done(function( data ) {
			  resdata = data;
			  plotchart(options, "container",data.jsonlast1,"Last 3 Months");
			  getCurrentPrice(sym);
			  $("#spinner").hide();
		 }).fail(function(){
			 $("#spinner").hide(); 
		 });
}
function plotchart(chartoptions, chartid,res, heading){
	chartoptions.series = [{name: 'Closing', data: res.closing} , { name : 'Opening' , data : res.opening } ];
	chartoptions.title.text = $("#stockid").val() + " - " + heading;
	chartoptions.subtitle.text = 'Duration: ' + res.from + " - " + res.till + '<br>Highest: <b> ' + res.closinghighest + ' </b> , Lowest: <b>' + res.closinglowest+'</b>';
	$('#'+chartid).highcharts(chartoptions);
}
function getCurrentPrice(symbol){
	$.ajax({
		  url: "GetCurrentPrice?symbol="+symbol,
		  contentType: "text/html" 
		  })
		  .done(function( data ) {
			  $("#currentprice").html(data);
			  $("#spinner").hide();
		 }).fail(function(){
			  $("#spinner").hide(); 
		 });
}
$(function () {
	$("#timeselector").change(function(){
		var cho = parseInt($("#timeselector").val());
		var header = $("#timeselector option:selected").text();
		switch(cho){
			case 1:
				 plotchart(options, "container",resdata.jsonlast0,header);
				break;
			case 2:
				 plotchart(options, "container",resdata.jsonlast1,header);
				break
			case 3:
				 plotchart(options, "container",resdata.jsonlast3,header);
				break;
			case 4: 
				 plotchart(options, "container",resdata.jsonlast6,header);
				break;
			case 5: 
				 plotchart(options, "container",resdata.jsonlast12,header);
				break;
			case 6: 
				 plotchart(options, "container",resdata.history,header);
				break;
		}
	});
    $('#container').highcharts(options);
    $('#jsonlast1').highcharts(options);
    $( "#slider-range" ).slider({
        range: true,
        min: 0,
        max: 40000,
        values: [ 500, 3000 ],
        slide: function( event, ui ) {
          $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
        },
        stop: function( event, ui ) {
        	$.ajax({
      		  url: "Utility?operation=pricefilter&min="+ui.values[ 0 ]+"&max="+ui.values[1],
      		  contentType: "text/html" 
      		  })
      		  .done(function( data ) {
      			  $("#pricefilter").html(data);
      			  updatebullbearindicators();
      			  $("#spinner").hide();
      		 }).fail(function(){
      			  $("#spinner").hide(); 
      		 });       	
        }
      });
      $( "#amount" ).val( "Rs." + $( "#slider-range" ).slider( "values", 0 ) +
        " - Rs." + $( "#slider-range" ).slider( "values", 1 ) );
});
function updatebullbearindicators(){
	
}
</script>
</body>
</html>