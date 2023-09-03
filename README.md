# Book-API
use where clause


use this

@Query(nativeQuery = true, value =
		    "SELECT " +
		    "orders.id AS order_id, " +
		    "orders.some_other_column AS order_some_other_column, " +
		    "book.id AS book_id, " +
		    "book.some_other_column AS book_some_other_column, " +
		    "user.id AS user_id, " +
		    "user.some_other_column AS user_some_other_column " +
		    "FROM orders " +
		    "JOIN book ON book.id = orders.book_id " +
		    "JOIN user ON user.id = orders.user_id " +
		    "WHERE user.id = :llid;")
		List<GetOrders> getOrders(@Param("llid") String llid);
