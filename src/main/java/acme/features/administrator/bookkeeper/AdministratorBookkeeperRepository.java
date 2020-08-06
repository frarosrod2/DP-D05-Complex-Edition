package acme.features.administrator.bookkeeper;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBookkeeperRepository extends AbstractRepository{

	@Query("select ua from UserAccount ua where ua.id =? 1")
	UserAccount findOneUserAccountById(int id);
	
}

