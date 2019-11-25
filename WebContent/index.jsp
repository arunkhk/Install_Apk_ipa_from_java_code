<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page  import="javax.servlet.jsp.PageContext"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Yaantra</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
<link href="/YaantraMobileDiagnosis/css/app-css.css" rel="stylesheet" type="text/css" />
<script src="/YaantraMobileDiagnosis/script/min.js"></script>
</head>

<script>
function myFunction() {
	debugger;
  document.getElementById('myDIV').removeAttribute("style");
}

function runMyBatFileForIos(){
/* 	alert("BatchExcuted");
	   WshShell = new ActiveXObject("Wscript.Shell"); //Create WScript Object
	   WshShell.run("E://Arun_Java_Workspace/YaantraMobileDiagnosis/iOS-installer/readLogBatchFile.bat"); */ // Please change the path and file name with your relevant available path in client system. This code can be used to execute .exe file as well
	}

$(document).ready(function(){
	
	$('input[type=radio][name=deviceType]').change(function() {
		
		$("#myDIV").hide();
	});
	
			$("#btnInstallApp").click(function()
					{
				debugger;
				var deviceType = $("input[name='deviceType']:checked").val();
				
				if(deviceType=='android')
					{
					installAndroid();
					}
				else if(deviceType=='ios')
				{
					installiOS();
				}
					
					});

		
		});
		
		
		function installAndroid()
		{
			
				var isError=false;
				$.ajax({
					type:'POST',
					url:'/YaantraMobileDiagnosis/MyMainServlet',
					datatype:'json',
					data:'4',
					beforeSend:function()
					{
						$("#myDIV").html("Application is being installed please wait....");
						$("#myDIV").show();
						$("#myDIV").css("color", "green");
						$("#btnInstallApp").prop('disabled', true).addClass('disabled');
					},
					success:function(data)
					{
						
						if($.trim(data)=='deviceNotConnected'){
							$("#myDIV").html('Android device is not connected  !, kindly connect your device properly');
							$("#myDIV").css("color", "red");
							isError=true;
							}
					
						else if($.trim(data)=='grantPermissoinToDubugging'){
							$("#myDIV").html('Kindly allow USB debugging, if you cancelled permission dialog prior remove USB cable and plug it again ');
							$("#myDIV").css("color", "red");
							isError=true;
							}
						
						else if($.trim(data)=='nextStep'){
							$("#myDIV").html('Device is beging digonose...');
							$("#myDIV").css("color", "green");
							GetDeviceStatus();
							}

					},
					complete:function(){
						if(!isError)	
							$("#myDIV").hide();
						$("#btnInstallApp").prop('disabled', false).removeClass('disabled');
					},
					error:function(xhr,error)
					{
						$("#myDIV").html('Something went wrong.');	
						$("#btnInstallApp").prop('disabled', false).removeClass('disabled');
					}
				
				});
					
			
		}
		
		
		function installiOS()
		{
			
				var isError=false;
				$.ajax({
					type:'POST',
					url:'/YaantraMobileDiagnosis/iOSServlet',
					datatype:'json',
					data:'6',
					beforeSend:function()
					{
						$("#myDIV").html("Application is being installed please wait....");
						$("#myDIV").show();
						$("#myDIV").css("color", "green");
						$("#btnInstallApp").prop('disabled', true).addClass('disabled');
					},
					success:function(data)
					{
						
						if($.trim(data)=='deviceNotConnected'){
							$("#myDIV").html('Apple device is not connected  !, kindly connect your device properly');
							$("#myDIV").css("color", "red");
							isError=true;
							}
					
						else if($.trim(data)=='grantPermissoinToDubugging'){
							$("#myDIV").html('Kindly allow USB debugging, if you cancelled permission dialog prior remove USB cable and plug it again ');
							$("#myDIV").css("color", "red");
							isError=true;
							}
						
						else if($.trim(data)=='nextStep'){
							$("#myDIV").html('Device is beging digonose...');
							$("#myDIV").css("color", "green");
							GetDeviceStatusForiOS();
							}

					},
					complete:function(){
						
						if(!isError)	
							$("#myDIV").hide();
						$("#btnInstallApp").prop('disabled', false).removeClass('disabled');
					},
					error:function(xhr,error)
					{
						$("#myDIV").html('Something went wrong.');	
						$("#btnInstallApp").prop('disabled', false).removeClass('disabled'); 
						
					}
				
				});	
		}
		
		
		
		
		
		function GetDeviceStatus()
		{
			var myVar=null;
			var isStart=false;
			   myVar = setInterval(myTimer, 500); 
			    function myTimer() {
					    $.ajax({
							type:'post',
							url:'/YaantraMobileDiagnosis/MyMainServlet',
							datatype:'json',
							data:'5',
							success:function(data){
								var cPrice=0;
								$("#dvPriceSection").hide();
								$("#dvFirstSection").hide();
								$("#dvSecondSection").show();
								var prevValue=$("#otpResult").html();	
								var prevValueFormat=prevValue.replace("<br>","");
								var curValueFormat=data.replace("<br>","");
								var healthScore="";
								var isHealthScore=false;
								if(curValueFormat.indexOf('Start')!=-1)
								{
									isStart=true;
								}
								if(curValueFormat.indexOf('Disconnected')!=-1)
								{
									window.location='index.jsp';
								}
								if(isStart)
								{
									if(curValueFormat.indexOf('Health Score')!=-1)
									{
										healthScore=$.trim(curValueFormat).split('_')[1];
										isHealthScore=true;
									}
									if(prevValue!='')
									{
										if($.trim(prevValueFormat)!=$.trim(curValueFormat))
										{
											if(curValueFormat.indexOf('_Ok')!=-1 || curValueFormat.indexOf('_Not')!=-1)
											{
												$("#ulDigonseStatus").append('<li><img src="img/'+$.trim(curValueFormat)+'.png"> <p>'+$.trim(curValueFormat).replace('_Ok','').replace('_Not','')+' </p></li>')
												$("#dvScanningParam").html('<img src="img/'+$.trim(curValueFormat)+'.png"><div>'+$.trim(curValueFormat).replace('_Ok','').replace('_Not','')+'</div>')
											}
										}
										
									}
							
									
									if(isHealthScore){
										//alert(curValueFormat)
										//clearInterval(myVar);
										$("#dvhScore").show();
										$("#hScore").html('<span>Health Score: </span>  <strong> '+$.trim(healthScore)+' </strong>');
										$("#dvScanningParam").html('Diagnosed Successfully');
										$("#dvScanComplete").show();
										$("#dvScan").hide();
										$("#myDIV").html('Device has been diagnosed successfully');
										isHealthScore=false;
									}
									else{
										if(curValueFormat.indexOf('Price_')!=-1)
										{
											var cPrice=$.trim(curValueFormat).split('_')[1];
											var cCategory=$.trim(curValueFormat).split('_')[2];
											$("#dvPriceSection").show();
											$("#dvFirstSection").hide();
											$("#dvSecondSection").hide();
											$("#myPrice").val(cPrice);
											$("#mycatagory").val(cCategory);
										}
										
										
										
										$("#otpResult").html(curValueFormat);	
										//$("#ulDigonseStatus").append('<li><img src="img/'+$.trim(data)+'".png"> <p>'+$.trim(data).replace('_Ok','').replace('_Not','')+' </p></li>')
										$("#otpResult").append('<br>');
									}
								}
							
							}
							
						});
			    }
		}

		function GetDeviceStatusForiOS()
		{
			var myVar=null;
			var isStart=false;
			  myVar = setInterval(myTimer, 500); 
			    function myTimer() {
					    $.ajax({
							type:'post',
							url:'/YaantraMobileDiagnosis/iOSServlet',
							datatype:'json',
							data:'7',
							success:function(data){
								//alert(data)
								var cPrice=0;
								$("#dvPriceSection").hide();
								$("#dvFirstSection").hide();
								$("#dvSecondSection").show();
								var prevValue=$("#otpResult").html();	
								var prevValueFormat=prevValue.replace("<br>","");
								var curValueFormat=data.replace("<br>","");
								var healthScore="";
								var isHealthScore=false;
								//alert(curValueFormat)
								if(curValueFormat.indexOf('Start')!=-1)
								{
									isStart=true;
								}
								if(curValueFormat.indexOf('Disconnected')!=-1)
								{
									window.location='index.jsp';
								}
								if(isStart)
								{
									if(curValueFormat.indexOf('Client is not ready')!=-1)
									{
										$("#dvScanningParam").html("Data syncing error due to device disconnected before")
									}
									if(curValueFormat.indexOf('Health Score')!=-1)
									{
										healthScore=$.trim(curValueFormat).split('_')[1];
										isHealthScore=true;
									}
									if(prevValue!='')
									{
										if($.trim(prevValueFormat)!=$.trim(curValueFormat))
										{
											if(curValueFormat.indexOf('_Ok')!=-1 || curValueFormat.indexOf('_Not')!=-1)
											{
												$("#ulDigonseStatus").append('<li><img src="img/'+$.trim(curValueFormat)+'.png"> <p>'+$.trim(curValueFormat).replace('_Ok','').replace('_Not','')+' </p></li>')
												$("#dvScanningParam").html('<img src="img/'+$.trim(curValueFormat)+'.png"><div>'+$.trim(curValueFormat).replace('_Ok','').replace('_Not','')+'</div>')
											}
										}
										
									}
							
									
									if(isHealthScore){
										//alert(curValueFormat)
										//clearInterval(myVar);
										$("#dvhScore").show();
										$("#hScore").html('<span>Health Score: </span>  <strong> '+$.trim(healthScore)+' </strong>');
										$("#dvScanningParam").html('Diagnosed Successfully');
										$("#dvScanComplete").show();
										$("#dvScan").hide();
										$("#myDIV").html('Device has been diagnosed successfully');
										isHealthScore=false;
									}
									else{
										if(curValueFormat.indexOf('Price_')!=-1)
										{
											var cPrice=$.trim(curValueFormat).split('_')[1];
											var cCategory=$.trim(curValueFormat).split('_')[2];
											$("#dvPriceSection").show();
											$("#dvFirstSection").hide();
											$("#dvSecondSection").hide();
											$("#myPrice").val(cPrice);
											$("#mycatagory").val(cCategory);
										}
										
										
										
										$("#otpResult").html(curValueFormat);	
										//$("#ulDigonseStatus").append('<li><img src="img/'+$.trim(data)+'".png"> <p>'+$.trim(data).replace('_Ok','').replace('_Not','')+' </p></li>')
										$("#otpResult").append('<br>');
									}
								}
							
							}
							
						});
			    }
		}
</script>


<body onload="javascript:runMyBatFileForIos()">

<div class="frame-border" id="dvFirstSection"> 
<div class="top-title">
<div class="inner-container">Developer Options</div>
</div>
<div class="inner-container">
<ul class="devl-option">
<li>If using stock <strong>Android</strong>, go to <strong>Settings</strong> > About phone > Build number > For eg. On a Samsung Galaxy device, go to Settings > About device > Build number...</li>
<li>Tap Build number seven times. ...</li>
<li>Go back to <strong>Settings</strong>, where you'll find a Developer options entry in the menu > Open developer option and USB debugging  </li>
</ul>
<div class="device-options">
<p>Select your device from below options, That you want to diagnosed. </p>

<input type="radio" name="deviceType" value="android" class="form-radio" checked><label for="radio-one">Android</label><br/>
<input type="radio" name="deviceType" value="ios" class="form-radio"><label for="radio-one">iOS</label>

  
</div>

<button type="submit" class="download-btn" id="btnInstallApp" name="button" style="width: 270px; margin-left: 35px"  >Install App</button>

<div id="myDIV" style ="display:none" align=center>Application is being installed.. please wait</div>
<h5 id="idNextStep" align=center></H5>
<div id="prevList" style="margin-top: 10px; height: 20px; text-align: center; font-weight: bold;"></div><br>
<div id="otpResult" style="margin-top: 10px; height: 20px; text-align: center; font-weight: bold;"></div><br>



</div>
</div>


<div class="frame-border" id="dvSecondSection" style="display:none;"> 
<div class="top-title">
<div class="inner-container">Yaantra Diagnosis</div>
</div>
<div class="top-scan-bg">
<div class="scan-arrow" id="dvScan"></div>
<div class="scan-completed" id="dvScanComplete" style="display:none"></div>
<div class="top-scran-text" id="dvScanningParam"></div>
 </div>

<div class="features">
<div class="inner-container">
<ul id="ulDigonseStatus">
</ul>
</div>
</div>

<div class="inner-container" id="dvhScore" style="display:none;">
<div class="health-score" id="hScore">
<span>Health Score: </span>  <strong> 10 </strong>
  </div>
  </div>
  
  
</div>

		
<div class="frame-border" id="dvPriceSection" style="display:none;"> 
<div class="top-title">
<div class="inner-container">Price Details</div>
</div>

<div class="price">
<div class="inner-container">
<div class="price-text">
<img src="img/Yaantra_logo.png">
<p>Yaantra Assured Price</p>
<span> 
	<img src="img/rupee.png">
	<input type="text" id="myPrice" readonly="readonly" style="color: black; font-weight: bold;" class="form-control"/>
</span>
</div>
</div>
</div>


<div class="inner-container">
	<div class="choose-txt"> Device Category </div>
</div>

<div class="cosmetic-div">
	<button class="consmetic-condition a"><input type="text" id="mycatagory" readonly="readonly" style="color: black; font-weight: bold;" class="form-control"/>  </button>

</div>
<div class="footer">
Copyright 2019 GadgetWood
</div>
</div>


</body>
</html>