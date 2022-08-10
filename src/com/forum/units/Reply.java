package com.forum.units;

public class Reply extends AbstractEntity {

	//reply body
	private String message;
	//user who replied to the question
	private User user;
	//question for which user has replied
	private Question question;
	//id of the last reply posted on discussion forum
	private static Long lastEntry = 0L;

	public void autoGenerateId() {
		lastEntry = lastEntry + 1L;

		// please write your code below and follow this instruction:
		// 1. Call the parent class' setId method and pass in lastEntry as the method variable;
		//
		// Note: You can read the setId method in the AbstractEntity class to understand how the setId method works
	}

	/**
	 * This method returns the reply body
	 *
	 * @return the reply body
	 */
	public String getMessage() {
		// please write your code here
		// You should return the instance variable message
	}

	/**
	 * This method sets the body of the reply
	 *
	 * @param message: the message that we want to set as the reply body
	 */
	public void setMessage(String message) {
		// please write your code here
		// You should set the instance variable message to the
		// message variable that's passed into this function
	}

	/**
	 * This method returns the user who wrote the reply
	 *
	 * @return the user who wrote this reply
	 */
	public User getUser() {
		// please write your code here
		// You should return the user instance variable
	}

	/**
	 * This method sets the user who wrote the reply
	 *
	 * @param user: the user who wrote this reply
	 */
	public void setUser(User user) {
		// please write your code here
		// You should set the instance variable user to the user variable
		// that is passed into this method
	}

	/**
	 * This method returns the question that this reply belongs to
	 *
	 * @return the question to which this reply belongs to
	 */
	public Question getQuestion() {
		// please write your code here
		// You should set the instance variable question
	}

	/**
	 * This method sets the question that this reply belongs to
	 *
	 * @param question: the question that this reply belongs to
	 */
	public void setQuestion(Question question) {
		// please write your code here
		// You should set the instance variable question to the question variable
		// that is passed into this method
	}
}
