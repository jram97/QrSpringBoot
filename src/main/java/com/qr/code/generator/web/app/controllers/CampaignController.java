package com.qr.code.generator.web.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.qr.code.generator.web.app.domain.UserAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qr.code.generator.web.app.domain.BranchOffice;
import com.qr.code.generator.web.app.domain.Campaign;
import com.qr.code.generator.web.app.domain.City;
import com.qr.code.generator.web.app.domain.Commerce;
import com.qr.code.generator.web.app.domain.Country;
import com.qr.code.generator.web.app.domain.OperatingSystem;
import com.qr.code.generator.web.app.domain.Scan;
import com.qr.code.generator.web.app.service.IBranchOfficeService;
import com.qr.code.generator.web.app.service.ICampaignService;
import com.qr.code.generator.web.app.service.ICityService;
import com.qr.code.generator.web.app.service.ICommerceService;
import com.qr.code.generator.web.app.service.ICountryService;
import com.qr.code.generator.web.app.service.IOperatingSystemService;
import com.qr.code.generator.web.app.service.IScanService;


@Controller
@RequestMapping("/campaign")
public class CampaignController {
	
	@Autowired
	private ICampaignService campaignService;
	
	@Autowired
	private ICommerceService commerceService;
	
	@Autowired
	private IBranchOfficeService branchOfficeService;
	
	@Autowired
	private IScanService scanService;
	
	@Autowired
	private ICityService cityService;
	
	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private IOperatingSystemService OSystemService;
	
	
	@GetMapping("/create")
	public String createCampaign(Model model) {
		model.addAttribute("commerce", new Commerce());
		model.addAttribute("branchOffice", new BranchOffice());
		model.addAttribute("shops", commerceService.findAll());
		model.addAttribute("branchOffices", branchOfficeService.findAll());
		model.addAttribute("campaign", new Campaign());
		model.addAttribute("sizes", campaignService.findAll().size());
		return "/create-campaign";
	}
	
	@GetMapping("/commercexbranchoffice")
	public String commerceXbranchOffice(@RequestParam(name="idCommerce", required=false , defaultValue= "1") Long idCommerce, Model model) {
		
		//model.addAttribute("branchOffices", branchOfficeService.find(idCommerce));
		return "forward:/create-campaign";
	}
	
	/*
	 * Al enviar el formulario de registro de usuarios, guarda el usuario en la base
	 * de datos
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String guardar(@RequestParam("file") MultipartFile foto,
			@RequestParam(name="idBranchOffice", required=false, defaultValue= "1") Long idBranchOffice,
			Campaign campaign) {

		Date currentDate = new Date();

		if(!foto.isEmpty()) {
			String rootPath = "//opt//uploads";
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				campaign.setImage(foto.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		campaign.setBranchOffice(branchOfficeService.findOne(idBranchOffice));
		UserAdmin userAdmin = new UserAdmin();
		userAdmin.setId((long) 1);
		campaign.setUser(userAdmin);
		campaign.setAvailable(true);
		/*
		try {
			if(campaign.getEndDate().after(currentDate))
				campaign.setAvailable(true);
			else
				campaign.setAvailable(false);
		}catch (Exception e){
			e.printStackTrace();
		}*/


		campaignService.save(campaign);

		return "redirect:/";
	}
	
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable("id") Long id, /* 
			@RequestParam(name="pais", required=false, defaultValue= "El Salvador") String pais,
			@RequestParam(name="osname", required=false, defaultValue= "Android") String osname,
			@RequestParam(name="ciudad", required=false, defaultValue= "San Salvador") String ciudad,*/
			Map<String, Object> model, RedirectAttributes flash) {
		
		
		Campaign campaign = campaignService.findOne(id);
		/*
		Date currentDate = new Date();
		
		if(campaign==null) {
			flash.addFlashAttribute("error", "La campaña no existe en la base de datos");
			return "redirect:/QRCode";
		}

		try {
			if(campaign.getEndDate().after(currentDate)) {
				Country country = new Country();
				country.setCountry(pais);
				countryService.save(country);

				City city = new City();
				city.setCity(ciudad);
				city.setCountry(country);
				cityService.save(city);


				OperatingSystem os = new OperatingSystem();
				os.setOSName(osname);
				OSystemService.save(os);


				Scan scan = new Scan();
				scan.setScanDate(new Date());
				scan.setCampaign(campaign);
				scan.setCity(city);
				scan.setOperatingSystem(os);
				scanService.save(scan);

			}
		}catch (Exception e){
			e.printStackTrace();
		}
		*/
		model.put("campaign", campaign);
		model.put("idcampaign", id);
		return "ver";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCampaign(@PathVariable("id") Long id){
		Campaign campaign = campaignService.findOne(id);
						
		boolean valor = campaign.getAvailable();

		if(valor) {
			campaign.setAvailable(false);
		}else {
			campaign.setAvailable(true);
		}
		campaignService.save(campaign);
		//campaignService.delete(id);
		return "redirect:/";
	}

}
