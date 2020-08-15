
package acme.features.bookkeeper.accountingRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class BookkeeperAccountingRecordCreateService implements AbstractCreateService<Bookkeeper, AccountingRecord> {

	@Autowired
	private BookkeeperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");

	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("investmentRoundId", request.getModel().getInteger("investmentRoundId"));
		request.unbind(entity, model, "title", "creationMoment", "status", "body");
	}

	@Override
	public AccountingRecord instantiate(final Request<AccountingRecord> request) {
		assert request != null;

		AccountingRecord result;
		InvestmentRound investmentRound;
		int investmentRoundId;
		Date moment;
		int userAccountId;
		Bookkeeper bookkeeper;

		result = new AccountingRecord();
		investmentRoundId = request.getModel().getInteger("investmentRoundId");
		investmentRound = this.repository.findInvestmentRound(investmentRoundId);
		result.setInvestmentRound(investmentRound);

		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);

		userAccountId = request.getPrincipal().getActiveRoleId();
		bookkeeper = this.repository.findBookkeeperById(userAccountId);
		result.setBookkeeper(bookkeeper);

		return result;
	}

	@Override
	public void validate(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AccountingRecord> request, final AccountingRecord entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);

	}

}
