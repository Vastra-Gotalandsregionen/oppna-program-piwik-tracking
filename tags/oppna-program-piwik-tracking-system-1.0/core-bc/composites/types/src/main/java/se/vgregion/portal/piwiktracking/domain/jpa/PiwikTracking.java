package se.vgregion.portal.piwiktracking.domain.jpa;

import se.vgregion.dao.domain.patterns.entity.AbstractEntity;

import javax.persistence.*;

import org.hibernate.FetchMode;

import com.liferay.portal.model.Group;

/**
 * JPA entity class representing a piwik tracking setup.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
@Entity
@Table(name = "vgr_piwik_tracking")
public class PiwikTracking extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "group_id", unique = true)
    private long groupId;
    
    @Transient
    private Group group;

    @Column(name = "company_id")
    private long companyId;

    @Column(name = "piwik_tracking_code")
    private String piwikTrackingCode;

    @Column(name = "user_id")
    private long userId;

    /**
     * Constructor.
     */
    public PiwikTracking() {
    }

    /**
     * Constructor.
     *
     * @param userId    the userId
     * @param companyId the companyId
     * @param groupId   the groupId
     */
    public PiwikTracking(long userId, long companyId, long groupId) {
        this.userId = userId;
        this.companyId = companyId;
        this.groupId = groupId;
    }

    /**
     * Constructor.
     *
     * @param userId            the userId
     * @param companyId         the companyId
     * @param groupId           the groupId
     * @param piwikTrackingCode the piwikTrackingCode
     */
    public PiwikTracking(long userId, long companyId, long groupId, String piwikTrackingCode) {
        this.userId = userId;
        this.companyId = companyId;
        this.groupId = groupId;
        this.piwikTrackingCode = piwikTrackingCode;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    
    public Group getGroup() {
        return group;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getPiwikTrackingCode() {
        return piwikTrackingCode;
    }

    public void setPiwikTrackingCode(String piwikTrackingCode) {
        this.piwikTrackingCode = piwikTrackingCode;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public Long getId() {
        return id;
    }
}
