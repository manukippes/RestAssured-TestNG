package ar.com.manutesting.utiles.soporte;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;

public class MetodosBasicos {
	
	protected Logger log = LoggerFactory.getLogger(MetodosBasicos.class);

	public void verificarTextosIguales(String texto, String textoEsperdo) {
		Assert.assertTrue("El texto mostrado "+texto+" no es igual al texto esperado "+textoEsperdo, texto.equals(textoEsperdo));
		log.info("Se validó que "+texto+" es igual a "+textoEsperdo);
	}
	
	public String ejecutarServicioGetQueryParam(String texto) {
		log.info("Ejecutando método GET con query param "+texto);
		baseURI = "https://api.openbrewerydb.org/breweries";
		String respuestaServicio = 	given()
									.queryParam("query", texto)
									.log().uri()
									.filter(new AllureRestAssured())
								.when()
									.get("/autocomplete")
								.then()
									.time(lessThan(10000L))
									.log().all()
									.assertThat()
									.statusCode(200)
									.contentType(ContentType.JSON)
									.statusLine(containsString("OK"))
									.extract().body().asString();
		return respuestaServicio;
	}
	
	public String ejecutarServicioGet(String idCerveceria) {
		log.info("Ejecutando método GET");
		baseURI = "https://api.openbrewerydb.org/breweries";
		String respuestaServicio = 	given()
				.log().uri()
				.filter(new AllureRestAssured())
			.when()
				.get("/"+idCerveceria)
			.then()
				.time(lessThan(10000L))
				.log().all()
				.assertThat()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.statusLine(containsString("OK"))
				.extract().body().asString();
		return respuestaServicio;
	}
	
}
