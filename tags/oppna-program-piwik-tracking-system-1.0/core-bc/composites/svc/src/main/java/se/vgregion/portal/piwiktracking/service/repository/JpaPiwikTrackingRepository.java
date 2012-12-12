package se.vgregion.portal.piwiktracking.service.repository;

import se.vgregion.dao.domain.patterns.repository.db.jpa.JpaRepository;
import se.vgregion.portal.piwiktracking.domain.jpa.PiwikTracking;

/**
 * JPA Repository interface for managing {@link PiwikTracking}s.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
public interface JpaPiwikTrackingRepository extends JpaRepository<PiwikTracking, Long, Long>,
        PiwikTrackingRepository {

}
