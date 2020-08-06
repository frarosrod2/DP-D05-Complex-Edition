<%@page language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="authenticated.investmentRound.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.round" path="round"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textarea code="authenticated.investmentRound.form.label.description" path="description"/>
	<acme:form-money code="authenticated.investmentRound.form.label.money" path="money"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.link" path="link"/>
	<acme:form-panel code="authenticated.investmentRound.form.label.workProgramme">
		<table class="table table-striped">	
			<tbody>
				<c:forEach items="${workProgramme}" var="activity">
						<tr>
							<td>${activity.title}</td>
						</tr>
			</c:forEach>
			</tbody>
		</table>
	</acme:form-panel>
	<acme:form-return code="authenticated.investmentRound.form.button.return" />	
</acme:form> 