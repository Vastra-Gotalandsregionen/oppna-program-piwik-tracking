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

<c:if test="${piwikTracking != null}">

	<liferay-util:html-bottom>
		<script type="text/javascript" src="//${piwikBaseURL}/piwik.js"></script>
		
		<script type="text/javascript">
	
			var piwikBaseURL = '${piwikBaseURL}';
			var piwikTrackingCode = ${piwikTracking.piwikTrackingCode};
	
			initPiwik();
	
			function initPiwik() {
				try {
					var piwikTracker = Piwik.getTracker('//' + piwikBaseURL + '/piwik.php', piwikTrackingCode);
					piwikTracker.trackPageView();
					piwikTracker.enableLinkTracking();
				}
				catch( err ) {
				}
			}
		
		</script>
		<noscript><p><img src="//${piwikBaseURL}/piwik.php?idsite=${piwikTracking.piwikTrackingCode}" style="border:0" alt="" /></p></noscript>
		
	</liferay-util:html-bottom>
</c:if>