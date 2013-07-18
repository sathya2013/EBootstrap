package com.me.bootstrap.web.back;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.me.bootstrap.constants.BootstrapConstants;
import com.me.bootstrap.entity.Orgnization;
import com.me.bootstrap.service.OrgnizationService;
import com.me.bootstrap.web.Servlets;

@Controller
@RequestMapping("/management/org")
public class OrganiztationController {

	@Autowired
    private OrgnizationService orgnizationService;
	
	@RequestMapping(value="/list.do")
	public String listOrg(@PageableDefaults(sort = "id", sortDir = Direction.DESC) Pageable pageable,
			HttpServletRequest request, org.springframework.ui.Model modelMap){
	
		Map<String, String[]> params = Servlets.getParameterValuesMap(request, BootstrapConstants.SEARCH_PREFIX);
		Page<Orgnization> pagedList = orgnizationService.findPage(params, pageable);
		modelMap.addAttribute("pagedList", pagedList);
		return "/orgnization/orgList";
	}
}
