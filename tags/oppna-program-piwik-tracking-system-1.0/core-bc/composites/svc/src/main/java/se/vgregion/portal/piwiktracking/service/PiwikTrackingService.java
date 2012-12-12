package se.vgregion.portal.piwiktracking.service;

import java.util.Collection;
import java.util.List;

import se.vgregion.portal.piwiktracking.domain.jpa.PiwikTracking;

/**
 * Service interface for managing {@link PiwikTracking}s.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public interface PiwikTrackingService {

    /**
     * Add a {@link PiwikTracking}.
     *
     * @param userId    the userId of the user who creates the {@link PiwikTracking}
     * @param companyId the companyId
     * @param groupId   the groupId
     */
    void addPiwikTracking(long userId, long companyId, long groupId);

    /**
     * Add a {@link PiwikTracking}.
     *
     * @param userId            the userId of the user who creates the {@link PiwikTracking}
     * @param companyId         the companyId
     * @param groupId           the groupId
     * @param piwikTrackingCode the tracking code for the piwik installation     
     */
    void addPiwikTracking(long userId, long companyId, long groupId, String piwikTrackingCode);

    /**
     * Find all {@link PiwikTracking}s.
     *
     * @return all {@link PiwikTracking}s.
     */
    Collection<PiwikTracking> findAll();

    /**
     * Find all {@link PiwikTracking} by class pk (id).
     *
     *@param companyId         			the companyId
     * @param id the id
     * @return {@link PiwikTracking} 	with the given id
     */
    PiwikTracking findPiwikTracking(long id);
    
    /**
     * Find all {@link PiwikTracking} for a given company.
     *
     * @param companyId the companyId
     * @return all {@link PiwikTracking} for a given company
     */
    List<PiwikTracking> findPiwikTrackingsByCompanyId(long companyId);

    /**
     * Find the first {@link PiwikTracking} for a group given a company
     *
     * @param companyId the companyId
     * @param groupId   the groupId
     * @return a {@link PiwikTracking}
     */
    PiwikTracking findPiwikTrackingByGroupId(long companyId, long groupId);
    
    /**
     * Find {@link PiwikTracking}s by company and group.
     *
     * @param companyId the companyId
     * @param groupId   the groupId
     * @return a {@link List} of {@link PiwikTracking}s
     */
    List<PiwikTracking> findPiwikTrackingsByGroupId(long companyId, long groupId);

    /**
     * Merge a {@link PiwikTracking}. Used for update operations.
     *
     * @param piwikTracking the updated {@link PiwikTracking}
     */
    PiwikTracking merge(PiwikTracking piwikTracking);
    
    /**
     * Remove a {@link PiwikTracking}.
     *
     * @param piwikTracking the {@link PiwikTracking} to remove
     */
    void remove(PiwikTracking piwikTracking);

    /**
     * Remove all {@link PiwikTracking}s.
     */
    void removeAll();

    /**
     * Set group for a {@link PiwikTracking}.
     * 
     * param a {@link PiwikTracking}
     * 
     * @return a {@link PiwikTracking}s
     */
    PiwikTracking setGroup(PiwikTracking piwikTracking);
    
    /**
     * Set group for each {@link PiwikTracking}s.
     * 
     * param a {@link List} of {@link PiwikTracking}s
     * 
     * @return a {@link List} of {@link PiwikTracking}s
     */
    List<PiwikTracking> setGroups(List<PiwikTracking> piwikTrackings);
    
    
}
