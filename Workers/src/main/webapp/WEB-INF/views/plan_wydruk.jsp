<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.List"%>
<%@ page import="pl.com.meridium.entity.Dates"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
	integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>


<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/js/Multiple-Dates-Picker-for-jQuery-UI/jquery-ui.multidatespicker.css" />
<script
	src="<%=request.getContextPath()%>/resources/js/Multiple-Dates-Picker-for-jQuery-UI/jquery-ui.multidatespicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Plan pracy - wydruk</title>
</head>
<body>
	<div class="container">
		<c:if test="${userLogged.ranga == 2}">
			<div class="row">
				<div class="col-6">
					<h4>Ewidencja czasu pracy</h4>
				</div>
				<div class="col-3">
				</div>
				<div class="col-3">
					<h4>
						<c:out value="${m}" />
						,
						<c:out value="${year}" />
					</h4>
				</div>
			</div>

			<div class="row">
				<div class="col-6">
					<h5>
						<c:out value="${userToShow.firstName}" />
						<c:out value="${userToShow.secondName}" />
					</h5>
				</div>

			</div>
			<c:set var="etat" value="${12/userToShow.etat}" />
			<div class="row">
				<div class="col-9">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td rowspan="3">Wymiar etatu</td>
								<td rowspan="3" colspan="2">1/<fmt:formatNumber type = "number" maxIntegerDigits = "1" value = "${etat}" /></td>
								<td colspan="6">Czas pracy</td>
							</tr>
							<tr>
								<td colspan="3"><strong><c:out value="${workDaysCounter}" /></strong></td>
								<td colspan="2"><strong><c:out value="${monthHours}" /></strong></td>
							</tr>
							<tr>
								<td colspan="3">dni robocze</td>
								<td colspan="2">godziny</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<table class="table table-bordered">
						<tbody>
						<tr>
							<td rowspan="2">dzień</td>
							<td colspan="8"><strong>Czas pracy</strong></td>
							<td></td>
							<td colspan="2">Liczba godzin urlopu</td>
						</tr>
						<tr>
							
							<td colspan="2">Rozpoczęcie</td>
							<td colspan="2">Zakończenie</td>
							<td>liczba godzin</td>
							<td colspan="3">Podpis</td>
							<td></td>
							<td>z poprzedniego roku</td>
						<td>z bieżącego roku</td>
						</tr>
						<%
						
						%>
						</tbody>
					</table>
				</div>
				

			</div>
		</c:if>
	</div>

</body>
</html>