<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Cars" scope="application" />
<t:wrapper>
	<h1>Cars</h1>
	<div class="row">
		<div class="col s12">
			<div class="center-align">
				<a class="btn-floating btn-large waves-effect waves-light" href="/car?view=edit"><i class="material-icons">add</i></a>
			</div>
		</div>
	</div>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Number</th>
				<th>Brand</th>
				<th>Model</th>
				<th>DriveUnit</th>
				<th>Engine</th>
				<th>Transmission</th>
				<th>Driver Name</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${list}" varStatus="loopCounter">
				<tr>
					<td><c:out value="${entity.id}" /></td>
					<td><c:out value="${entity.number}" /></td>
					<td><c:out value="${entity.brand}" /></td>
					<td><c:out value="${entity.model}" /></td>
					<td><c:out value="${entity.driveUnit}" /></td>
					<td><c:out value="${entity.engine}" /></td>
					<td><c:out value="${entity.transmission}" /></td>
					<td><c:out value="${entity.driverId}" /></td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="/car?view=edit&id=${entity.id}"><i
							class="material-icons">edit</i></a><a class="btn-small btn-floating waves-effect waves-light red" title="удалить"
						onclick="sendHTTPDelete('/car?id=${entity.id}')"><i class="material-icons">delete</i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</t:wrapper>