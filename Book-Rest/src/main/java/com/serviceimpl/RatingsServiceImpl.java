package com.serviceimpl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dao.BookDao;
import com.dao.RatingsDao;
import com.dao.UserDao;
import com.jwt.JwtUtils;
import com.pojo.Book;
import com.pojo.Ratings;
import com.pojo.User;
import com.service.RatingsService;


/**
 * This class provides implementations for managing book ratings and comments in the system.
 * It includes a method for adding ratings to books.
 */
@Service
public class RatingsServiceImpl implements RatingsService {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RatingsDao ratingsDao;

	@Autowired
	private BookDao bookDao;

	
	/**
     * Adds a user rating and comment for a book in the system.
     *
     * @param map A map containing data for rating and comments, including book ID, rating value, and comments.
     * @return ResponseEntity with a success message if the rating is added (HTTP status OK),
     *         or an error message with an internal server error status if an exception occurs.
     */
	@Override
	public ResponseEntity<String> addRating(Map<String, String> map) {

		try {

			// Extract the required data from the map
			Integer bookId = Integer.parseInt(map.get("bookId"));
			Double ratingValue = Double.parseDouble(map.get("rating"));
			String comments = map.get("comment");

			// Get the authenticated user
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userToken = authentication.getName();
			String username = jwtUtils.extractUsername(userToken);
			User user = userDao.getUserByUserName(username);

			// Check if the book exists
			Optional<Book> optionalBook = bookDao.findById(bookId);
			if (optionalBook.isPresent()) {
				Book book = optionalBook.get();

				// Create a new rating
				Ratings ratings = new Ratings();
				ratings.setRating(ratingValue);
				ratings.setUser(user);
				ratings.setRatingPostDate(new Date());
				ratings.setComment(comments);

				// Save the rating
				ratingsDao.save(ratings);

				// Add the rating to the book's list of ratings
				List<Ratings> existingRatings = book.getListRatings();
				existingRatings.add(ratings);
				book.setListRatings(existingRatings);

				// Calculate and update the average rating for the book
				double sum = 0.0;
				for (Ratings r : existingRatings) {
					sum += r.getRating();
				}
				double averageRating = existingRatings.isEmpty() ? 0.0 : sum / existingRatings.size();
				DecimalFormat df = new DecimalFormat("#.#");
				String formattedValue = df.format(averageRating);
				double roundedValue = Double.parseDouble(formattedValue);
				book.setAverageRating(roundedValue);

				// Save the updated book with the average rating
				bookDao.save(book);

				return new ResponseEntity<String>("Rating added successfully", HttpStatus.OK);

			}
			return new ResponseEntity<String>("Error adding rating", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Error adding rating", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
