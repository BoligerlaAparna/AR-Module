package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.CitizenApp;
import in.ashokit.service.ArService;

@RestController
public class ArRestController {
	
	@Autowired
	private ArService service;
	
	@PostMapping("/app")
	public ResponseEntity<String> createCizenApp( @RequestBody CitizenApp app)
	{
	 Integer appId = service.createApplication(app);
	 
	 if(appId >0)
	 {
		 return new ResponseEntity<>("App Created with App Id:" +appId,HttpStatus.OK);
	 }
	 else
	 {
		 return new ResponseEntity<>("Invalid SSN ",HttpStatus.BAD_REQUEST);
	 }
		
	}

}
