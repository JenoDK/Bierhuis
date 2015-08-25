package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Brouwer;
import be.vdab.services.BrouwerService;

@Controller
@RequestMapping(value = "/brouwers", produces = MediaType.TEXT_HTML_VALUE)
public class BrouwerController {
	private final BrouwerService brouwerService;
	private static final String BROUWERS_VIEW = "brouwers/brouwers";
	private static final String BIEREN_PER_BROUWER_VIEW = "brouwers/bieren";

	@Autowired
	BrouwerController(BrouwerService brouwerService) {
		this.brouwerService = brouwerService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView findAll() {
		return new ModelAndView(BROUWERS_VIEW, "brouwers",
				brouwerService.findAll()).addObject("aantalBrouwers",
						brouwerService.findAantalBrouwers());
		
	}
	
	@RequestMapping(value = "{brouwer}", method = RequestMethod.GET)
	ModelAndView read(@PathVariable Brouwer brouwer) {
		ModelAndView modelAndView = new ModelAndView(BIEREN_PER_BROUWER_VIEW);
		if (brouwer != null) {
			modelAndView.addObject(brouwer);
		}
		return modelAndView;
	}
}
