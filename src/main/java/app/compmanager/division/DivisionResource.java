package app.compmanager.division;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/api/divisions")
@Produces("application/json")
@Consumes("application/json")
public class DivisionResource {

	private final DivisionService service;

	public DivisionResource(DivisionService service) {
		this.service = service;
	}

	@GET
	public List<Division> getAllDivisions() {
		return service.getAllDivisions();
	}

	@GET
	@Path("/{id}")
	public Division getDivisionById(@PathParam("id") UUID id) {
		return service.getDivisionById(id);
	}

	@POST
	public Response createDivision(@Valid DivisionCreateRequest request) {
		Division division = service.createDivision(request);
		return Response.created(URI.create("/api/divisions/" + division.id))
				.entity(division)
				.build();
	}

	@PUT
	@Path("/{id}")
	public Response updateDivision(@PathParam("id") UUID id, @Valid DivisionUpdateRequest request) {
		Division division = service.updateDivision(id, request);
		return Response.ok(division).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteDivision(@PathParam("id") UUID id) {
		service.deleteDivision(id);
		return Response.noContent().build();
	}

}
