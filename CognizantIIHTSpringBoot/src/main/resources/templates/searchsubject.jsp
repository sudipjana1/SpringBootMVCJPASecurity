<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
	<title>Search Subject</title>
</head>
<body>
<h3>
Search Subject
</h3>
<P><a href="/cognizant/home">Home</a></P>

 <form:form modelAttribute="subject" action="searchsubject" method="get">
      <label >Subject ID: </label>
      <form:input path="subjectId"  />
      <br/>
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
    <tr>
    <td> ${subject.subjectId}</td>
    <td> ${subject.subtitle}</td>
    <td> ${subject.durationInHours}</td>
    </tr>
    </table>
    
    <table border="1">
    		<tr>
   	 	<th>Book Id</th>
    		<th>Title</th>
    		<th>Price</th>
    		<th>Volume</th>
    		<th>Pub Date</th>
    		</tr>
      
		<c:forEach items="${subject.references}" var="ref" varStatus="status">
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
