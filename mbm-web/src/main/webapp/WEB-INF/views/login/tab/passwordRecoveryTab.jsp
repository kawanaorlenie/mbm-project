<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h3>Forgot your password?</h3>
<p>Just enter the same e-mail address you used in registration
	process, click 'send' and follow step-by-step instructions to regain
	account access.</p>
<form:form class="rec_form" method="Post" action="forgot"
	commandName="forgotPassword">
	<fieldset>
		<LEGEND>Password Recovery</LEGEND>
		<form:errors path="email" class="alert alert-danger" />
		<label for="email">E-mail adress</label>
		<form:input class="recovery" placeholder="E-mail adress" path="email" />
		<button name="submit" type="submit">Send</button>
	</fieldset>
</form:form>
