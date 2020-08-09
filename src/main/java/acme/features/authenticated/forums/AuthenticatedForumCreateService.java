package acme.features.authenticated.forums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedForumCreateService implements AbstractCreateService<Authenticated, Forum>{

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
		
		request.bind(entity, errors, "moment");
		
	}

	@Override
	public void unbind(Request<Forum> request, Forum entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title");
		
	}

	@Override
	public Forum instantiate(Request<Forum> request) {
		assert request != null;

		
		Forum result;
		result = new Forum();

		
		int id;
		id = request.getPrincipal().getAccountId();
		
		Authenticated user = this.repository.getAuthenticatedByAccountId(id);
		Collection<Authenticated> c = new ArrayList<Authenticated>();
		c.add(user);
		result.setUsers(c);
		
		Date moment;
		moment = new Date(System.currentTimeMillis() -1);
		result.setMoment(moment);
		
		
		return result;
	}

	@Override
	public void validate(Request<Forum> request, Forum entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void create(Request<Forum> request, Forum entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;
		
		moment = new Date(System.currentTimeMillis() -1);
		entity.setMoment(moment);
		
		this.repository.save(entity);
		
	}

}
