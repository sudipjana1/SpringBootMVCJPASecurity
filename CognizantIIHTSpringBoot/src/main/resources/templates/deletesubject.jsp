<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
	<title>Delete Subject</title>
</head>
<body>
<h3>
Delete Subject
</h3>
<P><a href="/cognizant/home">Home</a></P>

 <form:form modelAttribute="subject" action="deletesubject" method="get">
      <label >Subject ID: </label>
      <form:input path="subjectId"  />
      <br/>
      <input type="submit" value="Submit" />
    </form:form>
    <br>
        <P>${suberror}</P>
    <br>

</body>
</html>
