package acme.features.authenticated.involvedUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.involvedUsers.InvolvedUser;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInvolvedUsersShowService implements AbstractShowService<Authenticated, InvolvedUser>{

	@Autowired
	private AuthenticatedInvolvedUsersRepository repository;
	
	@Override
	public boolean authorise(Request<InvolvedUser> request) {
		assert request != null;
		return false;
	}

	@Override
	public void unbind(Request<InvolvedUser> request, InvolvedUser entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "authenticatedUserName");
		
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
	
	

}
