package qaops.automacao.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.*;


public class AppTest {

    @BeforeClass
    public static void setup(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    
    @Test 
    public void testeListaMetadosDoUsuario(){
       when().
               get("https://reqres.in/api/users?page=2").
       then().

               statusCode(HttpStatus.SC_OK).
               body("page", is(2)).
               body("data", is(notNullValue()));
    }
    @Test
    public void testeCriaUsuairoComSucesso(){
        given().
                contentType(ContentType.JSON).
                body("{" +
                                "\"email\": \"carol@reque.in\"," +
                                "\"first_name\": \"Carol\"," +
                                "\"last_name\": \"Mesquita\",\"avatar\": \"https://reqres.in/img/faces/7-image.jpg\"}").
        when().
                post("https://reqres.in/api/users").
        then().
                statusCode(HttpStatus.SC_CREATED).
                body("first_name", is("Carol"));

    }
}

