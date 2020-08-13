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
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class BookkeeperAccountingRecordCreateService implements AbstractCreateService<Bookkeeper, AccountingRecord>{

	@Autowired
	private BookkeeperAccountingRecordRepository repository;
	
	@Override
	public boolean authorise(Request<AccountingRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(Request<AccountingRecord> request, AccountingRecord entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "investmentRound");
		
	}

	@Override
	public void unbind(Request<AccountingRecord> request, AccountingRecord entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "status", "body", "bookkeeper");
	}

	@Override
	public AccountingRecord instantiate(Request<AccountingRecord> request) {
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
	public void validate(Request<AccountingRecord> request, AccountingRecord entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void create(Request<AccountingRecord> request, AccountingRecord entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
		
	}

	
	
}
