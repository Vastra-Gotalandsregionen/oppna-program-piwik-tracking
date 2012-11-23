package se.vgregion.portal.piwiktracking.service.repository;

import se.vgregion.dao.domain.patterns.repository.db.jpa.DefaultJpaRepository;
import se.vgregion.portal.piwiktracking.domain.jpa.PiwikTracking;

import java.util.List;

/**
 * Implementation of {@link JpaPiwikTrackingRepository}.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public class JpaPiwikTrackingRepositoryImpl extends DefaultJpaRepository<PiwikTracking, Long>
        implements JpaPiwikTrackingRepository {
	
    @Override
    public PiwikTracking findPiwikTracking(long id) {

    	PiwikTracking piwikTracking = findByPrimaryKey(id);

        return piwikTracking;
    }

    @Override
    public List<PiwikTracking> findPiwikTrackingsByCompanyId(long companyId) {
        String queryString = "SELECT n FROM PiwikTracking n WHERE n.companyId = ?1 ORDER BY n.groupId ASC";
        Object[] queryObject = new Object[]{companyId};

        List<PiwikTracking> piwikTrackings = findByQuery(queryString, queryObject);

        return piwikTrackings;
    }

    @Override
    public PiwikTracking findPiwikTrackingByGroupId(long companyId, long groupId) {

        List<PiwikTracking> piwikTrackings = findPiwikTrackingsByGroupId(companyId, groupId);
        
        PiwikTracking piwikTracking = null;
        
        if(piwikTrackings.size() > 0) {
        	piwikTracking = piwikTrackings.get(0);
        }

        return piwikTracking;
    }
    
    @Override
    public List<PiwikTracking> findPiwikTrackingsByGroupId(long companyId, long groupId) {
        String queryString = "SELECT n FROM PiwikTracking n WHERE n.companyId = ?1 AND n.groupId = ?2 ORDER BY "
                + "n.groupId ASC";

        Object[] queryObject = new Object[]{companyId, groupId};

        List<PiwikTracking> piwikTrackings = findByQuery(queryString, queryObject);

        return piwikTrackings;
    }
    
    @Override
    public List<PiwikTracking> findPiwikTrackingsByPiwikTrackingCode(long companyId, String piwikTrackingCode) {
        String queryString = "SELECT n FROM PiwikTracking n WHERE n.companyId = ?1 AND n.piwikTrackingCode = ?2 ORDER BY "
                + "n.groupId ASC";

        Object[] queryObject = new Object[]{companyId, piwikTrackingCode};

        List<PiwikTracking> piwikTrackings = findByQuery(queryString, queryObject);

        return piwikTrackings;
    }

}
