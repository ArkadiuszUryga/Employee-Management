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
						</strong> <br> Pozostało Ci do wykorzystania <strong><c:out
								value="${userLogged.urlop}" /></strong> dni urlopu

					</p>
				</div>
				<%@ include file="/WEB-INF/views/menu.jsp"%>




				<c:set var="userL" scope="session" value="${userLogged}" />
				<c:set var="now" scope="session" value="${now}" />
				<c:set var="rodzaj" scope="session" value="${rodzaj}" />
				<c:set var="firstDate" scope="session" value="${firstDate}" />
				<c:set var="secondDate" scope="session" value="${secondDate}" />


				<c:if test="${userLogged.ranga == 1}">
					<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-body">
					<h4>Wniosek został wysłany do rozpatrzenia</h4>
					<a href="<c:url value = "/urlop/drukuj/"/>" target="_blank" >Wydrukuj wniosek</a>
					
											</div>
							</div>
						</div>
				</c:if>

	
</body>
</html>