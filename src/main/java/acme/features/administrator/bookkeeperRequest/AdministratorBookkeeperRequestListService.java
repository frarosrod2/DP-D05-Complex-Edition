package acme.features.administrator.bookkeeperRequest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.BookkeeperRequest;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorBookkeeperRequestListService implements AbstractListService<Administrator, BookkeeperRequest>{

	@Autowired
	private AdministratorBookkeeperRequestRepository repository;
	
	@Override
	public boolean authorise(Request<BookkeeperRequest> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(Request<BookkeeperRequest> request, BookkeeperRequest entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "name", "responsabilityStatement", "state");
		
	}

	@Override
	public Collection<BookkeeperRequest> findMany(Request<BookkeeperRequest> request) {
		assert request != null;
		Collection<BookkeeperRequest> result;
		result = this.repository.findMany();
		return result;
	}

	
	
	
	
}
