package acme.features.authenticated.involvedUsers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.involvedUsers.InvolvedUser;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedInvolvedUsersListService implements AbstractListService<Authenticated, InvolvedUser>{

	@Autowired
	private AuthenticatedInvolvedUsersRepository repository;
	
	@Override
	public boolean authorise(Request<InvolvedUser> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(Request<InvolvedUser> request, InvolvedUser entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "authenticatedUserName");
		
	}

	@Override
	public Collection<InvolvedUser> findMany(Request<InvolvedUser> request) {
		assert request != null;
		
		int id = request.getModel().getInteger("id");
		
		Collection<InvolvedUser> result;
		result = this.repository.findMany(id);
		
		return result;
	}
	
	

}
