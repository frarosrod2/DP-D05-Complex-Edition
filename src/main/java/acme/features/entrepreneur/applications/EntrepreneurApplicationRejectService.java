
package acme.features.entrepreneur.applications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import acme.entities.applications.Application;
import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurApplicationRejectService implements AbstractUpdateService<Entrepreneur, Application> {

	@Autowired
	private EntrepreneurApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		boolean result;
		int applicationId;
		Application application;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneApplicationById(applicationId);
		investmentRound = application.getInvestmentRound();
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result && application.getStatus().equals("pending");
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "justification");

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findOneApplicationById(id);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("justification")) {
			Boolean isJustified = !entity.getJustification().equals("");
			errors.state(request, isJustified, "justification", "employer.justification.error.justified");
		}
		// Check spam

		Customisation customisation = this.repository.findCustomisation();

		String[] spamWords = customisation.getSpamWords().split(", ");
		Double threshold = customisation.getSpamThreshold();
		Boolean isSpamTitle = false;

		int numberWordsTitle;
		int numberSpam;

		if (!errors.hasErrors("justification")) {
			String title = entity.getJustification().toLowerCase();
			numberWordsTitle = title.split("\\W+").length;
			numberSpam = 0;
			for (String s : spamWords) {
				numberSpam += StringUtils.countOccurrencesOf(title, s);
			}
			isSpamTitle = numberWordsTitle > 0 && numberSpam * 100 / numberWordsTitle >= threshold;
			errors.state(request, !isSpamTitle, "justification", "entrepreneur.investmentRound.error.isSpam");
		}

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		//		Date moment = new Date(System.currentTimeMillis() - 1);

		entity.setStatus("rejected");
		//		entity.setUpdateStatusMoment(moment);
		this.repository.save(entity);

	}

}
