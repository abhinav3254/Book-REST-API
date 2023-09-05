package com.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.constants.Constants;
import com.dao.PublishersDao;
import com.jwt.JwtFilter;
import com.pojo.Publishers;
import com.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private PublishersDao dao;
	@Autowired
	private JwtFilter jwtFilter;
	
	@Override
	public ResponseEntity<String> addPublisher(Map<String, String> map) {
		try {
			if (jwtFilter.isAdmin()) {
				Publishers publishers = configPublishers(map);
				dao.save(publishers);
				
				return new ResponseEntity<String>(Constants.designMessage("Added Publisher !!"),HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(Constants.designMessage("UNAUTHORIZED ACCESS"),HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			System.out.println("Error in PublisherServiceImpl class :- "+e);
		}
		return new ResponseEntity<String>(Constants.designMessage("SOMETHING WENT WRONG"),HttpStatus.OK);
	}
	
	private Publishers configPublishers(Map<String, String>map) {
		try {
			Publishers publishers = new Publishers();
			
			publishers.setPublisherName(map.get("name"));
			publishers.setCountry("country");
			
			return publishers;
		} catch (Exception e) {
			System.out.println("Error in PublisherServiceImpl class :- "+e);
		}
		
		throw new RuntimeException("Error in PublisherServiceImpl");
	}

	@Override
	public ResponseEntity<List<Publishers>> getAllPublishers() {
		try {
			List<Publishers> listPublishers = dao.findAll();
			return new ResponseEntity<List<Publishers>>(listPublishers,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Publishers>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
