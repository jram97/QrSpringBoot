package com.qr.code.generator.web.app.controllers;

import com.qr.code.generator.web.app.domain.Campaign;
import com.qr.code.generator.web.app.domain.City;
import com.qr.code.generator.web.app.domain.UserAdmin;
import com.qr.code.generator.web.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//@RequestMapping("${application.name}")
public class MainController {

    @Autowired
	ICampaignService campaignService;

	@Autowired
    IUserAdminService userAdminService;

	@Autowired
	IScanService scanService;

	@Autowired
	ICityService cityService;

	@Autowired
	ICountryService countryService;

	/*
	 * Página principal que se muestra al logguearse
	 */
	@GetMapping({"/", "", "/home", "/index"})
	public String mainPage(Model model, HttpServletRequest session) {

		if(!session.getSession().isNew()) {

			Date currentDate = new Date();
			List<Campaign> campaigns = campaignService.findIsAvailable();
			campaigns.forEach(campaign -> {
				try {
					if (campaign.getEndDate().after(currentDate))
						campaign.setAvailable(true);
					else
						campaign.setAvailable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			model.addAttribute("campaigns", campaigns);
			return "/index";
		}else{
			return "/login";
		}

	}

	@GetMapping("/sort/{order}")
	public String mainPageSorted(@PathVariable("order") int order, Model model){
		List<Campaign>  campaigns = null;
		switch (order){
			case 1: campaigns = campaignService.findByOrderByStartDateDesc();
					break;
			case 2: campaigns = campaignService.findByOrderByTitleAsc();
					break;
			case 3: campaigns = campaignService.findAll();
					campaigns.sort(Comparator.comparing(a -> a.getScans().size()));
					Collections.reverse(campaigns);
					break;
		}
		model.addAttribute("campaigns", campaigns);
		return "/index";
	}

	/*
	 * pruebas para layout principal
	 */
	@GetMapping({"/layout"})
	public String layPage() {
		return "/layout/layout";
	}
	
	/*
	 * Login para el registro de usuarios
	 */
	/*@GetMapping("/login")
	public String loginPage(@RequestParam(value="error", required=false) String error,
							@RequestParam(value="logout", required = false) String logout,
							Model model, Principal principal, RedirectAttributes flash) {

		if (principal != null) {
			flash.addFlashAttribute("info", "Ya existe una sesión con estas credenciales!");
			return "redirect:/";
		}

		if (error != null) {
			model.addAttribute("error", error);
		}

		if (logout != null) {
			model.addAttribute("success", "Cierre de Sesión correcto");
		}

		return "login";
	}*/

	/*
	@GetMapping("/login")
    public String loginPage(){
	    return "login";
    }

    @PostMapping("/login")
    public String loginValidation(@RequestParam("username") String username, @RequestParam("password") String password, Model model){
        UserAdmin userAdmin = userAdminService.findUserAdminByUsernameAndPassword(username, password);

        if(userAdmin == null){
            model.addAttribute("info", "Incorrect username or password.");
            return "login"; //User not found
        }else if (userAdmin.getLogin()){
            return ""; //User already loggin!
        }else{
            userAdmin.setLogin(true);
            return "redirect:/QRCode/";
        }

    }

    @GetMapping("/logout")
    public String logout(@RequestParam("userIdHidden") Long id){
	    UserAdmin userAdmin = userAdminService.findOne(id);
	    userAdmin.setLogin(false);
	    return "redirect:/QRCode/login";
    }
*/
	/*
	 * Pantalla de registro
	 */
	@GetMapping("/register")
	public String registerPage() {
		return "forward:/auth/register";
	}
	
	/*
	 * Pantalla de forgot password
	 */
	@GetMapping("/forgot-password")
	public String forgotPasswordPage() {
		return "forward:/auth/forgot-password";
	}
	
	/*
	 * Formulario de creacion de campañas QR
	 */
	@GetMapping("/campaign")
	public String createCampaign(HttpServletRequest session) {
		if(!session.getSession().isNew()) {
			return "forward:/campaign/create";
		}else{
			return "/login";
		}

	}
	
	@PostMapping("/campaign")
	public String createPostCampaign() {
		return "forward:/campaign/create";
	}
	
	/*
	 * Formulario emergente Commercio
	 */
	@PostMapping("/commerce")
	public String createPostCommerce() {
		return "forward:/commerce/create";
	}


	@GetMapping("/details/{id}")
	public String showDashBoard(@PathVariable("id") Long id, Model model){
		Campaign campaign = campaignService.findOne(id);

		model.addAttribute("dashCities", cityService.getCampaignCities(id));
		model.addAttribute("dashCountries", countryService.getCampaignCountry(id));
		model.addAttribute("campaigns", campaign);

		return "/dashboard";
	}
	
	@RequestMapping("/download/{id}")
	@ResponseBody
	public void download(@PathVariable("id") Long id, HttpServletResponse response) {

		Campaign campaign = campaignService.findOne(id);
		
		String fileVal = campaign.getImgQR();

		String fileName = fileVal.split(",")[1];

		String folderPath = "//opt//uploads/"+campaign.getTitle()+".png";

		//Decodifica
		try (FileOutputStream imageOutFile = new FileOutputStream(folderPath)) {
		    // Converting a Base64 String into Image byte array
		    byte[] imageByteArray = Base64.getDecoder().decode(fileName);
		    imageOutFile.write(imageByteArray);
		  } catch (FileNotFoundException e) {
		    System.out.println("Image not found" + e);
		  } catch (IOException ioe) {
		    System.out.println("Exception while reading the Image " + ioe);
		  }


		//Descarga
		response.setContentType("image/png");
		response.setHeader("Content-Disposition", "attachment; filename=" + campaign.getTitle()+".png");
		response.setHeader("Content-Transfer-Encoding", "binary");
		try {
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			FileInputStream fis = new FileInputStream(folderPath);
			int len;
	    	  byte[] buf = new byte[1024];
	    	  while((len = fis.read(buf)) > 0) {
	    		  bos.write(buf,0,len);
	    	  }
	    	  bos.close();
	    	  response.flushBuffer();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	/*
	 * 404
	 */
	
	@GetMapping("/Err404")
	public String metodo404() {
		return "/404";
	}
	
	/*
	 * 403
	 */
	@GetMapping("/Err403")
	public String metodo403() {
		return "/403";
	}
	
}
