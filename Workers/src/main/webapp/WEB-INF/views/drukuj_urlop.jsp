<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
	integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Użytkownik</title>
</head>
<body>


	<div class="container">
		<div class="row">
		<div class="col-sm-3"><center><span class="dottedUnderline">&nbsp;&nbsp;&nbsp; <c:out value="${userLogged.firstName}" />
							<c:out value="${userLogged.secondName}" /> &nbsp;&nbsp;&nbsp;</span></center></div>
		
			<div class="col-sm-6"></div>
			<div class="col-sm-3"><span class="dottedUnderline">&nbsp;&nbsp;&nbsp; Pszczyna, dn. <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />&nbsp;&nbsp;&nbsp;</span></div>

		</div>
		<div class="row">
			<div class="col-sm-3"><p class="small text-center">(Imię i nazwisko)</p></div>
			<div class="col-sm-6"></div>
			<div class="col-sm-3"><p class="small text-center">(miejscowość, data)</p></div>

		</div>
		<div style="margin-top:150px"></div>
		<div class="row">
			<div class="col-sm-12"><h1 class="text-center text-uppercase">Wniosek o urlop</h1></div>
		</div>
		<div style="margin-top:20px"></div>
		<div class="row">
			<div class="col-sm-12">
				<p class="big">
				Proszę o udzielenie mi urlopu <c:out value="${rodzaj}" /> w okresie od dnia <strong><fmt:formatDate pattern="yyyy-MM-dd" value="${firstDate}" /></strong>
							do dnia
							<strong><fmt:formatDate pattern="yyyy-MM-dd" value="${secondDate}" /></strong> włącznie, tj. <c:out value="${workDaysCounter}" /> dni roboczych
							przysługujących mi w roku <fmt:formatDate pattern="yyyy" value="${now}" />
				</p>
			</div>
		</div>
		<div style="margin-top:80px"></div>
		<div class="row">
		<div class="col-sm-8"></div>
		<div class="col-sm-4"><span class="dottedUnderline">&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
	
		</span></div>
			
		</div>
		<div class="row">
			
			<div class="col-sm-8"></div>
			<div class="col-sm-4"><p class="small text-center">(podpis pracownika)</p></div>

		</div>
		<div style="margin-top:40px"></div>
		<div class="row">
			<div class="col-sm-12">
				<p class="big">
				Wyrażam zgodę na urlop we wskazanym terminie
				</p>
			</div>
		</div>
		<div style="margin-top:80px"></div>
		<div class="row">
		<div class="col-sm-8"></div>
		<div class="col-sm-4"><span class="dottedUnderline">&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
	
		</span></div>
			
		</div>
		<div class="row">
			
			<div class="col-sm-8"></div>
			<div class="col-sm-4"><p class="small text-center">(podpis osoby upoważnionej)</p></div>

		</div>
	</div>
</body>
</html>