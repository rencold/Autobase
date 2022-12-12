<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Driver edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Add driver</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit driver #${dto.id}</h1>
		</c:otherwise>
	</c:choose>


	<form class="col s12" method="post" action="/driver">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="name" required value="${dto.name}">
					<label for="name">Name</label>
				</div>
				<div class="input-field col s6">
					<input type="text" name="phoneNumber" required
						value="${dto.phoneNumber}"> <label for="phoneNumber">Phone
						Number</label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="exp" required value="${dto.exp}">
					<label for="exp">Experience</label>
				</div>
				<div class="input-field col s6">
					<input type="text" name="statement" required
						value="${dto.statement}"> <label for="statement">Statement</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/driver"><i
					class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>
