package app.compmanager.competition;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.nullValue;

@QuarkusTest
class CompetitionResourceTest {

    @Test
    void testCreateAndGetCompetition() {
        // Créer une compétition (multi-jours)
        String body = """
                {
                    "name": "CrossFit Open 2025",
                    "startDate": "2025-06-15",
                    "endDate": "2025-06-17",
                    "timezone": "Europe/Paris",
                    "location": "Box Paris 11",
                    "description": "Compétition locale"
                }
                """;

        Integer id = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/competitions")
                .then()
                .statusCode(201)
                .body("name", is("CrossFit Open 2025"))
                .body("startDate", is("2025-06-15"))
                .body("endDate", is("2025-06-17"))
                .body("timezone", is("Europe/Paris"))
                .body("status", is("DRAFT"))
                .extract().path("id");

        // Récupérer par ID
        given()
                .when().get("/api/competitions/" + id)
                .then()
                .statusCode(200)
                .body("name", is("CrossFit Open 2025"))
                .body("location", is("Box Paris 11"));
    }

    @Test
    void testCreateSingleDayCompetition() {
        // Compétition sur un seul jour (pas d'endDate)
        String body = """
                {
                    "name": "WOD Challenge",
                    "startDate": "2025-09-20",
                    "timezone": "Europe/London"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/competitions")
                .then()
                .statusCode(201)
                .body("name", is("WOD Challenge"))
                .body("startDate", is("2025-09-20"))
                .body("endDate", nullValue())
                .body("timezone", is("Europe/London"));
    }

    @Test
    void testListCompetitions() {
        given()
                .when().get("/api/competitions")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    void testDeleteCompetition() {
        // Créer puis supprimer
        String body = """
                {
                    "name": "À supprimer",
                    "startDate": "2025-07-01",
                    "timezone": "UTC"
                }
                """;

        Integer id = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/competitions")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .when().delete("/api/competitions/" + id)
                .then()
                .statusCode(204);

        // Vérifier que c'est supprimé
        given()
                .when().get("/api/competitions/" + id)
                .then()
                .statusCode(404);
    }

    @Test
    void testGetNotFound() {
        given()
                .when().get("/api/competitions/999999")
                .then()
                .statusCode(404);
    }
}