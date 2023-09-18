package com.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BookDao;
import com.dao.CartDao;
import com.dao.CartItemDao;
import com.dao.OrdersDao;
import com.dao.PaymentDao;
import com.dao.UserDao;
import com.jwt.JwtFilter;
import com.jwt.JwtUtils;
import com.pojo.Book;
import com.pojo.Cart;
import com.pojo.CartItem;
import com.pojo.Orders;
import com.pojo.Payment;
import com.pojo.User;
import com.service.OrderService;


/**
 * This class provides implementations for managing orders and order-related operations in the system.
 * It includes methods for placing orders, retrieving user-specific orders, retrieving all orders for admin,
 * and managing refund status.
 * 
 * @Transactional use case here
 * Transactions are used to ensure that a series of operations on the database either all succeed or all fail together.
 */ 
@Transactional
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private CartItemDao cartItemDao;

	@Autowired
	private OrdersDao ordersDao;

	@Autowired
	private CartDao cartDao;

	@Autowired
	private JwtFilter jwtFilter;

	@Autowired
	BookDao bookDao;

	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private CartServiceImpl cartServiceImpl;
	
	/**
     * Places an order for the currently authenticated user if items are successfully added to the cart.
     *
     * @parameter map A map containing additional order information such as delivery address.
     * @return ResponseEntity with a success message if the order is placed (HTTP status OK),
     *         or an error message with an internal server error status if an exception occurs.
     */
	@Override
	public ResponseEntity<String> placeOrder(Map<String, String> map) {
		try {

			// Obtain the currently authenticated user
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userToken = authentication.getName();
			String username = jwtUtils.extractUsername(userToken);
			User user = userDao.getUserByUserName(username);

			
			// Attempt to add items to the cart and place the order if successful
			Boolean ans = cartServiceImpl.addToCart2();
			
			if (ans) {
				// Retrieve the user's cart
				List<Cart> cartList = cartDao.getCartByUserId(user.getId());
				
//				System.out.println("Cart List Size is :- "+cartList.size());
				Cart cart = cartList.get(0);
				

				Payment payment = paymentDao.getAllPaymentByUser(user.getId());

				String address = map.get("address");

				Orders orders = new Orders();

				orders.setOrderDate(LocalDateTime.now());

				orders.setCart(cart);
				orders.setUser(user);
				orders.setAddress(address);
				orders.setPayment(payment);

				ordersDao.save(orders);

				// here doing cart manage

//				System.out.println(" -- > here is the cart items size :-- " + cart.getCartItems().size());

				List<CartItem> newList = cart.getCartItems();

				for (int i = 0; i < newList.size(); i++) {
					int inCartQuantityOfABook = newList.get(i).getQuantity();
					Book book1 = newList.get(i).getBook();
					book1.setBookQuantity(book1.getBookQuantity() - inCartQuantityOfABook);
					bookDao.save(book1);
				}

				// here my cart manage ends

				// Get the user ID
				Integer userId = user.getId();

				// Retrieve the payment and cart items for the user
				Payment payment2 = paymentDao.getAllPaymentByUser(userId);
				List<CartItem> cartItems = cartItemDao.getAllItemsFromCart(userId);

				// Set user details to null for payment
				if (payment2 != null) {
					payment2.setUser(null);
					paymentDao.save(payment2); // Update the payment in the database
				}

				// Set user details to null for each cart item
				for (CartItem cartItem : cartItems) {
					cartItem.setUser(null);
					cartItemDao.save(cartItem); // Update each cart item in the database
				}

				cart.setUser(null); // Set the user to null
				cartDao.save(cart); // Update the cart in the database

				return new ResponseEntity<String>("ORDER PLACED", HttpStatus.OK);
			} else {
				// for not added to cart
				
				// first delete the user payment
				
				paymentDao.deletePaymentByUserId(user.getId());
				
				// second delete the refernce of user in cart_item table
				
				cartItemDao.deleteAllItemsFromCartByUserId(user.getId());
				
				return new ResponseEntity<String>("ORDER CAN'T BE PLACED",HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error placing order", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	/**
     * Retrieves a list of orders placed by the currently authenticated user.
     *
     * @return ResponseEntity containing a list of user-specific orders if successful (HTTP status OK),
     *         or an error response with an internal server error status if an exception occurs.
     */
	@Override
	public ResponseEntity<List<Orders>> getAllOrders() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String token = authentication.getName();

			String username = jwtUtils.extractUsername(token);

			User user = userDao.getUserByUserName(username);

			List<Orders> listOrders = ordersDao.getAllOrders(user.getId().toString());

			return new ResponseEntity<List<Orders>>(listOrders, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Orders>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
     * Retrieves a list of all orders placed in the system (admin access).
     *
     * @return ResponseEntity containing a list of all orders if the user is an admin (HTTP status OK),
     *         or an unauthorized response if the user is not an admin (HTTP status UNAUTHORIZED),
     *         or an error response with an internal server error status if an exception occurs.
     */
	@Override
	public ResponseEntity<List<Orders>> getAllTheOrdersPlaced() {
		try {
			if (jwtFilter.isAdmin()) {
				List<Orders> listOrders = ordersDao.findAll();
				return new ResponseEntity<List<Orders>>(listOrders, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Orders>>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Orders>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}


	/**
     * Retrieves a list of orders with a refund status (admin access).
     *
     * @return ResponseEntity containing a list of orders with refund status if the user is an admin (HTTP status OK),
     *         or an error response with an internal server error status if an exception occurs.
     */
	@Override
	public ResponseEntity<List<Orders>> getAllRefund() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String token = authentication.getName();
			String username = jwtUtils.extractUsername(token);
			User user = userDao.getUserByUserName(username);
			
			
			List<Orders> list = ordersDao.getRefund(user.getId());
			return new ResponseEntity<List<Orders>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Orders>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
