package se.vgregion.portal.piwiktracking.controller;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import se.vgregion.portal.piwiktracking.domain.jpa.PiwikTracking;
import se.vgregion.portal.piwiktracking.service.PiwikTrackingService;
import se.vgregion.portal.piwiktracking.util.PiwikTrackingUtil;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

/**
 * Controller for the edit view for the piwik tracking portlet.
 *
 * @author Erik Andersson
 * @company Monator Technologies AB
 */
@Controller
@RequestMapping("EDIT")
public class PiwikTrackingEditController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PiwikTrackingEditController.class.getName());
    private PiwikTrackingService piwikTrackingService;

    /**
     * Constructor.
     *
     * @param piwikTrackingService the {@link PiwikTrackingService}
     */
    @Autowired
    public PiwikTrackingEditController(PiwikTrackingService piwikTrackingService) {
        this.piwikTrackingService = piwikTrackingService;
    }

    /**
     * Method handling Action request.
     *
     * @param request  the request
     * @param response the response
     * @param model    the model
     */
    @ActionMapping("savePiwikTrackingSettings")
    public final void savePiwikTrackingSettings(
            ActionRequest request, ActionResponse response, final ModelMap model) {

    	System.out.println("PiwikTrackingEditController - savePiwikTrackingSettings");

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long companyId = themeDisplay.getCompanyId();
        long userId = themeDisplay.getUserId();
        
        long piwikTrackingId = ParamUtil.getLong(request, "piwikTrackingId", -1);
        
        long piwikTrackingGroupId = ParamUtil.getLong(request, "groupId", -1);
        
        String piwikTrackingCode = ParamUtil.getString(request, "piwikTrackingCode", "");
        
        if(piwikTrackingId == -1) {
        	// Create new
        	piwikTrackingService.addPiwikTracking(userId, companyId, piwikTrackingGroupId, piwikTrackingCode);
        }
        else {
        	// Update
        	PiwikTracking piwikTracking = piwikTrackingService.findPiwikTracking(piwikTrackingId);
        	
        	piwikTracking.setGroupId(piwikTrackingGroupId);
        	piwikTracking.setPiwikTrackingCode(piwikTrackingCode);
        	
        	piwikTrackingService.merge(piwikTracking);
        }
    }

    /**
     * Method handling Action request.
     *
     * @param request  the request
     * @param response the response
     * @param model    the model
     */
    @ActionMapping("deletePiwikTracking")
    public final void deletePiwikTracking(
            ActionRequest request, ActionResponse response, final ModelMap model) {
        
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long companyId = themeDisplay.getCompanyId();
        
        long piwikTrackingId = ParamUtil.getLong(request, "piwikTrackingId", -1);
        
        if(piwikTrackingId != -1) {
        	PiwikTracking piwikTracking = piwikTrackingService.findPiwikTracking(piwikTrackingId);
        	piwikTrackingService.remove(piwikTracking);
        }
    }
    
    

    /**
     * Method handling Render request, fetching portlet preferences for view.
     *
     * @param request  the request
     * @param response the response
     * @param model    the model
     * @return jsp url for view
     */
    @RenderMapping(params = "view=showEditPiwikTracking")
    public final String showEditPiwikTracking(RenderRequest request, RenderResponse response, final ModelMap model) {

    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	long companyId = themeDisplay.getCompanyId();
    	
    	long piwikTrackingId = ParamUtil.getLong(request, "piwikTrackingId", -1);
    	String backURL = ParamUtil.getString(request, "backURL", "");
    	
    	PiwikTracking piwikTracking = null;
    	
    	if(piwikTrackingId == -1) {
    		piwikTracking = new PiwikTracking();
    	}
    	else {
    		piwikTracking = piwikTrackingService.findPiwikTracking(piwikTrackingId);
    	}
    	
        List<Group> companyGroups = PiwikTrackingUtil.getCompanyGroups(companyId);
    	
        model.addAttribute("piwikTracking", piwikTracking);
        model.addAttribute("companyGroups", companyGroups);
        model.addAttribute("backURL", backURL);
        
        return "edit_piwik_tracking";
    }
    
    /**
     * Method handling Render request, fetching portlet preferences for view.
     *
     * @param request  the request
     * @param response the response
     * @param model    the model
     * @return jsp url for view
     */
    @RenderMapping
    public final String showEdit(RenderRequest request, RenderResponse response, final ModelMap model) {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long companyId = themeDisplay.getCompanyId();
        
        String currentURL = PortalUtil.getCurrentURL(request);
        
        List<PiwikTracking> piwikTrackings = piwikTrackingService.findPiwikTrackingsByCompanyId(companyId);
        
        PortletURL iteratorURL = response.createRenderURL();

        model.addAttribute("currentURL", currentURL);
        model.addAttribute("iteratorURL", iteratorURL);
        model.addAttribute("piwikTrackings", piwikTrackings);
        
        return "edit";
    }    

}
