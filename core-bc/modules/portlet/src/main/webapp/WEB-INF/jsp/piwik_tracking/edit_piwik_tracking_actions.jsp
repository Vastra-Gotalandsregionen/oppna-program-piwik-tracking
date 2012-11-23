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

<c:set var="searchContainerRow" value="${SEARCH_CONTAINER_RESULT_ROW}" scope="page" />
<c:set var="piwikTracking" value="${searchContainerRow.object}" scope="page" />

<liferay-ui:icon-menu cssClass="">

	<portlet:renderURL var="showEditPiwikTrackingURL">
		<portlet:param name="piwikTrackingId" value="${piwikTracking.id}" />
		<portlet:param name="view" value="showEditPiwikTracking" />
		<portlet:param name="backURL" value="${currentURL}" />
	</portlet:renderURL>

	<liferay-ui:icon image="edit" url="${showEditPiwikTrackingURL}" />

	<portlet:actionURL name="deletePiwikTracking" var="deletePiwikTrackingURL">
		<portlet:param name="piwikTrackingId" value="${piwikTracking.id}" />
	</portlet:actionURL>
	
	<liferay-ui:icon-delete label="delete" url="${deletePiwikTrackingURL}"  />
	
</liferay-ui:icon-menu>