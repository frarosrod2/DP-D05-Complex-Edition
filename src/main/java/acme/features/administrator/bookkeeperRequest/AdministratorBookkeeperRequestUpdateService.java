package acme.features.administrator.bookkeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Bookkeeper;
import acme.entities.roles.BookkeeperRequest;
import acme.features.administrator.bookkeeper.AdministratorBookkeeperRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorBookkeeperRequestUpdateService implements AbstractUpdateService<Administrator, BookkeeperRequest>{

	@Autowired
	private AdministratorBookkeeperRequestRepository repository;
	
	@Autowired
	private AdministratorBookkeeperRepository repositoryBK;
	
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
		
		request.unbind(entity, model, "name", "responsabilityStatement", "state");
		
		
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

	@Override
	public void validate(Request<BookkeeperRequest> request, BookkeeperRequest entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(Request<BookkeeperRequest> request, BookkeeperRequest entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		String state = entity.getState();
		
		if (state.equals("Accepted")) {
			Bookkeeper bk = new Bookkeeper();
			
			bk.setName(entity.getName());
			bk.setResponsabilityStatement(entity.getResponsabilityStatement());
			bk.setUserAccount(entity.getUserAccount());
			
			this.repositoryBK.save(bk);
		}
		
	}

	
	
}
