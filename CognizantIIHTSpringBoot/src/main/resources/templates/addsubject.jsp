<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
<head>
	<title>Add Subject</title>
</head>
<body>
<h3>
Add Subject
</h3>
<P><a href="/cognizant/home">Home</a></P>

 <form:form modelAttribute="subject" action="addsubject" method="post">
<!--       <label >Subject ID: </label>
      <input name="subjectId"  />
      <br/> -->
		
      <label >Subject Title: </label>
      <input name="subtitle" />
      <br/>
      <label >Duration : </label>
      <input name="durationInHours" />
      <br/>


      <br/>
      
		<c:forEach items="${subject.references}" var="ref" varStatus="status">
		    <label >Book Ref ${status.count} : </label>
		
			<input name="references[${status.index}].bookId" value="${ref.bookId}"/>
			<br>
<%-- 			<td><input name="references[${status.index}].title" value="${ref.title}"/></td>
			<td><input name="references[${status.index}].price" value="${ref.price}"/></td>
			<td><input name="references[${status.index}].volume" value="${ref.volume}"/></td>
			<td><input name="references[${status.index}].publishDate" value="${ref.publishDate}"/></td> --%>
						
			
		
	</c:forEach>
	
      <input type="submit" value="Submit" />
    </form:form>
</body>
</html>
