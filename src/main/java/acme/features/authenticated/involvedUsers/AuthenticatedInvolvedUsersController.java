package acme.features.authenticated.involvedUsers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.involvedUsers.InvolvedUser;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/involvedUsers")
public class AuthenticatedInvolvedUsersController extends AbstractController<Authenticated, InvolvedUser>{

	@Autowired
	private AuthenticatedInvolvedUsersListService listService;
	
	@Autowired
	private AuthenticatedInvolvedUsersShowService showServie;
	
	@Autowired
	private AuthenticatedInvolvedUsersCreateService createService;
	
	@Autowired
	private AuthenticatedInvolvedUsersDeleteService deleteService;
	/*
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, listService);
		super.addBasicCommand(BasicCommand.SHOW, showService);
		super.addBasicCommand(BasicCommand.CREATE, createService);
		super.addBasicCommand(BasicCommand.DELETE, deleteService);
	}
	*/
}
