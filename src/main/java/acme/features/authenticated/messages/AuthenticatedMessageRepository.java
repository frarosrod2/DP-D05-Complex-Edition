
package acme.features.authenticated.messages;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisations.Customisation;
import acme.entities.forums.Forum;
import acme.entities.involvedUsers.InvolvedUser;
import acme.entities.messages.Message;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("SELECT a.forums FROM Authenticated a WHERE a.id = ?1")
	Collection<Forum> getUserInvolvedForums(int authenticatedId);

	@Query("SELECT a FROM Authenticated a")
	Collection<Authenticated> getAuthenticatedUsers();

	@Query("SELECT a FROM Authenticated a WHERE a.userAccount.username IN ?1")
	Collection<Authenticated> getAuthenticatedUsersByUsernames(String[] usernames);

	@Query("SELECT t FROM Forum t WHERE t.id = ?1")
	Forum getForumById(int forumId);

	@Query("select m from Message m where m.forum.id = ?1")
	Collection<Message> findMessagesByForumId(int forumId);
	
	@Query("SELECT c FROM Customisation c")
	Customisation findCustomisation();

	@Query("select i from InvolvedUser i where i.forum.id = ?1")
	Collection<InvolvedUser> findInvolvedUserByForumId(int forumId);
	
	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int accountId);
	
	@Query("select c.spamwords from Config c")
	String getSpamWords();

	@Query("select c.spamth from Config c")
	Double getThreshold();

	@Query("select m from Message m where m.id = ?1")
	Message findOneMessageById(int id);
}
