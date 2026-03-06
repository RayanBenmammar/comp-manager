package app.compmanager.division;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DivisionCreateRequest {

	@NotBlank(message = "Division name is mandatory")
	public String name;

	@NotNull(message = "Competition id is mandatory")
	public Long competitionId;

}
