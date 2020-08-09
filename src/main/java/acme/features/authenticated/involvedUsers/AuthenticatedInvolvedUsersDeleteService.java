package acme.features.authenticated.involvedUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.involvedUsers.InvolvedUser;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedInvolvedUsersDeleteService implements AbstractDeleteService<Authenticated, InvolvedUser>{

	@Autowired
	private AuthenticatedInvolvedUsersRepository repository;
	
	@Override
	public boolean authorise(Request<InvolvedUser> request) {
		assert request != null;
		
		int involvedUserId = request.getModel().getInteger("id");
		int forumId = this.repository.findForumIdByInvolvedUserId(involvedUserId);
		Forum forum = this.repository.findForumById(forumId);
		int authenticatedId = this.repository.findAuthenticatedIdByInvolvedUserId(involvedUserId);
		
		boolean isCreator = forum.getCreator().getId() == request.getPrincipal().getAccountId();
		boolean notCreator = authenticatedId != request.getPrincipal().getAccountId();
		
		return isCreator && notCreator;
	}

	@Override
	public void bind(Request<InvolvedUser> request, InvolvedUser entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(Request<InvolvedUser> request, InvolvedUser entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;		

		request.unbind(entity, model, "searchUser");
	}

	@Override
	public InvolvedUser findOne(Request<InvolvedUser> request) {
		assert request != null;
		
		InvolvedUser result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOne(id);
		
		return result;
	}

	@Override
	public void validate(Request<InvolvedUser> request, InvolvedUser entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(Request<InvolvedUser> request, InvolvedUser entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
	
	

}
