package acme.features.administrator.bookkeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.BookkeeperRequest;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@RequestMapping("administrator/bookkeeper-request")
@Controller
public class AdministratorBookkeeperRequestController extends AbstractController<Administrator, BookkeeperRequest>{

	@Autowired
	AdministratorBookkeeperRequestListService listService;
	
	@Autowired
	AdministratorBookkeeperRequestShowService showService;
	
	@Autowired
	AdministratorBookkeeperRequestUpdateService updateService;
	
}
