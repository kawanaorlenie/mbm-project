<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="hello-user">
   <sec:authorize access="isAuthenticated()">
			Hello, <sec:authentication property="principal.username" />!
   </sec:authorize>
   <sec:authorize access="isAnonymous()"> 
         You are not logged in. 
   </sec:authorize>
</div>
<div class=" menu">
   <sec:authorize access="isAuthenticated()">
      <ul>
         <li><a href="<c:url value="/logout" />">Logout</a></li>
         <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="<c:url value="/admin" />">Admin Panel</a></li>
         </sec:authorize>
      </ul>
   </sec:authorize>
</div>
<div class=" language">
   <label>Language: </label> <select id="languageSelect" class="ui-button ui-widget ui-state-default ui-corner-all">
      <option value="en">English</option>
      <option value="pl">Polski</option>
   </select>
</div>
