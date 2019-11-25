<%@page import="servlet.util.AppUtil"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.TimerTask"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.File"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.DataInputStream"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Yaantra</title>
<link href="/YaantraMobileDiagnosis/css/app-css.css" rel="stylesheet" type="text/css" />
</head>
<body  >
<H5>Price Page</H5>
<%
            String myCatagory = AppUtil.mycatagory;
           String myPrice = AppUtil.myPrice;
			System.out.println("mycatagory "+ myCatagory);
			System.out.println("mycatagory "+ myPrice);
			
			%>
			
			
						
						
		
<div class="frame-border"> 
<div class="top-title">
<div class="inner-container">Price Details</div>
</div>

<div class="price">
<div class="inner-container">
<div class="price-text">
<img src="img/Yaantra_logo.png">
<p>Yaantra Assured Price</p>
<span> <img src="img/rupee.png"><input type="text" name="myPrice"
								value="<%=myPrice%>" readonly="readonly"
								style="color: black; font-weight: bold;" class="form-control"/></span>
</div>
</div>
</div>


<div class="inner-container">
<div class="choose-txt"> Device Category </div>
</div>

<div class="cosmetic-div">
<button class="consmetic-condition a">   <input type="text" name="mycatagory"
								value="<%=myCatagory%>" readonly="readonly"
								style="color: black; font-weight: bold;" class="form-control"/>  </button>

</div>
<div class="footer">
Copyright 2019 GadgetWood
</div>
</div>
		
						
						
</body>
</html>