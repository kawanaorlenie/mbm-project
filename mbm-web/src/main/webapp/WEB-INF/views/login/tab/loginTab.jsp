<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:url value="/login" var="loginUrl"/>
<h3>
   <spring:message code="login.header" text="default text" />
</h3>
<p>
   <spring:message code="login.mainText" text="default text" />
</p>
   <c:if test="${param.error != null}">        
        <p>
            Invalid username and password.
        </p>
    </c:if>
    <c:if test="${param.logout != null}">       
        <p>
            You have been logged out.
        </p>
    </c:if>
<form class="login_form" name='f' action="${loginUrl}" method='POST'>
   <FIELDSET>
      <LEGEND>Log In</LEGEND>
      <c:if test="${not empty error}">
         <div class="alert alert-danger">No User with this password exists.
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
      </c:if>
      <label for="username">Login</label> <input type='text' placeholder="Login" name='username' id="username">
      <label for="password">Password</label> <input type='password' placeholder="Password" name='password'
         id="password" /> <label class="with-checkbox"> <input type="checkbox"
         name='_spring_security_remember_me' value="true" /> <spring:message code="login.rememberMe"
            text="default text" /></label>
       <input type="hidden"                        
        name="${_csrf.parameterName}"
        value="${_csrf.token}"/>
      <button name="submit" type="submit">Log In</button>
   </FIELDSET>
</form>
