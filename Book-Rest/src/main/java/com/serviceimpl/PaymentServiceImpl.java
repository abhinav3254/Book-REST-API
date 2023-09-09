package com.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dao.PaymentDao;
import com.dao.UserDao;
import com.jwt.JwtUtils;
import com.pojo.Payment;
import com.pojo.User;
import com.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDao userDao;

	@Override
	public ResponseEntity<String> savePayment(Map<String, String> map) {
		try {
			
			System.out.println("MAP VALUE  ----->  "+map.toString());
			
			String cardNumber = map.get("cardNumber");
			String bankName = map.get("bankName");
			String nameOnCard = map.get("nameOnCard");
			String cvv = map.get("cvv");
			String dateString = map.get("date");
			System.out.println("date is  ------->>>> "+dateString);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			Date date = dateFormat.parse(dateString);

			Double amount = Double.parseDouble(map.get("amount"));

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String token = auth.getName();

			String username = jwtUtils.extractUsername(token);

			User user = userDao.getUserByUserName(username);
			
			Payment payment = new Payment();
			
			payment.setCardNumber(cardNumber);
			payment.setBankName(bankName);
			payment.setNameOnCard(nameOnCard);
			payment.setCvv(cvv);
			payment.setExpiryDate(date);
			payment.setUser(user);
			
			payment.setAmount(amount);
			
			System.out.println(payment.toString());
			
			paymentDao.save(payment);		
			return new ResponseEntity<String>("SAVED",HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
