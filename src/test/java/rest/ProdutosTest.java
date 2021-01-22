package rest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class ProdutosTest {
	
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = "http://localhost:3000";
		
	}
	
	@Test
	public void consultaTodosProdutos() {
		 given()
		 .log().all()
		 .contentType(ContentType.JSON)
		 //.queryParam("nome","Fulano da Silva")
		 .when()
         .get("/produtos")
         .then()
         .log().body()
         .assertThat()
         .statusCode(200)
		 .content("quantidade", equalTo(2)).log();
	}
	
	@Test
	public void consultaProdutoPorDescricao() {
		 given()
		 .log().all()
		 .contentType(ContentType.JSON)
		 .queryParam("descricao","TV")
		 .when()
         .get("/produtos")
         .then()
         .log().body()
         .assertThat()
         .statusCode(200)
         .content("quantidade", equalTo(1),
        		 "produtos[0].'descricao'", equalTo("TV")).log();
	}
	
	@Test
	public void consultaProdutoNaoCadastrado() {
		 given()
		 .log().all()
		 .contentType(ContentType.JSON)
		 .queryParam("descricao","Video Game")
		 .when()
         .get("/produtos")
         .then()
         .log().body()
         .assertThat()
         .statusCode(200)
         .content("quantidade", equalTo(0),
        		 "produtos[0]", equalTo(null)).log();
	}
	
	@AfterClass
	public void tearDown() {
		
	}

}
