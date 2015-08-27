package be.vdab.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.entities.Bier;
import be.vdab.valueobjects.BierInMandje;

@Controller
@RequestMapping(value = "/bieren", produces = MediaType.TEXT_HTML_VALUE)
public class BierController {
	private static final String BIER_VIEW = "bieren/bier";
	private static final String MANDJE_VIEW = "redirect:/mandje";

	@RequestMapping(value = "{bier}", method = RequestMethod.GET)
	ModelAndView read(@PathVariable Bier bier, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(BIER_VIEW);
		if (bier != null) {
			modelAndView
					.addObject(bier)
					.addObject(new BierInMandje())
					.addObject("bierAlInMandje",
							checkIfBierAlInMandje(bier, request));
		}
		return modelAndView;
	}

	@InitBinder("bierInMandje")
	void initBinderBierInMandje(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.POST)
	ModelAndView toevoegenAanMandje(@Valid BierInMandje bierInMandje,
			BindingResult bindingResult, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView(BIER_VIEW, "bier", bierInMandje.getBier())
					.addObject(
							"bierAlInMandje",
							checkIfBierAlInMandje(bierInMandje.getBier(),
									request));
		}
		Bier bier = bierInMandje.getBier();
		long bierid = bier.getBierNr();
		int aantal = bierInMandje.getAantal();
		HttpSession session = request.getSession();
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Integer> bierIdsEnAantalInMandje = (Map<Long, Integer>) session
					.getAttribute("mandje");
			if (bierIdsEnAantalInMandje == null) {
				bierIdsEnAantalInMandje = new HashMap<Long, Integer>();
			}
			if (bierIdsEnAantalInMandje.containsKey(bierid)) {
				String bierAantalToevoegen = request
						.getParameter("bierAantalToevoegen");
				String bierAantalVervangen = request
						.getParameter("bierAantalVervangen");
				if (bierAantalToevoegen != null) {
					bierIdsEnAantalInMandje.put(bierid,
							bierIdsEnAantalInMandje.get(bierid) + aantal);
				} else if (bierAantalVervangen != null) {
					bierIdsEnAantalInMandje.put(bierid, aantal);
				} else {
					return new ModelAndView(BIER_VIEW, "bier",
							bierInMandje.getBier()).addObject(
							"bierAlInMandje",
							checkIfBierAlInMandje(bierInMandje.getBier(),
									request));
				}
			} else {
				bierIdsEnAantalInMandje.put(bierid, aantal);
			}
			session.setAttribute("mandje", bierIdsEnAantalInMandje);
		}
		return new ModelAndView(MANDJE_VIEW);
	}

	private boolean checkIfBierAlInMandje(Bier bier, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Integer> bierIdsEnAantalInMandje = (Map<Long, Integer>) session
					.getAttribute("mandje");
			if (bierIdsEnAantalInMandje == null) {
				return false;
			} else if (bierIdsEnAantalInMandje.containsKey(bier.getBierNr())) {
				return true;
			}
		}
		return false;
	}
}