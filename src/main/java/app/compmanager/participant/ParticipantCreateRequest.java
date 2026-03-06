package app.compmanager.participant;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ParticipantCreateRequest {

	@NotBlank(message = "Participant first name is mandatory")
	public String firstName;

	@NotBlank(message = "Participant last name is mandatory")
	public String lastName;

	@Email(message = "Participant email must be valid")
	public String email;

	public ParticipantGender gender;

	@NotNull(message = "Competition id is mandatory")
	public Long competitionId;

	@NotNull(message = "Division id is mandatory")
	public Long divisionId;

	public ParticipantStatus status;

	public LocalDate registrationDate;

}
