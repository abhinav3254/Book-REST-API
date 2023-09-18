package com.serviceimpl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.constants.Constants;
import com.dao.BookDao;
import com.dao.CartItemDao;
import com.dao.UserDao;
import com.jwt.JwtUtils;
import com.pojo.Book;
import com.pojo.CartItem;
import com.pojo.User;
import com.service.CartItemService;

/**
 * Service implementation for managing cart items.
 * Provides methods for adding, incrementing, decrementing, deleting, and retrieving cart items.
 */
@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private CartItemDao cartItemDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private JwtUtils jwtUtils;

	
	/**
     * Adds a book to the cart item for the currently authenticated user.
     *
     * @parameter bookId The ID of the book to add to the cart item.
     * @return ResponseEntity with a success message if the book is added to the cart item (HTTP status OK),
     *         or an error message with an internal server error status if an exception occurs.
     */
	@Override
	public ResponseEntity<String> addToCartItem(String bookId) {
		try {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			String token = auth.getName();

			String username = jwtUtils.extractUsername(token);

			User user = userDao.getUserByUserName(username);
			Optional<Book> book = bookDao.findById(Integer.parseInt(bookId));

//			Find that if book exists already if yes then increase the quantity else add the book
			CartItem cartItem2 = cartItemDao.findBookInCartItem(book.get().getId(),user.getId());
			
			DecimalFormat df = new DecimalFormat("#.##");
			
			if (Objects.isNull(cartItem2)) {
				CartItem cartItem = new CartItem();
				cartItem.setBook(book.get());
				cartItem.setQuantity(1);
				Double price = Double.parseDouble(book.get().getPrice());
				String formattedValueprice = df.format(price);
				double roundedValueprice = Double.parseDouble(formattedValueprice);
				
				cartItem.setBookPrice(roundedValueprice);
				cartItem.setUser(user);
				Double cgst = (0.18 * price);
				String formattedValuecgst = df.format(cgst);
				double roundedValuecgst = Double.parseDouble(formattedValuecgst);
				
				cartItem.setCgst(roundedValuecgst);
				cartItem.setSgst(roundedValuecgst);
				Double discount = (0.10 * price);
				
				String formattedValuediscount = df.format(discount);
				double roundedValuediscount = Double.parseDouble(formattedValuediscount);
				
				cartItem.setDiscount(roundedValuediscount);

				Double finalPrice = (roundedValuecgst + roundedValuecgst + roundedValueprice) - roundedValuediscount;
				
				String formattedValuefinalPrice = df.format(finalPrice);
				double roundedValuefinalPrice = Double.parseDouble(formattedValuefinalPrice);
				
				
				cartItem.setFinalPrice(roundedValuefinalPrice);

				cartItemDao.save(cartItem);
				return new ResponseEntity<String>("Added", HttpStatus.OK);
			} else {
				CartItem cartItem = cartItem2;
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItem.setCgst(cartItem.getCgst() * 2);
				cartItem.setSgst(cartItem.getSgst() * 2);
				cartItem.setDiscount(cartItem.getDiscount() * 2);
				cartItem.setFinalPrice(cartItem.getFinalPrice() * 2);

				// here checking the quantity 
				
				if (book.get().getBookQuantity()<cartItem.getQuantity()) {
					return new ResponseEntity<String>("Stock Unavailable", HttpStatus.BAD_REQUEST);
				} else  {
					cartItemDao.save(cartItem);
					return new ResponseEntity<String>("Added", HttpStatus.OK);
				}
			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
     * Retrieves all cart items for the currently authenticated user.
     *
     * @return ResponseEntity with a list of cart items if retrieved successfully (HTTP status OK),
     *         or an error message with an internal server error status if an exception occurs.
     */
	@Override
	public ResponseEntity<List<CartItem>> getAllItemsFromCart() {
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String token = auth.getName();

			String username = jwtUtils.extractUsername(token);

			User user = userDao.getUserByUserName(username);
			List<CartItem> list = cartItemDao.getAllItemsFromCart(user.getId());

			return new ResponseEntity<List<CartItem>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<CartItem>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
	 * Increments the quantity of a cart item by one for the given cart item ID if it doesn't exceed the available stock.
	 *
	 * @param cartItemId The ID of the cart item to increment.
	 * @return ResponseEntity with a success message if the quantity is incremented (HTTP status OK),
	 *         or an error message with an internal server error status if an exception occurs
	 *         or if the quantity would exceed the available stock.
	 */
	@Override
	public ResponseEntity<String> incrementItem(String cartItemId) {
		try {
			// Retrieve the cart item by its ID
			Optional<CartItem> cartItemOptional = cartItemDao.findById(Integer.parseInt(cartItemId));
			// Retrieve the associated book
			Optional<Book> book = bookDao.findById(cartItemOptional.get().getBook().getId());

			// Get the available book quantity in the inventory
			Integer bookQuantityInInventory = book.get().getBookQuantity();
			
			// Get the cart item
			CartItem cartItem = cartItemOptional.get();
			
			if (cartItem.getQuantity()<bookQuantityInInventory) {
				// Increment the quantity by one
				Integer quantity = cartItem.getQuantity() + 1;

				cartItem.setQuantity(quantity);
				// Save the updated cart item
				cartItemDao.save(cartItem);

				return new ResponseEntity<String>("Incremented", HttpStatus.OK);

			} else {
				// Quantity exceeds available stock, return an error message
				return new ResponseEntity<String>(Constants.designMessage("INSUFFICIENT_STORAGE"),HttpStatus.BAD_REQUEST);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
	 * Decrements the quantity of a cart item by one for the given cart item ID. If the quantity becomes zero,
	 * the cart item is deleted.
	 *
	 * @parameter cartItemId The ID of the cart item to decrement.
	 * @return ResponseEntity with a success message if the quantity is decremented (HTTP status OK),
	 *         or an error message with an internal server error status if an exception occurs.
	 */
	@Override
	public ResponseEntity<String> decrementItem(String cartItemId) {
		try {
			// Retrieve the cart item by its ID
			Optional<CartItem> cartItemOptional = cartItemDao.findById(Integer.parseInt(cartItemId));

			// Get the cart item
			CartItem cartItem = cartItemOptional.get();

			// Decrement the quantity by one
			Integer quantity = cartItem.getQuantity() - 1;

			if (quantity>0) {
			cartItem.setQuantity(quantity);
			cartItemDao.save(cartItem);
			} else {
				// If the quantity becomes zero, delete the cart item
				cartItemDao.deleteById(Integer.parseInt(cartItemId));
			}

			return new ResponseEntity<String>("Decrement", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
	 * Deletes a cart item with the specified cart item ID from the user's cart.
	 *
	 * @param cartItemId The ID of the cart item to be deleted.
	 * @return ResponseEntity with a success message if the cart item is deleted (HTTP status OK),
	 *         or an error message with an internal server error status if an exception occurs.
	 */
	@Override
	public ResponseEntity<String> deleteItem(String cartItemId) {
		try {
			Optional<CartItem> cartItemOptional = cartItemDao.findById(Integer.parseInt(cartItemId));

			CartItem cartItem = cartItemOptional.get();
			cartItemDao.delete(cartItem);
			return new ResponseEntity<String>("Deleted", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
	 * Calculates and returns the sum of the final prices of all cart items in the user's cart.
	 *
	 * @return ResponseEntity with the sum of all final prices (HTTP status OK),
	 *         or an error message with an internal server error status if an exception occurs.
	 */
	@Override
	public ResponseEntity<Double> getSumOfAllFinalPrice() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String token = auth.getName();

			String username = jwtUtils.extractUsername(token);

			User user = userDao.getUserByUserName(username);
			List<CartItem> list = cartItemDao.getAllItemsFromCart(user.getId());
			
			Double sumOfAllFinalPrice = 0.0;
			
			for (int i = 0;i<list.size();i++) {
				sumOfAllFinalPrice = sumOfAllFinalPrice+(list.get(i).getFinalPrice()*list.get(i).getQuantity());
			}
			
			return new ResponseEntity<Double>(sumOfAllFinalPrice,HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Double>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
	 * Deletes all cart items from the user's cart.
	 *
	 * @return ResponseEntity with a success message if all cart items are deleted (HTTP status OK),
	 *         or an error message with an internal server error status if an exception occurs.
	 */
	@Override
	public ResponseEntity<String> deleteAll() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String token = auth.getName();

			String username = jwtUtils.extractUsername(token);

			User user = userDao.getUserByUserName(username);
			
			cartItemDao.deleteAllItemsFromCartByUserId(user.getId());
			
			return new ResponseEntity<String>(HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
