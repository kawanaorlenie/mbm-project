<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<h3>
	<spring:message code="signup.header" text="Registration" />
</h3>
<p>
	<spring:message code="signup.mainText" text="Fill up this form to register new account" />
</p>
<form id="registerForm" class="signup_form" method="POST" action="/register">
	<fieldset>
		<legend>Registration</legend>
		<%-- <form:errors path="*" class="alert alert-danger" /> --%>
		<label for="name">User Name</label> 
		<input type="text" class="signup" name="name" /> 
		
		<label for="password">Password</label> 
		<input type="password" class="signup" name="password" /> 
		
		<label for="confirmPassword">Confirm Password</label> 
		<input type="password" class="signup" name="confirmPassword" /> 
		
		<label for="email">E-mail</label>
		<input type="text" class="signup" name="email" /> 
		
		<label class="with-checkbox">
		<input type="checkbox"> <spring:message code="signup.accept" text="I accept" /><a href=""> <spring:message code="signup.terms" text="the terms" /></a></label>
		
		<button name="submit" type="submit">
			<spring:message code="signup.signUpButton" text="Register" />
		</button>
	</fieldset>
</form>
