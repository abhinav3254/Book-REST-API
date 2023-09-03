package com.constants;

public class Constants {
	
	
	private Constants() {}
	
	public static final String SOMETHING_WENT_WRONG = "{"
			+ "\n message : SOMETHING WENT WRONG\n"
					+ "}";
	
	public static String designMessage(String msg) {
		 String message = "{"
				+ "\n message : "+msg+"\n"
						+ "}";
		 return message;
	}
}
