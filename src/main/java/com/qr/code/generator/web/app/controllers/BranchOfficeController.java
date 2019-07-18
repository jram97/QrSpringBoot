package com.qr.code.generator.web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qr.code.generator.web.app.domain.BranchOffice;
import com.qr.code.generator.web.app.service.IBranchOfficeService;

@Controller
@RequestMapping("/branch-office")
public class BranchOfficeController {
	
	@Autowired
	private IBranchOfficeService branchOfficeService;
	
	@PostMapping("/create")
	public String branchOfficeRegister(BranchOffice branchOffice, Model model, RedirectAttributes flash) {
		branchOfficeService.save(branchOffice);
		return "redirect:/campaign/create";
	}

}
