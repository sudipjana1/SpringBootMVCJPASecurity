<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
	<title>Search Book By Title</title>
</head>
<body>
<h3>
Search Book By Title
</h3>
<P><a href="/cognizant/home">Home</a></P>

 <form:form modelAttribute="book" action="searchbookbytitle" method="get">
      <label >Book Title: </label>
      <form:input path="title"  />
      <br/>
      <input type="submit" value="Submit" />
    </form:form>
    <br>
        <P>${bookerror}</P>
    <br>
    <c:if test="${empty bookerror}">
    <table border="1">
    		<tr>
    		<th>Book Id</th>
    		<th>Title</th>
    		<th>Price</th>
    		<th>Volume</th>
    		<th>Pub Date</th>
    		<c:forEach items="${books}" var="ref" varStatus="status">
		<tr>
			<td>${ref.bookId}</td>
			<td>${ref.title}</td>
			<td>${ref.price}</td>
			<td>${ref.volume}</td>
			<td>${ref.publishDate}</td>
		</tr>
	</c:forEach>
    </table>
    </c:if>

</body>
</html>
