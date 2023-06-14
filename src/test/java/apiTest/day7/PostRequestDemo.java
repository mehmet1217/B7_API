package apiTest.day7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class PostRequestDemo {

    @BeforeClass
    public void beforeClass(){
        baseURI = "http://eurotech.study";
    }

    @Test
    public void postNewUser() {
        /**Task
         * Given accept type and Content Type JSON
         * And Request body is :
         {
         "email": "string",
         "password": "string",
         "name": "string",
         "google": "string",
         "facebook": "string",
         "github": "string"
         }
         * When user sends POST request to /api/users
         * Then status code : 200
         * And token should be created
         */

        String jsonBody = "{\n" +
                "         \"email\": \"mertens@gmail.com\",\n" +
                "         \"password\": \"Test1234\",\n" +
                "         \"name\": \"Mertens\",\n" +
                "         \"google\": \"Google Mertens\",\n" +
                "         \"facebook\": \"string\",\n" +
                "         \"github\": \"mertensGS\"\n" +
                "         }";
        Response response = given().accept(ContentType.JSON)  // hey api send me body  as JSON format
                .and()
                .contentType(ContentType.JSON) // Hey api I am sending JSON format
                .and()
                .body(jsonBody)
                .when()
                .post("/api/users");
        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser2(){
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("email", "mertens1@gmail.com" );
        requestMap.put("password", "Test1234" );
        requestMap.put("name", "mertens1" );
        requestMap.put("google", "mertens1google" );
        requestMap.put("facebook", "string" );
        requestMap.put("github", "string" );

        Response response = given().accept(ContentType.JSON)  // hey api send me body  as JSON format
                .and()
                .contentType(ContentType.JSON) // Hey api I am sending JSON format
                .and()
                .body(requestMap)
                .when()
                .post("/api/users");
        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser3(){
       NewUserInfo newUserInfo = new NewUserInfo();
       newUserInfo.setEmail("mertens2@gmail.com");
       newUserInfo.setPassword("Test1234");
       newUserInfo.setName("Mertens");
       newUserInfo.setGoogle("string");
       newUserInfo.setFacebook("string");
       newUserInfo.setGithub("string");

        Response response = given().accept(ContentType.JSON)  // hey api send me body  as JSON format
                .and()
                .contentType(ContentType.JSON) // Hey api I am sending JSON format
                .and()
                .body(newUserInfo) // Serilization
                .when()
                .post("/api/users");
        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser4(){
        NewUserInfo newUserInfo = new NewUserInfo("mertens4@gmail.com","Test1234","Mertens4","string","string","string");

        Response response = given().accept(ContentType.JSON)  // hey api send me body  as JSON format
                .and()
                .contentType(ContentType.JSON) // Hey api I am sending JSON format
                .and()
                .body(newUserInfo) // Serilization
                .when()
                .post("/api/users");
        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));

    }
}











