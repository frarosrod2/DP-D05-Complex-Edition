package acme.features.administrator.bookkeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.BookkeeperRequest;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorBookkeeperRequestShowService implements AbstractShowService<Administrator, BookkeeperRequest>{

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
		request.unbind(entity, model, "name");
		
	}

	@Override
	public BookkeeperRequest findOne(Request<BookkeeperRequest> request) {
		assert request != null;
		
		BookkeeperRequest result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		
		return result;
	}
	
	

}