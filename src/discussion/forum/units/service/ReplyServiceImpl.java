package discussion.forum.units.service;

import java.util.ArrayList;

import com.forum.units.Question;
import com.forum.units.Reply;
import com.forum.units.User;
import com.forum.util.Utility;

public class ReplyServiceImpl implements ReplyService {
	public static ArrayList<Reply> replies = new ArrayList<>();
	
	public Reply addReply(String message, Question question, User user) {
		if (Utility.isNotNullAndEmpty(message) && question != null && user != null) {
			Reply reply = getReply(question, message);
			if (reply != null) {
				System.out.println("This reply is already present for this question");
				return reply;
			}
			reply = new Reply();
			reply.setMessage(message);
			reply.setQuestion(question);
			reply.setUser(user);
			reply.autoGenerateId();
			reply.setCreated();
			replies.add(reply);
			return reply;
		}
		System.out.println("Any specified field can't be empty");
		return null;
	}
	
	private Reply getReply(Question question, String replyMessage) {
		for (Reply reply : replies) {
			if ((reply.getQuestion() == question) && reply.getMessage().equals(replyMessage)) {
				return reply;
			}
		}
		return null;
	}
	
	public Reply getReply(long id) {
		for (Reply reply : replies) {
			if (reply.getId() == id) {
				return reply;
			}
		}
		return null;
	}
	
	public ArrayList<Reply> getReplies(Question question) {
		ArrayList<Reply> repliesToQuestion = new ArrayList<>();
		for (Reply reply : replies) {
			if (reply.getQuestion() == question) {
				repliesToQuestion.add(reply);
			}
		}
		return repliesToQuestion;
	}
	
	public void deleteReply(Reply reply) {
		replies.remove(reply);
	}
}
