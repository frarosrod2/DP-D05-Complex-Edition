
package acme.features.authenticated.messages;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisations.Customisation;
import acme.entities.forums.Forum;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("SELECT i.forum FROM InvolvedUser i WHERE i.id = ?1")
	Collection<Forum> getUserInvolvedForums(int authenticatedId);

	@Query("SELECT a FROM Authenticated a")
	Collection<Authenticated> getAuthenticatedUsers();

	@Query("SELECT a FROM Authenticated a WHERE a.userAccount.username IN ?1")
	Collection<Authenticated> getAuthenticatedUsersByUsernames(String[] usernames);

	@Query("SELECT t FROM Forum t WHERE t.id = ?1")
	Forum getForumById(int forumId);

	@Query("SELECT c FROM Customisation c")
	Customisation findCustomisation();
}
