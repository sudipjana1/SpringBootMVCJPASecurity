<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Search Subject By Duration</title>
</head>
<body>
	<h3>Search Subject By Duration</h3>
	<P>
		<a href="/cognizant/home">Home</a>
	</P>
	<form:form modelAttribute="subject" action="searchsubjectbyduration"
		method="get">
		<label>Subject Duration: </label>
		<form:input path="durationInHours" />
		<br />
		<input type="submit" value="Submit" />
	</form:form>
	<br>
	<P>${suberror}</P>
	<br>
	<c:if test="${empty suberror}">
		<table border="1">
			<tr>
				<th>Sub Id</th>
				<th>Title</th>
				<th>Duration</th>
			</tr>
			<c:forEach items="${subjects}" var="subject" varStatus="status">
				<tr>
					<td>${subject.subjectId}</td>
					<td>${subject.subtitle}</td>
					<td>${subject.durationInHours}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>
