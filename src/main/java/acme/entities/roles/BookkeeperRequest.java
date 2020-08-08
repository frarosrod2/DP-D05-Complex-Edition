package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class BookkeeperRequest extends UserRole{
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String responsabilityStatement;
	
	private String state;
	
	

}
