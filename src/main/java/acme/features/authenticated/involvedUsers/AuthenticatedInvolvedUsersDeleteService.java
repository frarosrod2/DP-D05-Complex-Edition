package acme.features.authenticated.involvedUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void bind(Request<InvolvedUser> request, InvolvedUser entity, Errors errors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unbind(Request<InvolvedUser> request, InvolvedUser entity, Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InvolvedUser findOne(Request<InvolvedUser> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validate(Request<InvolvedUser> request, InvolvedUser entity, Errors errors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Request<InvolvedUser> request, InvolvedUser entity) {
		// TODO Auto-generated method stub
		
	}
	
	

}
