package acme.features.authenticated.bookkeeperRequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.BookkeeperRequest;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBookkeeperRequestRepository extends AbstractRepository{
	
	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select br from BookkeeperRequest br where br.userAccount.id = ?1")
	BookkeeperRequest findOneBookkeeperRequestByUserAccountId(int id);
}
