package se.vgregion.portal.piwiktracking.service.repository;

import java.util.List;

import se.vgregion.dao.domain.patterns.repository.Repository;
import se.vgregion.portal.piwiktracking.domain.jpa.PiwikTracking;

/**
 * Repository interface for managing {@code PiwikTracking}s.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public interface PiwikTrackingRepository extends Repository<PiwikTracking, Long> {

    /**
     * Find {@link PiwikTracking}s for a company with the given primary key
     *
     * @param companyId the companyid
     * @param id (the primary key) of the {@link PiwikTracking}
     * @return a {@link PiwikTracking}
     */
    PiwikTracking findPiwikTracking(long id);
	
    /**
     * Find all {@link PiwikTracking}s for a company.
     *
     * @param companyId the companyid
     * @return a {@link List} of {@link PiwikTracking}s
     */
    List<PiwikTracking> findPiwikTrackingsByCompanyId(long companyId);

    /**
     * Find the first {@link PiwikTracking} for a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @return a {@link PiwikTracking}
     */
    PiwikTracking findPiwikTrackingByGroupId(long companyId, long groupId);
    
    /**
     * Find all {@link PiwikTracking}s for a group in a company.
     *
     * @param companyId the companyid
     * @param groupId   the groupId
     * @return a {@link List} of {@link PiwikTracking}s
     */
    List<PiwikTracking> findPiwikTrackingsByGroupId(long companyId, long groupId);

    /**
     * Find all {@link PiwikTracking}s with the specific PiwikTrackingCode within a company.
     *
     * @param companyId the companyid
     * @param piwikTracking  the Piwik Tracking Code for a {@link PiwikTracking}
     * @return a {@link List} of {@link NavigationSite}s
     */
    List<PiwikTracking> findPiwikTrackingsByPiwikTrackingCode(long companyId, String piwikTrackingCode);
}
