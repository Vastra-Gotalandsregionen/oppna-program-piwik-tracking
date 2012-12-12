<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<portlet:defineObjects />

<portlet:actionURL name="savePiwikTrackingSettings" var="savePiwikTrackingSettingsURL">
</portlet:actionURL>

<div class="info-box">
	<h2>Inst&auml;llningar f&ouml;r Piwik</h2>
	<h3>F&ouml;r siten ${piwikTracking.group.name}</h3>

	<aui:form  action="${savePiwikTrackingSettingsURL}" name="savePiwikTrackingForm" method="post" cssClass="edit-piwik-tracking">
	
		<c:if test="${piwikTracking.id != null}">
			<aui:input name="piwikTrackingId" type="hidden" />
		</c:if>
	
		<aui:select label="site" name="groupId">
			<aui:option value="0">
				- <liferay-ui:message key="choose-site" />
			</aui:option>
			<c:forEach var="group" items="${companyGroups}">
				<c:set var="optionSelected" value="false" scope="page" />
					<c:if test="${group.groupId eq piwikTracking.groupId}">
						<c:set var="optionSelected" value="true" scope="page" />
					</c:if>
				<aui:option value="${group.groupId}" selected="${optionSelected}">${group.name}</aui:option>									
			</c:forEach>
		</aui:select>
		
		<aui:input name="piwikTrackingCode" type="text" value="${piwikTracking.piwikTrackingCode}" />
		
		<aui:button-row>
			<aui:button type="submit" name="save" />
			<aui:button type="cancel" name="cancel" onClick=" window.location = '${backURL}'; " />
		</aui:button-row>	
	
	</aui:form>

</div>