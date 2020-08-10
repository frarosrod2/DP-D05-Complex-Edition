
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
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedForumCreateService implements AbstractCreateService<Authenticated, Forum> {

	@Autowired
	private AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String[] usernames = request.getServletRequest().getParameterValues("users");
		Collection<Authenticated> usersInvolved = this.repository.getAuthenticatedUsersByUsernames(usernames);
		Collection<Authenticated> authenticatedUsers = this.repository.getAuthenticatedUsers();

		request.getModel().setAttribute("users", usersInvolved);
		request.getModel().setAttribute("authenticatedUsers", authenticatedUsers);

		request.bind(entity, errors, "users", "moment");

	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Collection<Authenticated> authenticatedUsers = this.repository.getAuthenticatedUsers();
		request.unbind(entity, model, "title", "users");
		model.setAttribute("authenticatedUsers", authenticatedUsers);

	}

	@Override
	public Forum instantiate(final Request<Forum> request) {
		assert request != null;

		Forum result;
		result = new Forum();

		int id;
		id = request.getPrincipal().getAccountId();

		Authenticated user = this.repository.getAuthenticatedByAccountId(id);
		Collection<Authenticated> c = new ArrayList<Authenticated>();
		c.add(user);
		result.setUsers(c);
		result.setCreator(user);

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		Integer accountId = request.getPrincipal().getAccountId();
		Authenticated currentAuthenticated = this.repository.getAuthenticatedByAccountId(accountId);
		Collection<Authenticated> involved = entity.getUsers();

		if (!involved.contains(currentAuthenticated)) {
			involved.add(currentAuthenticated);
		}

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

	}

}
