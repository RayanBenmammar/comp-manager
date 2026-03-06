package app.compmanager.participant;

import java.util.List;

import app.compmanager.competition.Competition;
import app.compmanager.competition.CompetitionService;
import app.compmanager.division.Division;
import app.compmanager.division.DivisionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ParticipantService {

	@Inject
	CompetitionService competitionService;

	@Inject
	DivisionService divisionService;

	public List<Participant> getAllParticipants() {
		return Participant.listAll();
	}

	public Participant getParticipantById(Long id) {
		Participant participant = Participant.findById(id);
		if (participant == null) {
			throw new NotFoundException("Participant with id " + id + " not found");
		}
		return participant;
	}

	@Transactional
	public Participant createParticipant(ParticipantCreateRequest request) {
		Competition competition = competitionService.getCompetitionById(request.competitionId);
		Division division = divisionService.getDivisionById(request.divisionId);
		validateDivisionBelongsToCompetition(division, competition);

		Participant participant = new Participant();
		participant.firstName = request.firstName;
		participant.lastName = request.lastName;
		participant.email = request.email;
		participant.gender = request.gender;
		participant.competition = competition;
		participant.division = division;
		if (request.status != null) {
			participant.status = request.status;
		}
		if (request.registrationDate != null) {
			participant.registrationDate = request.registrationDate;
		}
		participant.persist();

		return participant;
	}

	@Transactional
	public Participant updateParticipant(Long id, ParticipantUpdateRequest request) {
		Participant participant = getParticipantById(id);

		Competition competition = participant.competition;
		Division division = participant.division;

		if (request.competitionId != null) {
			competition = competitionService.getCompetitionById(request.competitionId);
		}
		if (request.divisionId != null) {
			division = divisionService.getDivisionById(request.divisionId);
		}
		validateDivisionBelongsToCompetition(division, competition);

		participant.competition = competition;
		participant.division = division;

		if (request.firstName != null) {
			participant.firstName = request.firstName;
		}
		if (request.lastName != null) {
			participant.lastName = request.lastName;
		}
		if (request.email != null) {
			participant.email = request.email;
		}
		if (request.gender != null) {
			participant.gender = request.gender;
		}
		if (request.status != null) {
			participant.status = request.status;
		}
		if (request.registrationDate != null) {
			participant.registrationDate = request.registrationDate;
		}

		return participant;
	}

	@Transactional
	public void deleteParticipant(Long id) {
		boolean deleted = Participant.deleteById(id);
		if (!deleted) {
			throw new NotFoundException("Participant with id " + id + " not found");
		}
	}

	private void validateDivisionBelongsToCompetition(Division division, Competition competition) {
		if (!division.competition.id.equals(competition.id)) {
			throw new BadRequestException("Division does not belong to the given competition");
		}
	}

}
