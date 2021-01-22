package rest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class UsuariosTest {
	
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = "http://localhost:3000";
		
	}
	
	@Test
	public void consultaUsuarioComSucesso() {
		 given()
		 .log().all()
		 .contentType(ContentType.JSON)
		 .queryParam("nome","Fulano da Silva")
		 //.param("email", "fulano@qa.com")
		 .when()
         .get("/usuarios")
         .then()
         .log().body()
         .assertThat()
         .statusCode(200)
		 .content("usuarios[0].'nome'", equalTo("Fulano da Silva"),
				  "usuarios[0].'email'", equalTo("fulano@qa.com"),
				  "usuarios[0].'administrador'", equalTo("true")
				  ).log();
	}
	
	@Test
	public void consultaUsuarioInvalido() {
		 given()
		 .log().all()
		 .contentType(ContentType.JSON)
		 .queryParam("nome","Usuario X")
		 .when()
         .get("/usuarios")
         .then()
         .log().body()
         .assertThat()
         .statusCode(200)
         .content("quantidade", equalTo(0),
        		 "usuarios[0]", equalTo(null)).log();
	}
	
	@Test
	public void consultaEmailNaoCadastrado() {
		 given()
		 .log().all()
		 .contentType(ContentType.JSON)
		 .queryParam("email","xpto@exemplo.com")
		 .when()
         .get("/usuarios")
         .then()
         .log().body()
         .assertThat()
         .statusCode(200)
         .content("quantidade", equalTo(0),
        		 "usuarios[0]", equalTo(null)).log();
	}
	
	@Test
	public void consultaEmailInvalido() {
		 given()
		 .log().all()
		 .contentType(ContentType.JSON)
		 .queryParam("email","exemplo.com")
		 .when()
         .get("/usuarios")
         .then()
         .log().body()
         .assertThat()
         .statusCode(400);
	}
	
	@AfterClass
	public void tearDown() {
		
		//Matar driver e o que for necessario
	}

}
