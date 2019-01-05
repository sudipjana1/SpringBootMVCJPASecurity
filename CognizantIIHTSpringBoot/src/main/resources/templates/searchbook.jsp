<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
	<title>Search Book</title>
</head>
<body>
<h3>
Search Book
</h3>
<P><a href="/cognizant/home">Home</a></P>

 <form:form modelAttribute="book" action="searchbook" method="get">
      <label >Book ID: </label>
      <form:input path="bookId"  />
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
    		</tr>
    		<tr>
    		<td> ${book.bookId}</td>
    		<td> ${book.title}</td>
    		<td> ${book.price}</td>
    		<td> ${book.volume}</td>
    		<td> ${book.publishDate}</td>
    		</tr>
    </table>
    </c:if>

</body>
</html>
