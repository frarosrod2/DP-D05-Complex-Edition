
package acme.features.entrepreneur.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurActivityCreateService implements AbstractCreateService<Entrepreneur, Activity> {

	@Autowired
	private EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		int investmentRoundId = request.getModel().getInteger("investmentRoundId");
		Principal principal = request.getPrincipal();
		InvestmentRound investmentRound = this.repository.findOneInvestmentRoundById(investmentRoundId);
		return !investmentRound.isFinalMode() && investmentRound.getEntrepreneur().getUserAccount().getId() == principal.getAccountId();
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "start", "end", "budget");
	}

	@Override
	public Activity instantiate(final Request<Activity> request) {
		assert request != null;
		Activity activity;
		activity = new Activity();
		return activity;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;

		int investmentRoundId = request.getModel().getInteger("investmentRoundId");
		InvestmentRound investmentRound = this.repository.findOneInvestmentRoundById(investmentRoundId);

		Activity createdActivity = this.repository.save(entity);
		investmentRound.getWorkProgramme().add(createdActivity);
	}

}
