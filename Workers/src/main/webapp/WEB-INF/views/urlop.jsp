<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/js/Multiple-Dates-Picker-for-jQuery-UI/jquery-ui.multidatespicker.css"/>
<script
	src="<%=request.getContextPath()%>/resources/js/Multiple-Dates-Picker-for-jQuery-UI/jquery-ui.multidatespicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Użytkownik</title>
</head>
<body>

	<div class="top-section">
          <div class="container">
            <div class="row">
              <div class="col-sm-6">
                 <p class="no-margin">
                  Witaj 
                  <strong>
                    <c:out value = "${userLogged.firstName}"/>
		 <c:out value = "${userLogged.secondName}"/>
                              </strong>
                              <br>
                               Pozostało Ci do wykorzystania 
		 				 <strong><c:out value = "${userLogged.urlop}"/></strong> dni urlopu
                              
                          </p>
                      </div>
                      <%@ include file="/WEB-INF/views/menu.jsp"%>
                      
		
		
          
          <c:set var = "userL" scope = "session" value = "${userLogged}"/>
         <c:set var = "now" scope = "session" value = "${now}"/>
         <c:set var = "rodzaj" scope = "session" value = "${rodzaj}"/>
         <c:set var = "firstDate" scope = "session" value = "${firstDate}"/>
         <c:set var = "secondDate" scope = "session" value = "${secondDate}"/>
		

		<c:if test = "${userLogged.ranga == 1}">
		
		 
		
		<c:if test = "${send == 1}">
		<p>Pszczyna dnia: <fmt:formatDate pattern = "yyyy-MM-dd" value = "${now}" /></p>
		<p><c:out value = "${userLogged.firstName}"/> <c:out value = "${userLogged.secondName}"/></p>
		<h2>Wniosek urlopowy</h2>
		<p>Proszę o udzielenie mi urlopu <c:out value = "${rodzaj}"/> w okresie od dnia <c:out value = "${firstDate}"/>
		 do dnia <c:out value = "${secondDate}"/></p>
		 
		</c:if>
		<c:if test = "${send != 1}">
		 
		<form method="post">
		<div id="dates"></div>
		<input type="text" name='dates' id='altField'/><br>
		
		 <label for="rodzaj" class="col-md-3 control-label">Rodzaj urlopu</label>
		 <select name="rodzaj" class="col-md-3 form-control">
									
									<option value="wypoczynkowego">Wypoczynkowy</option>
									<option value="bezpłatnego">Bezpłatny</option>
									<option value="z tytułu opieki nad dzieckiem">Opieka nad dzieckiem</option>
									<option value="okolicznościowego">Okolicznościowy</option>
									</select>
		
		<input type="submit" value="Wyślij"/>
		</form>
		
		</c:if>
		</c:if>
		
	<script>
		document.addEventListener("DOMContentLoaded", function(event) {
			var date = new Date();
			$('#dates').multiDatesPicker({
				altField: '#altField',
				maxPicks: 2,
				dateFormat: "yy-m-d"
				
				
			});
		});
	</script>
</body>
</html>