<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Request edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create request</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit request #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/request">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />

			<div class="row">
				<div class="input-field col s12">
					<input type="text" name="statement" value="${dto.statement}">
					<label for="statement">Statement</label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="custName" value="${dto.custName}">
					<label for="custName">Customer Name</label>
				</div>
				<div class="input-field col s6">
					<input type="text" name="phoneNumber" value="${dto.phoneNumber}">
					<label for="phoneNumber">Phone Number</label>
				</div>
			</div>

			<div class="row">
				<div class="col s6">
					<label for="runId">Run ID</label> 
					<select name="runId"	class="browser-default" required>
						<option value="">--SELECT RUN NUMBER--</option>
						<c:forEach items="${allRuns}" var="run">
							<option value="${run.id}"
								<c:if test="${run.id eq dto.runId}">selected="selected"</c:if>>${run.id}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col s6">
					<label for="carId">Car ID</label> <select name="carId"
						class="browser-default" required>
						<option value="">--select car number--</option>
						<c:forEach items="${allCars}" var="car">
							<option value="${car.id}"
								<c:if test="${car.id eq dto.carId}">selected="selected"</c:if>>${car.number}</option>
						</c:forEach>
					</select>
				</div>
			</div>

		</div>

		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/request"><i
					class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>