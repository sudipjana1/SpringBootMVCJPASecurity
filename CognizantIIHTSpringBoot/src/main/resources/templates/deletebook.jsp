<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
	<title>Delete Book</title>
</head>
<body>
<h3>
Delete Book
</h3>
<P><a href="/cognizant/home">Home</a></P>

 <form:form modelAttribute="book" action="deletebook" method="get">
      <label >Book ID: </label>
      <form:input path="bookId"  />
      <br/>
      <input type="submit" value="Submit" />
    </form:form>
    <br>
        <P>${bookerror}</P>
    <br>

</body>
</html>
