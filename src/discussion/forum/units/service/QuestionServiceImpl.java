package discussion.forum.units.service;

import java.util.ArrayList;

import com.forum.units.Question;
import com.forum.units.User;
import com.forum.util.Utility;

public class QuestionServiceImpl implements QuestionService {
	public static ArrayList<Question> questions = new ArrayList<>();
	
	public Question createQuestion(String title, String message, User user) {
		if (Utility.isNotNullAndEmpty(title) && Utility.isNotNullAndEmpty(message) && user != null) {
			Question question = getQuestionByBody(message);
			if (question != null) {
				System.out.println("Asked question already exists with same body");
				return question;
			}
			question = new Question();
			question.autoGenerateId();
			question.setTitle(title);
			question.setMessage(message);
			question.setUser(user);
			question.setCreated();
			questions.add(question);
			return question;
		}
		System.out.println("Any specified field can't be empty");
		return null;
	}
	
	private Question getQuestionByBody(String questionMessage) {
		for (Question question : questions) {
			if (question.getMessage().equals(questionMessage)) {
				return question;
			}
		}
		return null;
	}
	
	public Question getQuestionById(long id) {
		for (Question question : questions) {
			if (question.getId() == id) {
				return question;
			}
		}
		return null;
	}
	
	public void deleteQuestion(Question question) {
		questions.remove(question);
	}
}
