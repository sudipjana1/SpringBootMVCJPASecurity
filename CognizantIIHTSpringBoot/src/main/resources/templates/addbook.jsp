<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<title>Add Book</title>
</head>
<body>
<h3>
Add Book
</h3>
<P><a href="/cognizant/home">Home</a></P>

 <form:form modelAttribute="book" action="addbook" method="post">
  <%--     <label >Book ID: </label>
      <form:input path="bookId"  />
      <br/> --%>
		
      <label >Book Price: </label>
      <form:input path="price" />
      <br/>
      <label >Book Title: </label>
      <form:input path="title" />
      <br/>

      <label >Book Volume: </label>
      <form:input path="volume"  />
      <br/>

      <label>Publish Date: </label>
     <form:input type="date" path="publishDate"/>
      <br/>
      <input type="submit" value="Submit" />
    </form:form>
</body>
</html>
