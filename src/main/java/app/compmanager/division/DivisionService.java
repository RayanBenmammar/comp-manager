package app.compmanager.division;

import java.util.List;

import app.compmanager.competition.Competition;
import app.compmanager.competition.CompetitionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DivisionService {

	@Inject
	CompetitionService competitionService;

	public List<Division> getAllDivisions() {
		return Division.listAll();
	}

	public Division getDivisionById(Long id) {
		Division division = Division.findById(id);
		if (division == null) {
			throw new NotFoundException("Division with id " + id + " not found");
		}
		return division;
	}

	@Transactional
	public Division createDivision(DivisionCreateRequest request) {
		Competition competition = competitionService.getCompetitionById(request.competitionId);

		Division division = new Division();
		division.name = request.name;
		division.competition = competition;
		division.persist();

		return division;
	}

	@Transactional
	public Division updateDivision(Long id, DivisionUpdateRequest request) {
		Division division = getDivisionById(id);

		if (request.name != null) {
			division.name = request.name;
		}
		if (request.competitionId != null) {
			division.competition = competitionService.getCompetitionById(request.competitionId);
		}

		return division;
	}

	@Transactional
	public void deleteDivision(Long id) {
		boolean deleted = Division.deleteById(id);
		if (!deleted) {
			throw new NotFoundException("Division with id " + id + " not found");
		}
	}

}
