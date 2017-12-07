<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pracownicy - logowanie</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body>

<div class="container" style="background-image: url('http://www.qrpatrol.com/sites/default/files/all-lone-workers.png') no-repeat center center fixed">    
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                    	<div><h2>System zarządzania pracownikami w firmie</h2></div>
                        <div class="panel-title">Zaloguj się</div>
                        <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Zapomniane hasło?</a></div>
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                         <form method="post" id="loginform" class="form-horizontal" role="form">
                         
                       <!--  <form id="loginform" class="form-horizontal" role="form"> -->
                                    
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                      <input id="name" type="text" class="form-control" name="user" value="" placeholder="e-mail"> 
                                                                      
                                    </div>
                                
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                         <input id="login-password" type="password" class="form-control" name="password" placeholder="hasło">
                                       
                                    </div>
                                    

                                <!-- 
                            <div class="input-group">
                                      <div class="checkbox">
                                        <label>
                                          <input id="login-remember" type="checkbox" name="remember" value="1"> Zapamiętaj mnie
                                        </label>
                                      </div>
                                    </div>
                                    
                                     -->
							

                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->

                                    <div class="col-sm-12 controls">
                                    	<input type="submit" class="btn btn-success" value="Login">
                                    	
                                      <!-- <a id="btn-login" href="#" class="btn btn-success">Login  </a>  -->
                                      

                                    </div>
                                </div>


                                
                           </form>     

					

                        </div>  
                        <a id="btn-login" href="user_logged/admin" class="btn btn-primary btn-sm">Admin  </a>  
                        <a id="btn-login" href="user_logged/kadry" class="btn btn-primary btn-sm">Kadry  </a>  
                        <a id="btn-login" href="user_logged/user" class="btn btn-primary btn-sm">Pracownik  </a>               
                    </div>  
        </div>
        
    </div>
</body>
</html>
