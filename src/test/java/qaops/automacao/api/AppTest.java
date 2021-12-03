package qaops.automacao.api;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.*;


public class AppTest {
    
    @Test 
    public void testeListaMetadosDoUsuario(){
       when().
               get("https://reqres.in/api/users?page=2").
       then().
               log().all().
               statusCode(HttpStatus.SC_OK).
               body("page", is(2)).
               body("data", is(notNullValue()));
    }
    @Test
    public void testeCriaUsuairoComSucesso(){
        given().log().all().
                contentType(ContentType.JSON).
                body("{" +
                                "\"email\": \"carol@reque.in\"," +
                                "\"first_name\": \"Carol\"," +
                                "\"last_name\": \"Mesquita\",\"avatar\": \"https://reqres.in/img/faces/7-image.jpg\"}").
        when().log().all().
                post("https://reqres.in/api/users").
        then().
                log().all().
                statusCode(HttpStatus.SC_CREATED).
                body("first_name", is("Carol"));

    }
}

