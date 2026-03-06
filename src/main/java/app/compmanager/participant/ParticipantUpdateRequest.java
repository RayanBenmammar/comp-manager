package app.compmanager.participant;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;

public class ParticipantUpdateRequest {

	public String firstName;

	public String lastName;

	@Email(message = "Participant email must be valid")
	public String email;

	public ParticipantGender gender;

	public Long competitionId;

	public Long divisionId;

	public ParticipantStatus status;

	public LocalDate registrationDate;

}
