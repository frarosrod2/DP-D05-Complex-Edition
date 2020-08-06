
package acme.features.authenticated.forums;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.forums.Forum;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedForumRepository extends AbstractRepository {

	@Query("SELECT a.forums FROM Authenticated a WHERE a.id = ?1")
	Collection<Forum> getUserInvolvedForums(int authenticatedId);

	@Query("SELECT a FROM Authenticated a")
	Collection<Authenticated> getAuthenticatedUsers();

	@Query("SELECT a FROM Authenticated a WHERE a.userAccount.username IN ?1")
	Collection<Authenticated> getAuthenticatedUsersByUsernames(String[] usernames);

	@Query("SELECT a FROM Authenticated a WHERE a.userAccount.id = ?1")
	Authenticated getAuthenticatedByAccountId(int accountId);

	@Query("select t from Forum t where t.id = ?1")
	Forum getForumById(int forumId);
}
