package be.vdab.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Bier;
import be.vdab.services.BierService;
import be.vdab.valueobjects.BierInMandje;

@Controller
@RequestMapping(value = "/mandje", produces = MediaType.TEXT_HTML_VALUE)
public class MandjeController {
	private final BierService bierService;
	private static final String MANDJE_VIEW = "mandje/mandje";
	
	@Autowired
	MandjeController(BierService bierService) {
		this.bierService = bierService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView read(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(MANDJE_VIEW);
		HttpSession session = request.getSession();
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session
					.getAttribute("mandje");
			if (mandje != null) {
				List<BierInMandje> bierenInMandje = new ArrayList<>();
				BigDecimal totaal = new BigDecimal(0);
				for (long id : mandje.keySet()) {
					Bier bier = bierService.read(id);
					BierInMandje bierInMandje = new BierInMandje(mandje.get(id), bier);
					totaal = totaal.add(bierInMandje.getTotaal());
					bierenInMandje.add(bierInMandje);
				}
				modelAndView.addObject("bierenInMandje", bierenInMandje).addObject("mandjeTotaal", totaal);
			}
		}
		return modelAndView;
	}
}
