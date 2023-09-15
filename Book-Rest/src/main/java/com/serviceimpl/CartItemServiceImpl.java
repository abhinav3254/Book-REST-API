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
				Double cgst = price + (0.18 * price);
				String formattedValuecgst = df.format(cgst);
				double roundedValuecgst = Double.parseDouble(formattedValuecgst);
				
				cartItem.setCgst(roundedValuecgst);
				cartItem.setSgst(roundedValuecgst);
				Double discount = price + (0.10 * price);
				String formattedValuediscount = df.format(discount);
				double roundedValuediscount = Double.parseDouble(formattedValuediscount);
				
				cartItem.setDiscount(roundedValuediscount);

				Double finalPrice = (roundedValuecgst + roundedValuecgst + roundedValueprice) - roundedValuediscount;
				
				String formattedValuefinalPrice = df.format(finalPrice);
				double roundedValuefinalPrice = Double.parseDouble(formattedValuefinalPrice);
				
				
				cartItem.setFinalPrice(roundedValuefinalPrice);

				cartItemDao.save(cartItem);
			} else {
				CartItem cartItem = cartItem2;
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItem.setCgst(cartItem.getCgst() * 2);
				cartItem.setSgst(cartItem.getSgst() * 2);
				cartItem.setDiscount(cartItem.getDiscount() * 2);
				cartItem.setFinalPrice(cartItem.getFinalPrice() * 2);

				cartItemDao.save(cartItem);
			}

			return new ResponseEntity<String>("Added", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

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

	@Override
	public ResponseEntity<String> incrementItem(String cartItemId) {
		try {
			
			Optional<CartItem> cartItemOptional = cartItemDao.findById(Integer.parseInt(cartItemId));
			
			Optional<Book> book = bookDao.findById(cartItemOptional.get().getBook().getId());

			Integer bookQuantityInInventory = book.get().getBookQuantity();
			
			
			CartItem cartItem = cartItemOptional.get();
			
			if (cartItem.getQuantity()<bookQuantityInInventory) {
				Integer quantity = cartItem.getQuantity() + 1;

				cartItem.setQuantity(quantity);

				cartItemDao.save(cartItem);

				return new ResponseEntity<String>("Incremented", HttpStatus.OK);

			} else {
				return new ResponseEntity<String>(Constants.designMessage("INSUFFICIENT_STORAGE"),HttpStatus.BAD_REQUEST);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> decrementItem(String cartItemId) {
		try {
			Optional<CartItem> cartItemOptional = cartItemDao.findById(Integer.parseInt(cartItemId));

			CartItem cartItem = cartItemOptional.get();

			Integer quantity = cartItem.getQuantity() - 1;

			if (quantity>0) {
			cartItem.setQuantity(quantity);
			cartItemDao.save(cartItem);
			} else {
				cartItemDao.deleteById(Integer.parseInt(cartItemId));
			}

			return new ResponseEntity<String>("Decrement", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

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
