<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h3>Forgot your password?</h3>
<p>Just enter the same e-mail address you used in registration
	process, click 'send' and follow step-by-step instructions to regain
	account access.</p>
<form id="passwordRecoveryForm" class="rec_form" method="Post" action="/forgot" >
	<fieldset>
		<LEGEND>Password Recovery</LEGEND>
		<label for="email">E-mail adress</label>
		<input class="recovery" placeholder="E-mail adress" id="email" name="email"/>
		     <input type="hidden"                        
        name="${_csrf.parameterName}"
        value="${_csrf.token}"/>
		<button name="submit" type="submit">Send</button>
	</fieldset>
</form>
