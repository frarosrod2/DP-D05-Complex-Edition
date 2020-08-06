<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="bookkeeper.accountingRecord.form.label.title" path="title"/>
	<acme:form-textbox code="bookkeeper.accountingRecord.form.label.status" path="status"/>
	<acme:form-textbox code="bookkeeper.accountingRecord.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textarea code="bookkeeper.accountingRecord.form.label.body" path="body"/>
  	
  	<acme:form-return code="bookkeeper.accountingRecord.form.button.return"/>
</acme:form>
