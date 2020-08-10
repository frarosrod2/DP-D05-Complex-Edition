
package acme.entities.forums;

import java.beans.Transient;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import acme.entities.messages.Message;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Forum extends DomainEntity {

	private static final long			serialVersionUID	= 1L;

	@NotBlank
	private String						title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date						moment;

	@Valid
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Collection<Message>			messages;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<Authenticated>	users;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated				creator;


	@Transient
	public String getCreatorUserName() {
		return this.creator.getUserAccount().getUsername();
	}

}
