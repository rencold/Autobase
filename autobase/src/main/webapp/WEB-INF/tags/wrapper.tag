<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title><c:out value="${pageTitle}" /></title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<script src="js/helpers.js"></script>
</head>


<nav class="teal darken-3" role="navigation">
	<div class="nav-wrapper container">
		<a id="logo-container" href="index.jsp" class="brand-logo">Autobase</a>
		<ul class="brand-logo center">
			<li><a href="request">Requests</a></li>
			<li><a href="driver">Drivers</a></li>
			<li><a href="car">Cars</a></li>
			<li><a href="run">Runs</a></li>
		</ul>
		<ul class="right hide-on-med-and-down">
		<li><a class="waves-effect waves-light btn">Logout</a></li>
		</ul>
	</div>
</nav>


<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		<jsp:doBody />
	</div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</html>