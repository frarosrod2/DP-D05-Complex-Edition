package acme.features.authenticated.forums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedForumDeleteService implements AbstractDeleteService<Authenticated, Forum>{

	@Autowired
	private AuthenticatedForumRepository repository;
	
	@Override
	public boolean authorise(Request<Forum> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(Request<Forum> request, Forum entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(Request<Forum> request, Forum entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "moment");
		
	}

	@Override
	public Forum findOne(Request<Forum> request) {
		assert request != null;
		
		Forum forum;
		
		int id;
		
		id = request.getModel().getInteger("id");
		forum = this.repository.getForumById(id);
		
		return forum;
	}

	@Override
	public void validate(Request<Forum> request, Forum entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(Request<Forum> request, Forum entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
		
	}
	

}
