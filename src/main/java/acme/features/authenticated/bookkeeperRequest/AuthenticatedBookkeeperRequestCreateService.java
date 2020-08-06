package acme.features.authenticated.bookkeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.BookkeeperRequest;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedBookkeeperRequestCreateService implements AbstractCreateService<Authenticated, BookkeeperRequest>{

	@Autowired
	private AuthenticatedBookkeeperRequestRepository repository;
	
	@Override
	public boolean authorise(Request<BookkeeperRequest> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(Request<BookkeeperRequest> request, BookkeeperRequest entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<BookkeeperRequest> request, BookkeeperRequest entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
	
		request.unbind(entity, model, "name", "responsabilityStatement");
	}

	@Override
	public BookkeeperRequest instantiate(Request<BookkeeperRequest> request) {
		BookkeeperRequest result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;
		
		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);
		
		result = new BookkeeperRequest();
		result.setUserAccount(userAccount);
		result.setState("Pending");
		
		return result;
	}

	@Override
	public void validate(Request<BookkeeperRequest> request, BookkeeperRequest entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void create(Request<BookkeeperRequest> request, BookkeeperRequest entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}
	
}
