package acme.features.authenticated.involvedUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.involvedUsers.InvolvedUser;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedInvolvedUsersCreateService implements AbstractCreateService<Authenticated, InvolvedUser>{

	@Autowired
	private AuthenticatedInvolvedUsersRepository repository;
	
	@Override
	public boolean authorise(Request<InvolvedUser> request) {
		assert request != null;
		
		boolean res = false;
		int forumId = request.getModel().getInteger("forumId");
		
		Forum forum = this.repository.findForumById(forumId);
		
		if (forum.getUsers().contains(request.getPrincipal().getAccountId())) {
			res = true;
		}
		
		return res;
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
		
		request.unbind(entity, model, "authenticatedUserName");
		
	}

	@Override
	public InvolvedUser instantiate(Request<InvolvedUser> request) {
		assert request != null;
		
		InvolvedUser result = new InvolvedUser();
		int forumdId;
		Authenticated authenticated;
		Forum forum;
		
		forumdId = request.getModel().getInteger("forumId");
		forum = this.repository.findForumById(forumdId);
		authenticated = this.repository.findAuthenticatedByName(request.getModel().getString("searchUser"));
		
		result.setForum(forum);
		result.setAuthenticated(authenticated);
		
		return result;
	}

	@Override
	public void validate(Request<InvolvedUser> request, InvolvedUser entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("searchUer")) {
			errors.state(request, this.repository.countSearchUser(entity.getSearchUser()) == 1, "searchUser", "authenticated.involvedIn.error.searchUser");
			
			errors.state(request, this.repository.countSearchUserInInvolvedUser(entity.getSearchUser(), request.getModel().getInteger("forumId")) < 1, "searchUser", "authenticated.involvedIn.error.searchUserInInvolved");					
		}
	}

	@Override
	public void create(Request<InvolvedUser> request, InvolvedUser entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}
	
	

}
