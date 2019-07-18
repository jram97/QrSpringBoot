package com.qr.code.generator.web.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qr.code.generator.web.app.domain.Campaign;
import com.qr.code.generator.web.app.domain.Commerce;
import com.qr.code.generator.web.app.service.ICampaignService;
import com.qr.code.generator.web.app.service.ICommerceService;

@Controller
@RequestMapping("/commerce")
public class CommerceController {
	
	@Autowired
	private ICommerceService commerceService;
	
	@PostMapping("/create")
	public String commerceRegister(Commerce commerce, Model model, RedirectAttributes flash) {
		
		commerceService.save(commerce);
		
		return "redirect:/campaign/create";
	}
	
	
}
