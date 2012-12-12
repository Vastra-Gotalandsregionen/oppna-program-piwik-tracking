package se.vgregion.portal.piwiktracking.util;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;

public final class PiwikTrackingUtil {

    private static final Log LOGGER = LogFactoryUtil.getLog(PiwikTrackingUtil.class);

    private PiwikTrackingUtil() {}
    
    /**
     * Get the {@link Group}s within a company.
     *
     * @param companyId the companyId
     * @return a {@link List} of {@link Group}s
     */
    public static List<Group> getCompanyGroups(long companyId) {

    	ArrayList<Group> filteredGroups = new ArrayList<Group>();
    	
        try {
            int companyGroupsCount = GroupLocalServiceUtil.getCompanyGroupsCount(companyId);

            List<Group> companyGroups = GroupLocalServiceUtil.getCompanyGroups(companyId, 0, companyGroupsCount);
            
            for(Group group : companyGroups) {
            	
            	if(group.getType() != 0) {
            		filteredGroups.add(group);
            	}
            }

            return filteredGroups;
        } catch (SystemException e) {
            LOGGER.error(e, e);
        }

        return filteredGroups;
    }

}
