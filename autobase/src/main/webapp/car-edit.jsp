<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Car edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create car</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit car #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/car">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="number" value="${dto.number}"> <label for="number">Number</label>
				</div>
			
			
				<div class="input-field col s6">
					<input type="text" name="brand" value="${dto.brand}"> <label for="brand">Brand</label>
				</div>
			</div>
			
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="model" value="${dto.model}"> <label for="model">Model</label>
				</div>
				<div class="input-field col s6">
					<input type="text" name="driveUnit" value="${dto.driveUnit}"> <label for="driveUnit">Drive Unit</label>
				</div>
			</div>
			
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="engine" value="${dto.engine}"> <label for="engine">Engine</label>
				</div>
			
				<div class="input-field col s6">
					<input type="text" name="transmission" value="${dto.transmission}"> <label for="transmission">Transmission</label>
				</div>
			</div>
			<div class="row">
				<div class="col s12">
					<label for="driverId">Driver ID</label> 
					<select name="driverId" class="browser-default center" required>
						<option value="">--select driver-</option>
						<c:forEach items="${allDrivers}" var="driver">
							<option value="${driver.id}" <c:if test="${driver.id eq dto.driverId}">selected="selected"</c:if>>${driver.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>	
			</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/car"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>
