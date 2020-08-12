package acme.features.authenticated.messages;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.involvedUsers.InvolvedUser;
import acme.entities.messages.Message;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message>{

	@Autowired
	private AuthenticatedMessageRepository repository;
	
	@Override
	public boolean authorise(Request<Message> request) {
		boolean res = false;
		
		int forumId = request.getModel().getInteger("forumId");
		Collection<InvolvedUser> involvedUsers = this.repository.findInvolvedUserByForumId(forumId);
		
		Long count = involvedUsers.stream().filter(x->x.getAuthenticated().getId() == request.getPrincipal().getActiveRoleId()).count();
		
		if (count != 0) {
			res = true;
		}
		return res;
	}

	@Override
	public void bind(Request<Message> request, Message entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "moment");
		
	}

	@Override
	public void unbind(Request<Message> request, Message entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "tags", "body");
	}

	@Override
	public Message instantiate(Request<Message> request) {
		Message result;
		
		UserAccount ua = this.repository.findOneUserAccountById(request.getPrincipal().getAccountId());
		Forum f = this.repository.getForumById(request.getModel().getInteger("forumId"));
		
		result = new Message();
		result.setCreator(ua);
		result.setForum(f);
		return result;
	}

	@Override
	public void validate(Request<Message> request, Message entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String isAccepted;
		String body;
		Integer numberOfWords;
		Integer countSpamWords;
		isAccepted = (String) request.getModel().getAttribute("accept");

		if (!errors.hasErrors("accept")) {
			errors.state(request, isAccepted.equals("true"), "accept", "authenticated.message.error.accept");
		}

		if (!errors.hasErrors("body")) {
			body = request.getModel().getString("body");
			numberOfWords = Arrays.asList(body.split(" ")).size();
			List<String> spamWords = Arrays.asList(this.repository.getSpamWords().split(", "));
			countSpamWords = this.countSpamWords(spamWords, body);
			Double actualPercentage = (double) (countSpamWords / numberOfWords);
			errors.state(request, actualPercentage < this.repository.getThreshold(), "body", "authenticated.message.error.isSpam");
		}
		
	}

	private Integer countSpamWords(final List<String> spamWords, final String body) {
		Integer result = 0;
		for (int i = 0; i < spamWords.size(); i++) {
			String actual = spamWords.get(i);
			Integer numberWordsAct = Arrays.asList(actual.split(" ")).size();
			if (body.contains(actual)) {
				result = result + numberWordsAct;
			}
		}
		return result;
	}
	
	
	@Override
	public void create(Request<Message> request, Message entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
		
	}

	
	
}
