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

<portlet:renderURL var="showAddPiwikTrackingURL">
	<portlet:param name="view" value="showEditPiwikTracking" />
	<portlet:param name="backURL" value="${currentURL}" />
</portlet:renderURL>

<div class="info-box">
	<h2>Inst&auml;llningar f&ouml;r Piwik</h2>
	<p><a class="rp-link" href="${showAddPiwikTrackingURL}">L&auml;gg till ny Piwik</a></p>
	
	<c:set var="searchDelta" value="1" scope="page" />
	<c:set var="piwikTrackingsCount" value="1" scope="page" />
	
	<liferay-ui:search-container
		id="piwikTrackingContainer"
		delta="${searchDelta}"
		emptyResultsMessage="there-are-no-piwik-tracking-items"
		iteratorURL = "${iteratorURL}"
	>
	
		<liferay-ui:search-container-results
			results="${piwikTrackings}"
			total="${piwikTrackingsCount }"
		/>
		
		<liferay-ui:search-container-row
			className="se.vgregion.portal.piwiktracking.domain.jpa.PiwikTracking"
			keyProperty="id"
			modelVar="piwikTracking"
		>
		
			<portlet:renderURL var="showEditPiwikTrackingURL">
				<portlet:param name="view" value="showEditPiwikTracking" />
				<portlet:param name="backURL" value="${currentURL}" />
				<portlet:param name="piwikTrackingId" value="${piwikTracking.id}" />
			</portlet:renderURL>
	
			<liferay-ui:search-container-column-text
				name="id"
				property="id"
				href="${showEditPiwikTrackingURL}"
			/>
			
			<liferay-ui:search-container-column-text
				name="groupName"
				value="${piwikTracking.group.name}"
			/>
			
			<liferay-ui:search-container-column-text
				name="piwikTrackingCode"
				property="piwikTrackingCode"
			/>
	
			<liferay-ui:search-container-column-jsp
				path="/WEB-INF/jsp/piwik_tracking/edit_piwik_tracking_actions.jsp"
				align="right"
			/>
			
		</liferay-ui:search-container-row>
		
		<liferay-ui:search-iterator />
	
	</liferay-ui:search-container>
	
	<portlet:renderURL var="viewUrl" portletMode="view" />
	<aui:button-row>
		<aui:button type="cancel" name="cancel" onClick=" window.location = '${viewUrl}'; " />
	</aui:button-row>
</div>