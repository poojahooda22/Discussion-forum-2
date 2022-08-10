package com.forum.main;

import java.io.IOException;

import com.forum.units.Question;
import com.forum.units.User;
import com.forum.units.UserRole;
import com.forum.util.Utility;

import discusion.forum.activiy.UserActivity;

public class DiscussionForum {
	
	public static void main(String args[]) throws IOException {
		User user;
		UserActivity userActivity = new UserActivity();
		userActivity.userService.createUser("admin", "admin", "admin", UserRole.ADMIN);
		while (true) {
			user = userActivity.loginActivity();
			if (user == null)
				continue;
			System.out.println("Welcome " + user.getUsername());
			menu(user, userActivity);
		}
	}
	
	public static void menu(User user, UserActivity userActivity) throws NumberFormatException, IOException {
		while (true) {
			int menuIndex = 0;
			if (user.getUserRole() == UserRole.ADMIN) {
				System.out.println(++menuIndex + " Create new user");
			}
			System.out.println(++menuIndex + " Ask a question");
			System.out.println(++menuIndex + " See all questions");
			System.out.println(++menuIndex + " Log Out");
			System.out.println("\n Enter your choice");
			if (!classifyMenuChoice(Integer.parseInt(Utility.inputFromUser()), userActivity, user))
				break;
			
		}
	}
	
	public static boolean classifyMenuChoice(int choice, UserActivity userActivity, User user) throws IOException {
		if (UserRole.ADMIN != user.getUserRole()) {
			choice++;
		}
		while (true) {
			switch (choice) {
				case 1:
					userActivity.createNewUser();
					return true;
				case 2:
					userActivity.postNewQuestion(user);
					return true;
				case 3:
					userActivity.seeAllQuestions(userActivity, user);
					return true;
				case 4:
					return false;
				default:
					System.out.println("Wrong choice. Try again");
			}
			System.out.println("\n Enter your choice");
			choice = Integer.parseInt(Utility.inputFromUser());
		}
	}
	
	public static UserRole roleFromMenu() throws NumberFormatException, IOException {
		int menuIndex = 0;
		System.out.println(++menuIndex + UserRole.ADMIN.toString());
		System.out.println(++menuIndex + UserRole.MODERATOR.toString());
		System.out.println(++menuIndex + UserRole.USER.toString());
		while (true) {
			System.out.println("\n Enter your choice");
			int choice = Integer.parseInt(Utility.inputFromUser());
			switch (choice) {
				case 1:
					return UserRole.ADMIN;
				case 2:
					return UserRole.MODERATOR;
				case 3:
					return UserRole.USER;
				default:
					System.out.println("Wrong choice. Try again");
			}
		}
	}
	
	public static void questionMenu(UserActivity userActivity, User user) throws NumberFormatException, IOException {
		while (true) {
			int menuIndex = 0;
			System.out.println(++menuIndex + " Upvote a question");
			System.out.println(++menuIndex + " Reply to a question");
			System.out.println(++menuIndex + " See replies for a question");
			System.out.println(++menuIndex + " Delete a question");
			System.out.println(++menuIndex + " Return to main menu");
			System.out.println("\n Enter your choice");
			if (!processQuestionChoice(Integer.parseInt(Utility.inputFromUser()), userActivity, user)) {
				break;
			}
		}
	}
	
	public static boolean processQuestionChoice(int choice, UserActivity userActivity, User user) throws NumberFormatException, IOException {
		while (true) {
			switch (choice) {
				case 1:
					userActivity.upvoteQuestion(user);
					return true;
				case 2:
					userActivity.replyToQuestion(user);
					return true;
				case 3:
					userActivity.seeAllReplies(userActivity, user);
					return true;
				case 4:
					userActivity.deleteQuestion(userActivity, user);
					return true;
				case 5:
					return false;
				default:
					System.out.println("Wrong choice. Try again");
			}
			System.out.println("Enter your choice");
			choice = Integer.parseInt(Utility.inputFromUser());
		}
	}
	
	public static void replyMenu(UserActivity userActivity, User user, Question question) throws NumberFormatException, IOException {
		while (true) {
			int menuIndex = 0;
			System.out.println(++menuIndex + " Upvote a reply");
			System.out.println(++menuIndex + " Delete a reply");
			System.out.println(++menuIndex + " Return to question menu");
			System.out.println("\n Enter your choice");
			if (!processReplyChoice(Integer.parseInt(Utility.inputFromUser()), userActivity, user, question)) {
				break;
			}
		}
	}
	
	public static boolean processReplyChoice(int choice, UserActivity userActivity, User user, Question question) throws NumberFormatException, IOException {
		while (true) {
			switch (choice) {
				case 1:
					userActivity.upvoteReply(user);
					return true;
				case 2:
					userActivity.deleteReply(question, userActivity, user);
					return true;
				case 3:
					return false;
				default:
					System.out.println("Wrong choice. Try again");
			}
		}
	}
}
