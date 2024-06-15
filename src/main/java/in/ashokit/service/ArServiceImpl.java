package in.ashokit.service;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.ashokit.binding.CitizenApp;
import in.ashokit.entity.CitizenAppEntity;
import in.ashokit.repository.CitizenAppRepository;


public class ArServiceImpl implements ArService {

	@Autowired
	private CitizenAppRepository appRepo;

	@Override
	public Integer  createApplication(CitizenApp app) {
		

		String endPointUrl = "https://ssa-eb-api.herokuuapp.com/ssn/{ssn}";

		RestTemplate tr = new RestTemplate();

		ResponseEntity<String> resEntity = tr.getForEntity(endPointUrl, String.class, app.getSsn());
		
       String stateName = resEntity.getBody();

		if ("New Jersey".equals(stateName)) {

			CitizenAppEntity entity = new CitizenAppEntity();
			BeanUtils.copyProperties(app, entity);
			
			
			entity.setStateName(stateName);
          CitizenAppEntity save=appRepo.save(entity);
            return save.getAppId();
            
		}

		return 0;
	}

}
