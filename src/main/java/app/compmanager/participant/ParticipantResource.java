package app.compmanager.participant;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/api/participants")
@Produces("application/json")
@Consumes("application/json")
public class ParticipantResource {

	private final ParticipantService service;

	public ParticipantResource(ParticipantService service) {
		this.service = service;
	}

	@GET
	public List<Participant> getAllParticipants() {
		return service.getAllParticipants();
	}

	@GET
	@Path("/{id}")
	public Participant getParticipantById(@PathParam("id") Long id) {
		return service.getParticipantById(id);
	}

	@POST
	public Response createParticipant(@Valid ParticipantCreateRequest request) {
		Participant participant = service.createParticipant(request);
		return Response.created(URI.create("/api/participants/" + participant.id))
				.entity(participant)
				.build();
	}

	@PUT
	@Path("/{id}")
	public Response updateParticipant(@PathParam("id") Long id, @Valid ParticipantUpdateRequest request) {
		Participant participant = service.updateParticipant(id, request);
		return Response.ok(participant).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteParticipant(@PathParam("id") Long id) {
		service.deleteParticipant(id);
		return Response.noContent().build();
	}

}
