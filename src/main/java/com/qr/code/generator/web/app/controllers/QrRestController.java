package com.qr.code.generator.web.app.controllers;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qr.code.generator.web.app.domain.Campaign;
import com.qr.code.generator.web.app.domain.City;
import com.qr.code.generator.web.app.domain.Country;
import com.qr.code.generator.web.app.domain.OperatingSystem;
import com.qr.code.generator.web.app.domain.Scan;
import com.qr.code.generator.web.app.dto.QrDTO;
import com.qr.code.generator.web.app.service.IBranchOfficeService;
import com.qr.code.generator.web.app.service.ICampaignService;
import com.qr.code.generator.web.app.service.ICityService;
import com.qr.code.generator.web.app.service.ICommerceService;
import com.qr.code.generator.web.app.service.ICountryService;
import com.qr.code.generator.web.app.service.IOperatingSystemService;
import com.qr.code.generator.web.app.service.IScanService;

@CrossOrigin(origins = "http//qr.solucionesroots.com:8080", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping("/api")
public class QrRestController {
	
	@Autowired
	private ICampaignService campaignService;
	
	@Autowired
	private IScanService scanService;
	
	@Autowired
	private ICityService cityService;
	
	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private IOperatingSystemService OSystemService;
	
	@PostMapping(value="/ver",consumes = "application/json", produces = "application/json")
	public String ver(@RequestBody QrDTO qrData) {
		
		Date currentDate = new Date();
		Campaign campaign = campaignService.findOne(qrData.getId());
		
		if(campaign==null) {
			return "redirect:/";
		}

		try {
			if(campaign.getEndDate().after(currentDate)) {
				Country country = new Country();
				country.setCountry(qrData.getCountry());
				countryService.save(country);

				City city = new City();
				city.setCity(qrData.getCity());
				city.setCountry(country);
				cityService.save(city);


				OperatingSystem os = new OperatingSystem();
				os.setOSName(qrData.getOsname());
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
		
		return "ver";
	}

}
