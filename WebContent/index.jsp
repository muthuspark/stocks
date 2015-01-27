<!DOCTYPE html>
<%@page import="com.sun.xml.internal.ws.api.pipe.NextAction"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="muthu.richy.utility.Datawarehouse"%>
<%@page import="java.util.List"%>
<html>
<%
List<String> syms = Datawarehouse.getAllSymbols();
Map<String, Double>  syms1 = Datawarehouse.getTop5Stocks1Month();
Map<String, Double> syms3 = Datawarehouse.getTop5Stocks3Month();
Map<String, Double> syms6 = Datawarehouse.getTop5Stocks6Month();
Map<String, Double> syms0 = Datawarehouse.getTop5Stocks1Week();
%>
<head>
<meta charset="UTF-8">
<link href="data:image/x-icon;base64,AAABAAEAEBAAAAAAAABoBQAAFgAAACgAAAAQAAAAIAAAAAEACAAAAAAAAAEAAAAAAAAAAAAAAAEAAAAAAAAAAAAA////AN4sHwAUFPUAYescAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQABAQEBAQEBAQEBAQEBAQAAAAAAAAAAAAAAAAAAAAABAAEBAQEBAQEBAQEBAQEBAQADAwEBAQEBAQEBAQEBAQEAAQEDAQEBAQEBAQEBAQEBAAEBAQMDAwEDAwEBAQEBAQABAQEBAQEDAQEDAQEBBAEAAQEBAQEBAQEBAQMBBAEBAAQBAQEBAQEBBAQBAwQBAQABBAEBAQEBBAEBBAQDAQEAAQEEAQEBBAEBAQEBAwEBAAEBAQQBBAEBAQEBAQMBAQABAQEBBAEBAQICAgEBAwEAAQECAQECAgIBAQECAQEBAAECAQICAQEBAQEBAQIBAQACAQEBAQEBAQEBAQEBAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=" rel="icon" type="image/x-icon" />
<script type='text/javascript' src='//code.jquery.com/jquery-1.9.1.js'></script>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="http://blueimp.github.io/jQuery-File-Upload/css/jquery.fileupload.css">
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow' rel='stylesheet' type='text/css'>
<link href='css/style.css' rel='stylesheet' type='text/css'>
<title>Analyze Stocks</title>
</head>
<body>
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
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active">
        	<!-- The fileinput-button span is used to style the file input field as button -->
			<!-- <a href="#" class="star" onclick="javascript:star();return false;"><i class="fa-star"></i>star</a>    -->
		    <button class="btn btn-default fileinput-button" style="margin: 7px;">
		        <span>Upload Stock History file</span>
		        <input id="fileupload" type="file" name="files[]" multiple>
		    </button>
	    </li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" value="" class="form-control"  placeholder="Enter Stock Symbol as given in Yahoo Finance" id="stockid"  />
        </div>
        <input type="button" class="btn btn-default" onclick="javascript:getFromSymbol();" value="Analyze">
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="bullbear">Bull and Bear</a></li>
        <li><a href="pricewise">Price Wise</a></li>
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
	<div class="row" >
		    <div id="progress" class="progress">
		        <div class="progress-bar progress-bar-success"></div>
		    </div>
		    <div id="files" class="files"></div>
	</div>
	<div class="row" style="margin: 0;padding: 0;">
		<div class="col-md-10" style="margin: 0;padding: 0;">
			<div id="container" class="thumbnail" style="min-width: 310px; height: 400px; margin: 0 auto" ></div>
		</div>
		<div class="col-md-2" id="currentprice">
				<div class="yfi_rt_quote_summary" id="yfi_rt_quote_summary">
					<div class="hd">
						<div class="title">
							<h2>Tata Steel Limited (TATASTEEL.NS)</h2>
							<span class="rtq_exch"><span class="rtq_dash">-</span>NSE
							</span><span class="wl_sign"></span>
						</div>
					</div>
					<div class="yfi_rt_quote_summary_rt_top sigfig_promo_1">
						<div>
							<span class="time_rtq_ticker"><span
								id="yfs_l84_tatasteel.ns">554.95</span></span> <span
								class="down_r time_rtq_content"><span
								id="yfs_c63_tatasteel.ns"><img width="10" height="14"
									style="margin-right: -2px;" border="0"
									src="http://l.yimg.com/os/mit/media/m/base/images/transparent-1093278.png"
									class="neg_arrow" alt="Down"> 7.25</span><span
								id="yfs_p43_tatasteel.ns">(1.29%)</span> </span><span class="time_rtq">
								<span id="yfs_t53_tatasteel.ns"><span
									id="yfs_t53_tatasteel.ns">2:44AM EDT</span></span>
							</span>
						</div>
						<div></div>
					</div>
				</div>
			</div>
	</div>
	<div class="row">
		<div class="col-md-2" style="padding: 0;">
			<ul class="list-group">
			<li class="list-group-item active">All Stocks</li>
			<%for(String s : syms){%>
				<li class="list-group-item " id="<%=s%>" ><a href="#" onclick="javascript:getHistoryOfSymbol('<%=s%>');return false;"><%=s %></a></li>
			<%}%>
			</ul>
		</div>
		<div class="col-md-7" style="padding: 0;">
				<div class="col-md-6" style="padding-right: 0;">
					<div id="jsonlast12" class="thumbnail"></div>
				</div>
				<div class="col-md-6">
					<div id="jsonlast6" class="thumbnail"></div>
				</div>
				<div class="col-md-6" style="padding-right: 0;">
					<div id="jsonlast3" class="thumbnail"></div>
				</div>
				<div class="col-md-6">
					<div id="jsonlast1" class="thumbnail"></div>
				</div>
				<div class="col-md-6" style="padding-right: 0;">
					<div id="jsonlast0" class="thumbnail"></div>
				</div>
				<div class="col-md-6">
					<div id="slopes" class="thumbnail"></div>
				</div>
		</div>
		<div class="col-md-3" style="padding: 0;">
		<!-- 	<div class="alert alert-info" id="msg" style="display:none;" role="alert">If you had invested Rs. 10000 on <span id="1"></span>, it would be worth Rs. <span id="2"></span> today , A profit of <span id="3"></span>%</div>
			<h2 style="margin: 0;">Regression Equations<br><label id="a">a</label> + <label id="b">b</label>x</p></h2> -->
			<ul class="list-group">
				<li class="list-group-item active">Top 20 Stocks since  last 1 Week</li>
			<%
			Iterator itr0 = syms0.entrySet().iterator();
			while(itr0.hasNext()){
				 Entry e = (Entry)itr0.next();
				 String symbol = e.getKey().toString();%>
				<li class="list-group-item " id="<%=symbol.substring(0,symbol.length()-4)%>" ><a href="#" onclick="javascript:getHistoryOfSymbol('<%=symbol.substring(0,symbol.length()-4)%>');return false;"><%=symbol.substring(0,symbol.length()-4) %></a><span class="badge"><%=e.getValue() %></span></li>
			<%}%>
			</ul>
			<ul class="list-group">
				<li class="list-group-item active">Top 20 Stocks since last month</li>
			<%
			Iterator itr = syms1.entrySet().iterator();
			while(itr.hasNext()){
				 Entry e = (Entry)itr.next();
				 String symbol = e.getKey().toString();%>
				<li class="list-group-item " id="<%=symbol.substring(0,symbol.length()-4)%>" ><a href="#" onclick="javascript:getHistoryOfSymbol('<%=symbol.substring(0,symbol.length()-4)%>');return false;"><%=symbol.substring(0,symbol.length()-4) %></a><span class="badge"><%=e.getValue() %></span></li>
				
			<%}%>
			</ul>	
			<ul class="list-group">
				<li class="list-group-item active">Top 20 Stocks since  last 3 months</li>
			<%
			Iterator itr2 = syms3.entrySet().iterator();
			while(itr2.hasNext()){
				 Entry e = (Entry)itr2.next();
				 String symbol = e.getKey().toString();%>
				<li class="list-group-item " id="<%=symbol.substring(0,symbol.length()-4)%>" ><a href="#" onclick="javascript:getHistoryOfSymbol('<%=symbol.substring(0,symbol.length()-4)%>');return false;"><%=symbol.substring(0,symbol.length()-4) %></a><span class="badge"><%=e.getValue() %></span></li>
			<%}%>
			</ul>
			<ul class="list-group">
				<li class="list-group-item active">Top 20 Stocks since  last 6 months</li>
			<%
			Iterator itr3 = syms6.entrySet().iterator();
			while(itr3.hasNext()){
				 Entry e = (Entry)itr3.next();
				 String symbol = e.getKey().toString();%>
				<li class="list-group-item " id="<%=symbol.substring(0,symbol.length()-4)%>" ><a href="#" onclick="javascript:getHistoryOfSymbol('<%=symbol.substring(0,symbol.length()-4)%>');return false;"><%=symbol.substring(0,symbol.length()-4) %></a><span class="badge"><%=e.getValue() %></span></li>
			<%}%>
			</ul>
			<!--<p>Enter X Axis Value : <input type="number" value="100" id="x" onChange="calculateY()"/></p>
				<p>Expected Price: <label id="y"></label></p> -->
		</div>
	</div>
</div>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script src="http://blueimp.github.io/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
<script src="http://blueimp.github.io/jQuery-File-Upload/js/jquery.fileupload.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script type='text/javascript'>//<![CDATA[ 
                                          
var options2 = {
		chart: {
             zoomType: 'x'
        },
		title: {
            text: 'Gap Analysis',
            x: -20 //center
        },
        subtitle: {
        	 text: document.ontouchstart === undefined ?
                     'Click and drag in the plot area to zoom in' :
                     'Pinch the chart to zoom in'
        },
        yAxis: {
            title: {
                text: 'Difference between low and high on that day'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        xAxis: {
        	title : {
        		text : 'Time (Weeks)'
        	}
        },
        tooltip: {
            valueSuffix: 'INR'
        },
        legend: {
            enabled: false
        },
        series: [{
        	type: 'area',
            name: 'Low to High',
            data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
        }]	
};
//]]>  
</script>
<script type='text/javascript'>//<![CDATA[ 
                                          
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
        		text : 'Time (Weeks)'
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

var slopes = {
		 chart: {
             type: 'line'
         },
         title: {
             text: 'Growth Slopes'
         },
         subtitle: {
             text: 'For last 6 and 1 month'
         },
         yAxis: {
             title: {
                 text: 'Growth'
             }
         },
         plotOptions: {
             area: {
                 pointStart: 0,
                 marker: {
                     enabled: false,
                     symbol: 'circle',
                     radius: 1,
                     states: {
                         hover: {
                             enabled: true
                         }
                     }
                 }
             }
         },
        series: [ {name: 'Tokyo',
                 data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]} ]	
};

$(function () {
        $('#container').highcharts(options);
        $('#jsonlast0').highcharts(options);
        $('#jsonlast12').highcharts(options);
        $('#jsonlast6').highcharts(options);
        $('#jsonlast3').highcharts(options);
        $('#jsonlast1').highcharts(options);
        $('#slopes').highcharts(slopes);
});

//]]>  
</script>
<script>
/*jslint unparam: true */
/*global window, $ */
$(function () {
    'use strict';
    // Change this to the location of your server-side upload handler:
    var url = 'UploadServlet';
    $('#fileupload').fileupload({
        url: url,
        dataType: 'json',
        done: function (e, data) {
        	plotchart(options, "container",data.result.history);
			plotchart(options,"jsonlast12",data.result.jsonlast12);
			plotchart(options,"jsonlast6",data.result.jsonlast6);
			plotchart(options,"jsonlast3",data.result.jsonlast3);
			plotchart(options,"jsonlast1",data.result.jsonlast1);
			plotchart(options,"jsonlast0",data.result.jsonlast0);
			plotslopechart(data.result.jsonlast6,data.result.jsonlast3,data.result.jsonlast1,data.result.jsonlast0);
			getCurrentPrice($("#stockid").val());
			//gap analysis
        },
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .progress-bar').css(
                'width',
                progress + '%'
            );
        }
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
});
</script>
<script type="text/javascript">
function calculateY(){
	var a = parseFloat($("#a").text());
	var b = parseFloat($("#b").text());
	var x = parseFloat($("#x").val());
	$("#y").text(a+b*x);
}
function getHistoryOfSymbol(sym){
	$("#stockid").val(sym);
	$("#spinner").show();
	$.ajax({
		  url: "GetStock?fromdb=true&stockid="+sym,
		  contentType: "application/json" 
		  })
		  .done(function( data ) {
			  plotchart(options, "container",data.history);
			  plotchart(options,"jsonlast12",data.jsonlast12);
			  plotchart(options,"jsonlast6",data.jsonlast6);
			  plotchart(options,"jsonlast3",data.jsonlast3);
			  plotchart(options,"jsonlast1",data.jsonlast1);
			  plotchart(options,"jsonlast0",data.jsonlast0);
			  plotslopechart(data.jsonlast6,data.jsonlast3,data.jsonlast1,data.jsonlast0);
			  getCurrentPrice(sym);
			  $("#spinner").hide();
		 }).fail(function(){
			 	$("#spinner").hide(); 
		 });
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
function plotslopechart(j6,j3,j1,j0){
	var m = j6.slopeeq.slope;
	var c = j6.slopeeq.intercept;
	var ar6 = new Array(); 
	for(var i=0;i<30;i++){
		ar6.push(m*i+c);
	}
	m = j3.slopeeq.slope;
	c = j3.slopeeq.intercept;
	var ar3 = new Array(); 
	for(var i=0;i<30;i++){
		ar3.push(m*i+c);
	}
	m = j1.slopeeq.slope;
	c = j1.slopeeq.intercept;
	var ar1 = new Array(); 
	for(var i=0;i<30;i++){
		ar1.push(m*i+c);
	}
	m = j0.slopeeq.slope;
	c = j0.slopeeq.intercept;
	var ar0 = new Array(); 
	for(var i=0;i<30;i++){
		ar0.push(m*i+c);
	}
	slopes.series = [{name: 'Growth for Last 6 Months', data: ar6} , { name : 'Growth for Last 3 Months' , data : ar3 }, { name : 'Growth for Last 1 Month' , data : ar1 }, { name : 'Growth for Last 1 Week' , data : ar0 } ];
	$('#slopes').highcharts(slopes);
}
function plotchart(chartoptions, chartid,res){
	//var slope = res.slope;
//	var intercept = res.intercept;
	//$("#a").text(slope);
	//$("#b").text(intercept);
	chartoptions.series = [{name: 'Closing', data: res.closing} , { name : 'Opening' , data : res.opening } ];
	//options.xAxis.categories = res.timeaxis;
	chartoptions.title.text = $("#stockid").val() + " - " +chartid;
	chartoptions.subtitle.text = 'Duration: ' + res.from + " - " + res.till + '<br>Highest: ' + res.closinghighest + ' , Lowest: ' + res.closinglowest;
	//$("#1").text(res.from);
	
	//var units = 10000/parseFloat(res.first);// no of units that I could have bought
	//var worth = parseFloat(res.current) * units;
	//$("#2").text(worth);
	//$("#3").text(parseInt((worth/10000) * 100));
	//$("#msg").show();
	$('#'+chartid).highcharts(chartoptions);
	//options2.series = [{name: 'Low-High', data: res.gap} ];
	//$('#chart1').highcharts(options2);
}
function getFromSymbol(){
	$("#spinner").show();
	$.ajax({
		  url: "GetStock?stockid="+$("#stockid").val(),
		  contentType: "application/json" 
		  })
		  .done(function( data ) {
			  plotchart(options, "container",data.history);
			  plotchart(options,"jsonlast12",data.jsonlast12);
			  plotchart(options,"jsonlast6",data.jsonlast6);
			  plotchart(options,"jsonlast3",data.jsonlast3);
			  plotchart(options,"jsonlast1",data.jsonlast1);
			  plotchart(options,"jsonlast0",data.jsonlast0);
			  plotslopechart(data.jsonlast6,data.jsonlast3,data.jsonlast1,data.jsonlast0);
			  getCurrentPrice($("#stockid").val());
			  $("#spinner").hide();
		 }).fail(function(){
			 	$("#spinner").hide(); 
		 });
	
}
function star(){
	$.ajax({
	  url: "Utility?operation=star&symbol="+$("#stockid").val()
	  })
	  .done(function( data ) {
		 $("#"+$("#stockid").val()).addClass("star");
	 });
}

$().ready(function(){
	$('#stockid').on('keypress', function (e) {
	    var code = (e.keyCode ? e.keyCode : e.which);
	    if (code == 13) {
	    	getFromSymbol();
	    }
	});
});
</script>
</body>
</html>