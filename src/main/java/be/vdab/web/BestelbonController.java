package be.vdab.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.entities.Bestelbon;
import be.vdab.entities.Bier;
import be.vdab.services.BestelbonService;
import be.vdab.services.BierService;
import be.vdab.valueobjects.Bestelbonlijn;
import be.vdab.valueobjects.BierInMandje;

@Controller
@RequestMapping(value = "/mandje", produces = MediaType.TEXT_HTML_VALUE)
public class BestelbonController {
	private final BestelbonService bestelbonService;
	private final BierService bierService;
	private static final String BESTELBON_VIEW = "bestelbon/bestelbon";
	private static final String MANDJE_VIEW = "mandje/mandje";
	private static final String MANDJE_REDIRECT_VIEW = "redirect:/mandje";

	@Autowired
	BestelbonController(BestelbonService bestelbonService,
			BierService bierService) {
		this.bestelbonService = bestelbonService;
		this.bierService = bierService;
	}

	@RequestMapping(method = RequestMethod.GET)
	String mandjeTonen(Model model, HttpServletRequest request) {
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
					BierInMandje bierInMandje = new BierInMandje(
							mandje.get(id), bier);
					totaal = totaal.add(bierInMandje.getTotaal());
					bierenInMandje.add(bierInMandje);
				}
				model.addAttribute("bierenInMandje", bierenInMandje)
						.addAttribute("mandjeTotaal", totaal);
				if (!model.containsAttribute("bestelbon")) {
					model.addAttribute("bestelbon", new Bestelbon());
				}

			}
		}
		return MANDJE_VIEW;
	}

	@InitBinder("bestelbon")
	void initBinderFiliaal(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}

	@RequestMapping(value = "bestelbon", method = RequestMethod.POST)
	String bestellingPlaatsen(
			@ModelAttribute("bestelbon") @Valid Bestelbon bestelbon,
			BindingResult bindingResult, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute(
					"org.springframework.validation.BindingResult.bestelbon",
					bindingResult);
			redirectAttributes.addFlashAttribute("bestelbon", bestelbon);
			return MANDJE_REDIRECT_VIEW;
		}
		HttpSession session = request.getSession();
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session
					.getAttribute("mandje");
			if (mandje != null) {
				for (long id : mandje.keySet()) {
					Bier bier = bierService.read(id);
					Bestelbonlijn bestelbonlijn = new Bestelbonlijn(bier,
							mandje.get(id));
					bestelbon.addBestelbonlijn(bestelbonlijn);
				}
			} else {
				redirectAttributes.addAttribute("fout",
						"Kon bestelling niet plaatsen, mandje is leeg");
				return BESTELBON_VIEW;
			}
		} else {
			redirectAttributes.addAttribute("fout",
					"Kon bestelling niet plaatsen, mandje is leeg");
			return BESTELBON_VIEW;
		}
		bestelbonService.create(bestelbon);
		session.removeAttribute("mandje");
		redirectAttributes.addAttribute("bestelbon", bestelbon.getBonNr());
		return BESTELBON_VIEW;
	}

}
