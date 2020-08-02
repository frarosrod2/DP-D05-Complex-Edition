
package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import acme.datatypes.CreditCard;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patron extends UserRole {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				name;

	@Valid
	private CreditCard			creditCard;
}
