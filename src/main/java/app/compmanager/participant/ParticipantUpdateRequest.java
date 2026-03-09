package app.compmanager.participant;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Email;

public class ParticipantUpdateRequest {

	public String firstName;

	public String lastName;

	@Email(message = "Participant email must be valid")
	public String email;

	public String affiliateName;

	public ParticipantGender gender;

	public UUID competitionId;

	public UUID divisionId;

	public ParticipantStatus status;

	public LocalDate registrationDate;

}
