<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page session="true"%>

<html lang="en">
<head>


    <title>Create an account</title>

</head>

<body>

<div class="container">

    <form:form action="saveregistration" method="POST" modelAttribute="userForm">
        <h2 class="form-signin-heading">Create your account</h2>
        <div>
                <form:input type="text" path="user.username" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
        </div>

            <div >
                <form:input type="password" path="user.password" class="form-control" placeholder="Password"></form:input>
            </div>

        <%-- <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="Confirm your password"></form:input>
                <form:errors path="passwordConfirm"></form:errors>
            </div>
        </spring:bind>  --%>
        <div>
        <select id="userRole.role" name="userRole.role">
  		 <option value="ROLE_ADMIN">Principal</option>
   		<option value="ROLE_USER">Librarian</option>
		</select> 
		</div>

        <button type="submit">Submit</button>
        <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
    </form:form>

</div>
<!-- /container -->

</body>
</html>