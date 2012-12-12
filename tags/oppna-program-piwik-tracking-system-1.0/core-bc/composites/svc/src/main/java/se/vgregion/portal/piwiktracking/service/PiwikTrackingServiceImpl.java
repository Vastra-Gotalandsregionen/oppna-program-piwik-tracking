package se.vgregion.portal.piwiktracking.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;

import se.vgregion.portal.piwiktracking.domain.jpa.PiwikTracking;

import se.vgregion.portal.piwiktracking.service.repository.PiwikTrackingRepository;

/**
 * Implementation of {@link PiwikTrackingService}.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public class PiwikTrackingServiceImpl implements PiwikTrackingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PiwikTrackingService.class.getName());
    private PiwikTrackingRepository piwikTrackingRepository;

    /**
     * Constructor.
     *
     * @param piwikTrackingRepository the {@link PiwikTrackingRepository}
     */
    @Autowired
    public PiwikTrackingServiceImpl(PiwikTrackingRepository piwikTrackingRepository) {
        this.piwikTrackingRepository = piwikTrackingRepository;
    }

    @Override
    public PiwikTracking findPiwikTracking(long id) {
    	PiwikTracking piwikTracking = piwikTrackingRepository.findPiwikTracking(id);
    	return setGroup(piwikTracking);
    }
    
    @Override
    public List<PiwikTracking> findPiwikTrackingsByCompanyId(long companyId) {
        List<PiwikTracking> piwikTrackings = piwikTrackingRepository.findPiwikTrackingsByCompanyId(companyId);
        return setGroups(piwikTrackings);
    }

    @Override
    public PiwikTracking findPiwikTrackingByGroupId(long companyId, long groupId) {
    	PiwikTracking piwikTracking = piwikTrackingRepository.findPiwikTrackingByGroupId(companyId, groupId);
    	return setGroup(piwikTracking);
    }
    
    @Override
    public List<PiwikTracking> findPiwikTrackingsByGroupId(long companyId, long groupId) {
    	List<PiwikTracking> piwikTrackings = piwikTrackingRepository.findPiwikTrackingsByGroupId(companyId, groupId);
    	return setGroups(piwikTrackings);
    }

    @Override
    @Transactional
    public void addPiwikTracking(long userId, long companyId, long groupId) {
        PiwikTracking piwikTracking = new PiwikTracking(userId, companyId, groupId);
        piwikTrackingRepository.merge(piwikTracking);
    }

    @Override
    @Transactional
    public void addPiwikTracking(long userId, long companyId, long groupId, String piwikTrackingCode) {
        PiwikTracking piwikTracking = new PiwikTracking(userId, companyId, groupId, piwikTrackingCode);
        piwikTrackingRepository.merge(piwikTracking);
    }

    @Override
    public Collection<PiwikTracking> findAll() {
        return piwikTrackingRepository.findAll();
    }

    @Override
    @Transactional
    public PiwikTracking merge(PiwikTracking piwikTracking) {
    	return piwikTrackingRepository.merge(piwikTracking);
    } 
    
    @Override
    @Transactional
    public void remove(PiwikTracking piwikTracking) {
    	PiwikTracking piwikTrackingToDelete = piwikTrackingRepository.find(piwikTracking.getId());
        piwikTrackingRepository.remove(piwikTrackingToDelete);
    }

    @Override
    @Transactional
    public void removeAll() {
        Collection<PiwikTracking> all = piwikTrackingRepository.findAll();
        for (PiwikTracking piwikTracking : all) {
        	PiwikTracking piwikTrackingToDelete = piwikTrackingRepository.find(piwikTracking.getId());
            piwikTrackingRepository.remove(piwikTrackingToDelete);
        }
    }
    
    @Override
    public PiwikTracking setGroup(PiwikTracking piwikTracking) {
    		
    		if(piwikTracking != null) {
        		try {
    				Group group = GroupLocalServiceUtil.getGroup(piwikTracking.getGroupId());
    				piwikTracking.setGroup(group);
    			} catch (PortalException e) {
    				LOGGER.error(e.getMessage(), e);
    			} catch (SystemException e) {
    				LOGGER.error(e.getMessage(), e);
    			}
    		}

    	return piwikTracking;
    }
    
    @Override
    public List<PiwikTracking> setGroups(List<PiwikTracking> piwikTrackings) {
    	
    	ArrayList<PiwikTracking> piwikTrackingsWithGroups = new ArrayList<PiwikTracking>();
    	
    	for(PiwikTracking piwikTracking : piwikTrackings) {
    		PiwikTracking piwikTrackingWithGroup = setGroup(piwikTracking);
    		piwikTrackingsWithGroups.add(piwikTrackingWithGroup);
    	}
    	return piwikTrackingsWithGroups;
    }

}
