<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="investor.application.form.label.ticker" path="ticker"/>
	<acme:form-moment code="investor.application.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="investor.application.form.label.statement" path="statement"/>
	<acme:form-textbox code="investor.application.form.label.investmentOffer" path="investmentOffer"/>
	<acme:form-textbox code="investor.application.form.label.status" path="status" readonly="true"/>
		
  	<acme:form-return code="investor.application.form.button.return"/>
</acme:form>
	
	