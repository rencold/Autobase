<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Requests" scope="application" />
<c:set var="pageUrl" value="/request" scope="page" />
<t:wrapper>
	<h1>Requests</h1>
	<div class="row">
		<div class="col s12">
			<div class="center-align">
				<a class="btn-floating btn-large waves-effect waves-light"
					href="/request?view=edit"><i class="material-icons">add</i></a>
			</div>
		</div>
	</div>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Customer's Name</th>
				<th>Phone Number</th>
				<th>Run ID</th>
				<th>Car Number</th>
				<th>Statement</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.custName}" /></td>
					<td><c:out value="${entity.phoneNumber}" /></td>
					<td><c:out value="${entity.runId}" /></td>
					<td><c:out value="${entity.carNumber}" /></td>
					<td><c:out value="${entity.statement}" /></td>
					<td><a
						class="btn-small btn-floating waves-effect waves-light blue"
						title="редактировать" href="/request?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a
						class="btn-small btn-floating waves-effect waves-light red"
						title="удалить"
						onclick="sendHTTPDelete('/request?id=${entity.id}')"><i
							class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</t:wrapper>