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


/**
 * This class provides implementations for managing payment-related operations in the system.
 * It includes a method for saving payment details.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDao userDao;

	
	/**
     * Saves payment details in the system, associated with the authenticated user.
     *
     * @param map A map containing payment information, including card details, amount, and date.
     * @return ResponseEntity with a success message if the payment is saved (HTTP status OK),
     *         or an error message with an internal server error status if an exception occurs.
     */
	@Override
	public ResponseEntity<String> savePayment(Map<String, String> map) {
		try {
			// Extract payment information from the map
			String cardNumber = map.get("cardNumber");
			String bankName = map.get("bankName");
			String nameOnCard = map.get("nameOnCard");
			String cvv = map.get("cvv");
			String dateString = map.get("date");
			
			// Parse the date from the string
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			Date date = dateFormat.parse(dateString);

			
			Double amount = Double.parseDouble(map.get("amount"));
			
			// Get the authenticated user's username
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String token = auth.getName();
			String username = jwtUtils.extractUsername(token);
			
			// Find the user by their username
			User user = userDao.getUserByUserName(username);
			
			// Create a Payment object with the extracted details
			Payment payment = new Payment();
			
			payment.setCardNumber(cardNumber);
			payment.setBankName(bankName);
			payment.setNameOnCard(nameOnCard);
			payment.setCvv(cvv);
			payment.setExpiryDate(date);
			payment.setUser(user);
			
			payment.setAmount(amount);
			
			System.out.println(payment.toString());
			// Save the payment information to the database
			paymentDao.save(payment);		
			return new ResponseEntity<String>("SAVED",HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
