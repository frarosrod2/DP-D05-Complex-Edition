
package acme.entities.banners;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import acme.datatypes.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner extends DomainEntity {

	//Serialisation identifier ----------------

	private static final long	serialVersionUID	= 1L;

	//Serialisation identifier ----------------

	@NotBlank
	@URL
	private String				picture;

	@NotBlank
	private String				slogan;

	@NotBlank
	@URL
	private String				targetURL;

	@Valid
	private CreditCard			creditCard;

	//Relationships
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Patron				patron;

}
