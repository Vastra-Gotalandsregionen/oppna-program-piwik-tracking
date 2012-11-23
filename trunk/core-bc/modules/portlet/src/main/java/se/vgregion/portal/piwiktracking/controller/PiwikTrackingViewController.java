package se.vgregion.portal.piwiktracking.controller;

import java.util.List;
import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import se.vgregion.portal.piwiktracking.domain.jpa.PiwikTracking;
import se.vgregion.portal.piwiktracking.service.PiwikTrackingService;
import se.vgregion.portal.piwiktracking.util.PiwikTrackingConstants;
import se.vgregion.portal.piwiktracking.util.PiwikTrackingUtil;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

/**
 * Controller class for the view mode in piwik tracking portlet.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
@Controller
@RequestMapping(value = "VIEW")
public class PiwikTrackingViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PiwikTrackingViewController.class.getName());
    private PiwikTrackingService piwikTrackingService;

    /**
     * Constructor.
     *
     * @param piwikTrackingService the {@link PiwikTrackingService}
     */
    @Autowired
    public PiwikTrackingViewController(PiwikTrackingService piwikTrackingService) {
        this.piwikTrackingService = piwikTrackingService;
    }

    /**
     * The default render method. Load the correct {@link PiwikTracking}s and add as request attributes.
     *
     * @param request  the request
     * @param response the response
     * @param model    the model
     * @return the view
     */
    @RenderMapping()
    public String showPiwikTracking(RenderRequest request, RenderResponse response, final ModelMap model) {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long scopeGroupId = themeDisplay.getScopeGroupId();
        long companyId = themeDisplay.getCompanyId();
        
        PiwikTracking piwikTracking = piwikTrackingService.findPiwikTrackingByGroupId(companyId, scopeGroupId);
        
        model.addAttribute("piwikTracking", piwikTracking);
        model.addAttribute("piwikBaseURL", PiwikTrackingConstants.PIWIK_BASE_URL);
        
        return "view";
    }

}
