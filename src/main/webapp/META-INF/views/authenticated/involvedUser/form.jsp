<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

	<acme:form>
		<jstl:if test="${command == 'show'}">
			<acme:form-textbox readonly="true" code="authenticated.involvedUser.form.label.authenticatedUserName" path="authenticatedUserName"/>
			<acme:form-submit code="authenticated.involvedUser.form.button.delete" action="/authenticated/involvedUser/delete?id=${id}"/>
			
		</jstl:if>
		
		<jstl:if test="${command == 'create'}">
			<acme:form-textbox code="authenticated.involvedUser.form.label.searchUser" path="searchUser"/>
			<acme:form-submit code="authenticated.thread.form.button.create" action="/authenticated/involved-user/create?forumId=${param.forumId}"/>
		</jstl:if>
			
			<acme:form-submit test="${command == 'delete'}" code="authenticated.involvedUser.form.button.delete" action="/authenticated/involvedUser/delete?id=${id}"/>
			<acme:form-return code="authenticated.thread.form.button.return"/>
	</acme:form>