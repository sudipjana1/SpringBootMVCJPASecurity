<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h3>
			Welcome : ${pageContext.request.userPrincipal.name} | ${serverTime} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h3>
	</c:if>

<a href="user/addbook"> 1: Add Book</a>
<br>
<a href="user/searchbook"> 2: Search Book</a>
<br>
<a href="user/deletebook"> 3: Delete Book</a>
<br>
<a href="admin/addsubject"> 4: Add Subject</a>
<br>
<a href="admin/searchsubject"> 5: Search Subject</a>
<br>
<a href="admin/deletesubject"> 6: Delete Subject</a>
<br>
<a href="user/searchbookbytitle"> 7: Search Book By Title</a>
<br>
<a href="admin/searchsubjectbyduration"> 8: Search Subject By Duration</a>
<br>



</body>
</html>
