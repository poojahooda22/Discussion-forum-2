package discusion.forum.activiy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.forum.main.DiscussionForum;
import com.forum.units.Question;
import com.forum.units.Reply;
import com.forum.units.User;
import com.forum.units.UserRole;
import com.forum.util.Utility;

import discussion.forum.units.service.QuestionService;
import discussion.forum.units.service.QuestionServiceImpl;
import discussion.forum.units.service.ReplyService;
import discussion.forum.units.service.ReplyServiceImpl;
import discussion.forum.units.service.UpvoteService;
import discussion.forum.units.service.UpvoteServiceImpl;
import discussion.forum.units.service.UserService;
import discussion.forum.units.service.UserServiceImpl;

public class UserActivity {
	public UserService userService;
	public QuestionService questionService;
	public ReplyService replyService;
	public UpvoteService upvoteService;

	public UserActivity() {
		userService = new UserServiceImpl();
		questionService = new QuestionServiceImpl();
		replyService = new ReplyServiceImpl();
		upvoteService = new UpvoteServiceImpl();
	}

	public User loginActivity() throws IOException {
		System.out.println("Welcome to discussion forum login");
		System.out.println("Enter your username : ");
		String username = Utility.inputFromUser();
		System.out.println("Enter your password : ");
		String password = Utility.inputFromUser();
		User user = userService.getUser(username, password);
		if (user != null) {
			return user;
		}
		System.out.println("You do not have the account. Request admin to get account in discussion forum");
		return null;
	}

	public void createNewUser() throws IOException {
		System.out.println("Enter username : ");
		String username = Utility.inputFromUser();
		System.out.println("Enter password : ");
		String password = Utility.inputFromUser();
		System.out.println("Enter email : ");
		String email = Utility.inputFromUser();
		System.out.println("What role : ");
		UserRole role = DiscussionForum.roleFromMenu();
		userService.createUser(username, password, email, role);
	}

	/**
	 * Ask the user to enter a new question
	 *
	 * @param user: the user who is asking the question
	 * @throws IOException
	 */
	public void postNewQuestion(User user) throws IOException {
		// ask user to write the question title
		System.out.println("Enter question title : ");
		String title = Utility.inputFromUser();

		// ask user to write the question body
		System.out.println("Enter question : ");
		// ** Please write your code below **
		// Please write code that follows the instruction below
		// 1. Use Utility.inputFromUser() to get the question body that the user entered
		// 2. Store the question body in a String variable called message

		questionService.createQuestion(title, message, user);
	}

	/**
	 * This method returns all of the questions that are stored in the class forum
	 *
	 * @param userActivity: the activity history of the user who is viewing this question
	 * @param user: the user who is viewing this question
	 *
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void seeAllQuestions(UserActivity userActivity, User user) throws NumberFormatException, IOException {
		ArrayList<Question> questions = QuestionServiceImpl.questions; // getting all the questions

		// ** Please write your code below: **
		//
		// Replace the ... in the if statement condition with correct boolean statement. You should follow the instruction
		// below:
		// 1) If there are 0 questions, print out "No question posted yet"
		// 2) If there are 1 or more questions, print out information about the question
		//
		// You can check how many the number of questions by using the size() function on the questions variable
		// such as: questions.size()
		//
		// The code for printing out "No question posted yet" and information about the question
		// has been written for you. You simply need to replace the ... in the if statement condition.

		if ( ... ) {
			System.out.println("No question posted yet");
		} else {
			sort(questions); // sorting the questions from the newest to the oldest
			for (Question question : questions) { // printing out information about each question
				System.out.println(question.getId() + ". Question Title - " + question.getTitle());
				System.out.println("Question - " + question.getMessage());
				System.out.println("Upvote - " + question.getUpvoteCount());
			}
			DiscussionForum.questionMenu(userActivity, user);
		}
	}

	public void sort(ArrayList<Question> questions) {
		Collections.sort(questions, new Comparator<Question>() {
			public int compare(Question q1, Question q2) {
				if (q1.getUpvoteCount() == q2.getUpvoteCount())
					return 0;

				return q1.getUpvoteCount() < q1.getUpvoteCount() ? 1 : -1;
			}
		});
	}

	public void upvoteQuestion(User user) throws NumberFormatException, IOException {
		System.out.println("Enter question number you want to upvote : ");
		upvoteService.addUpvote(getQuestion(), user);
	}

	public void replyToQuestion(User user) throws IOException {
		System.out.println("Enter question number you want to reply to : ");
		Question question = getQuestion();
		System.out.println("Post your reply");
		replyService.addReply(Utility.inputFromUser(), question, user);
	}

	/**
	 *
	 * @param userActivity: the user's activity history
	 * @param user: the current logged in user
	 *
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void deleteQuestion(UserActivity userActivity, User user) throws NumberFormatException, IOException {
		System.out.println("Enter question number you want to delete : ");
		Question question = getQuestion();

		// ** Please write your code below: **
		//
		// Note: This is the most challenging part of the project, so please don't worry if you don't get it correct
		// on the first try. Please feel free to look at the solution if you are stuck.
		//
		// Write the logic to delete a question from the class forum, and you should follow the rules below:
		// 1) If the current user's role is an admin, the user can delete the question
		//
		// 2) Else if the current user's role is a moderator, the user can delete the question only:
		//    a) If the user who wrote the question is a regular user
		//    b) Or the current user is also the user who wrote the question
		//    c) Otherwise, you should print: "You are not authorised to delete this question"
		//
		// 3) Else if the current user is also the user who wrote the question, we can delete the question.
		// 	  Otherwise, you should print: "You are not authorised to delete this question"
		//
		// ** A few tips and pointers: **
		// 1) For a question, you can use question.getUser() to get the user who wrote the question
		//
		// 2) For a user, you can use user.getUserRole() to get the user's role
		//
		// 3) User roles are defined as:
		// 	  a) admin role: UserRole.ADMIN
		//    b) moderator role: UserRole.MODERATOR
		//    c) regular user role: UserRole.USER
		////
		// 5) You can use userA == userB to compare if two users are the same person
		//
		// 6) You can user questionService.deleteQuestion(question) to delete the question


		if (user.getUserRole() == UserRole.ADMIN) { // the user is an admin
			questionService.deleteQuestion(question); // deleting a question
		}
		// Write else if condition to check if the user is a moderator
		// Write else if condition to check if the user is a regular user


		if (QuestionServiceImpl.questions.size() == 0)
			DiscussionForum.menu(user, userActivity);
	}

	private Question getQuestion() throws NumberFormatException, IOException {
		Question question;
		while (true) {
			question = questionService.getQuestionById(Long.parseLong(Utility.inputFromUser()));
			if (question != null)
				break;
			System.out.println("Enter correct question from displayed questions");
		}
		return question;
	}

	public void seeAllReplies(UserActivity userActivity, User user) throws NumberFormatException, IOException {
		System.out.println("For which question number you want to see replies : ");
		Question question = getQuestion();
		ArrayList<Reply> replies = replyService.getReplies(question);
		if (replies.size() == 0) {
			System.out.println("No reply posted yet");
		} else {
			for (Reply reply : replies) {
				System.out.println(reply.getId() + ". Comment - " + reply.getMessage());
				System.out.println("Upvote - " + upvoteService.upvoteCount(reply));
			}
			DiscussionForum.replyMenu(userActivity, user, question);
		}
	}

	public void upvoteReply(User user) throws NumberFormatException, IOException {
		System.out.println("Enter reply number you want to upvote : ");
		upvoteService.addUpvote(getReply(), user);
	}

	public void deleteReply(Question question, UserActivity userActivity, User user) throws NumberFormatException, IOException {
		System.out.println("Enter reply number you want to delete : ");
		Reply reply = getReply();
		if (user.getUserRole() == UserRole.ADMIN) {
			replyService.deleteReply(reply);
		} else if (user.getUserRole() == UserRole.MODERATOR) {
			if (reply.getUser().getUserRole() == UserRole.USER) {
				replyService.deleteReply(reply);
			} else if (reply.getUser() == user) {
				replyService.deleteReply(reply);
			} else {
				System.out.println("You are not authorised to delete this reply");
			}
		} else {
			if (reply.getUser() == user) {
				replyService.deleteReply(reply);
			} else {
				System.out.println("You are not authorised to delete this reply");
			}
		}
		if (replyService.getReplies(question).size() == 0)
			DiscussionForum.questionMenu(userActivity, user);

	}

	private Reply getReply() throws NumberFormatException, IOException {
		Reply reply;
		while (true) {
			reply = replyService.getReply(Long.parseLong(Utility.inputFromUser()));
			if (reply != null)
				break;
			System.out.println("Enter correct reply from displayed replies");
		}
		return reply;
	}

}
