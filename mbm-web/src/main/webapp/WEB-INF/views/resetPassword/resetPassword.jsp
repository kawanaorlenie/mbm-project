<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>Reset your password</h3>
<p>Enter your new password and confirm it</p>
<form:form class="rec_form" method="Post" action="/resetPassword" commandName="passwords">
   <fieldset>
      <legend>Enter your new password</legend>
      <form:password path="password" placeholder="Password" />
      <form:password path="confirmPassword" placeholder="Confirm Password" />
      <form:hidden path="email" />
      <form:hidden path="uuid"/>
      <input type="hidden"                        
        name="${_csrf.parameterName}"
        value="${_csrf.token}"/>
      <input type="submit" value="Submit" /> <br>
      <form:errors path="password" />
      <form:errors path="confirmPassword" />
   </fieldset>
</form:form>

