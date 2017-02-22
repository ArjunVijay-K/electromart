<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="/WEB-INF/views/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	
<style>
body {
   background-image: linear-gradient(#1E8449  , #F7F9F9 );
   
   
}

</style>
</head>

<body>

<div class="container" style="position:relative; top:80px;">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-success">
                <div class="panel-heading"  style="background-color:#229954;color:white">ShippingAddress</div>
                <div class="panel-body">
                    <form role="form" method="post"  >
                    
                        <label for="username">UserName</label>
                        <input type="text" id="username" class="form-control" name="username" placeholder="Your NAME">
              	          
              	         <label for="emailid">Email id</label>
                        <input type="text" id="emailid" class="form-control" name="emailid" placeholder="Your MAIL ID">
              	            
              	          
                        <label for="mobile_number" class="m-t-10">MobileNumber</label>
                        <input type="text" id="mobile_number" class="form-control" name="mobilenumber" placeholder=" Your MOBILE NUMBER">
                        
                        
                        
                        <label for="address" class="m-t-10">Address</label>
                        <input type="text" id="address" class="form-control" name="address" placeholder="Your ADDRESS">
                        
                        
                        <br>
                        <center><input type="submit" class="btn btn-success m-t-10"  name="_eventId_pay" value="Next  >>"></a></center>
                        
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>