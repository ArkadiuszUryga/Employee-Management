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
<title>Użytkownik</title>
</head>
<body>
	<div class="top-section">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<p class="no-margin">
						Witaj <strong> <c:out value="${userLogged.firstName}" />
							<c:out value="${userLogged.secondName}" />
						</strong><br> <br> &nbsp;&nbsp;Umowa do:<strong> <fmt:formatDate pattern="yyyy-MM-dd" value="${userLogged.umowa}" /></strong><br>
						&nbsp;&nbsp;Następne badania okresowe: <strong> <fmt:formatDate
								pattern="yyyy-MM-dd" value="${userLogged.badania}" /></strong><br>
						&nbsp;&nbsp;Dzienny wymiar pracy: <strong><c:out
								value="${userLogged.defaultHour}" /> </strong>godzin<br>
						&nbsp;&nbsp;pozostały urlop:<strong> <c:out
								value="${userLogged.urlop}" />
						</strong>dni

					</p>
				</div>
				<%@ include file="/WEB-INF/views/menu.jsp"%>

				<c:set var="userL" scope="session" value="${userLogged}" />
				<c:set var="hours" scope="session" value="${userL.defaultHour}"/>


				<c:if test="${userLogged.ranga == 3}">
					<a href="<c:url value = "/user/add/"/>"
						class="btn btn-primary btn-lg btn-block">Dodaj użytkownika </a>
					<a href="<c:url value = "/users/"/>" class="btn btn-primary btn-lg btn-block">Albo
						usuń, jeśli masz taką chęć</button>
				</c:if>

				<c:if test="${userLogged.ranga == 2}">
					<div class="modal-dialog1">
						<div class="modal-content1">
							<div class="modal-body">
								<div class="text-center">

									<h4>Lista pracowników</h4>
									<table class="table table-sm table-secondary">
										<tr class="table-secondary">
											<th>Imię</th>
											<th>Nazwisko</th>
											
											<th>etat</th>
											<th>Wymiar urlopu</th>
											<th>Pozostały urlop</th>
											<th>Umowa do:</th>
											<th>Badania do:</th>
											<th>Szczegóły</th>
											<th>Wyślij wiadomość</th>
										</tr>
										<c:forEach items="${workers}" var="worker">
											<tr>
												<td><c:out value="${worker.firstName}" /></td>
												<td><c:out value="${worker.secondName}" /></td>
												<c:set var="etat" scope="session" value="${12/worker.etat}" />
												
												<td>1/<fmt:formatNumber type = "number" maxIntegerDigits = "1" value = "${etat}" /></td>
												<td><c:out value="${worker.wymiar_urlopu}" /></td>
												<td><c:out value="${worker.urlop}" /></td>
												<td><fmt:formatDate pattern="yyyy-MM-dd"
														value="${worker.umowa}" /></td>
												<td><fmt:formatDate pattern="yyyy-MM-dd"
														value="${worker.badania}" /></td>
														<td><a	href="<c:url value = "/worker/${worker.id}/"/>">Szczegóły</a></td>
														<td><a	href="<c:url value = "/send_message/${worker.id}/"/>">Wiadomość</a></td>
											</tr>
										</c:forEach>
									</table>

									
								</div>
							</div>
						</div>
					</div>

				</c:if>
				<c:if test="${userLogged.ranga == 1}">


					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-body">
								<div class="text-center">

									<h4>Twój miesięczny plan pracy</h4>
									<c:if test="${isPlanSended == true}">
										<p>Plan na następny miesiąc został wysłany i oczekuje na zatwierdzenie</p>
									</c:if>
									<c:if test="${isPlanSended == false}">
									<p>Następny miesiąc to: <c:out value="${m}" />
									dni w następnym m-cu: <c:out value="${lastDay}" />
									w tym roboczych: <c:out value="${workDaysCounter}" />, co daje 
									
									
									<c:out value="${monthHours}" /> godzin do przepracowania.<br>
									Z zeszłego miesiąca zostało Ci <c:out value="${hoursFromLastMonth}" /> godzin, czyli faktycznie do przepracowania
									jest <c:out value="${finalHours}" /> godzin. <br>
									Musisz zaznaczyć w następnym m-cu <c:out value="${days}" /> dni, a na kolejny zostanie <c:out value="${hoursForNextMonth}" /> godzin. 
									
									
									
									</p>
									</c:if>
									<form method="post">
										<center>
											<div id="dates"></div>
										</center>
										<input type="text" name='dates' id='altField' class="m-3" /><br>
										<input type="submit" class="m-3" value="Wyślij" />
									</form>
								</div>
							</div>
						</div>
					</div>


				</c:if>

				<script>
		document.addEventListener("DOMContentLoaded", function(event) {
			var date = new Date();
			$('#dates').multiDatesPicker({
				altField: '#altField',
				beforeShowDay: $.datepicker.noWeekends,
				dateFormat: "yy-m-d",
				
				<c:if test="${dates.size() > 0}">
					addDates: [
						<%Calendar cal = Calendar.getInstance();
				List<Dates> dates = (List<Dates>) request.getAttribute("dates");
				
				for (Dates dt : dates) {
					cal.setTime(dt.getDate());
					out.print("date.setDate(" + cal.get(Calendar.DAY_OF_MONTH) + "), ");
					
				}%>
					],
					
				</c:if>
				<c:set var="picks" scope="session" value="${days}" />
				<%
				
				List<Dates> dates = (List<Dates>) request.getAttribute("dates");
				for (Dates dt : dates) {
					%>
					<c:set var="picks" scope="session" value="${picks+1}" />
				<%	
				}
				%>
				
			maxPicks : <c:out value="${picks}" />
				
			});
		});
	</script>
</body>
</html>