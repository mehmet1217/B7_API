package apiTest.day7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import static org.testng.Assert.*;

public class PostSaveProfile {

    @BeforeClass
    public void beforeClass(){

        baseURI = "http://eurotech.study";
    }

    @Test
    public void postNewUser(){

        /**
         * Create new user
         * Verify with token
         * Save user Profile with using token
         */

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
        String token = response.path("token");


        String profileBody = "{\n" +
                "  \"company\": \"Amazon\",\n" +
                "  \"website\": \"www.amazon.de\",\n" +
                "  \"location\": \"Berlin\",\n" +
                "  \"status\": \"SDET\",\n" +
                "  \"skills\": \"HTML,CSS,Java, Selenium\",\n" +
                "  \"githubusername\": \"string\",\n" +
                "  \"youtube\": \"string\",\n" +
                "  \"twitter\": \"string\",\n" +
                "  \"facebook\": \"string\",\n" +
                "  \"linkedin\": \"string\",\n" +
                "  \"instagram\": \"string\"\n" +
                "}";

        response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("x-auth-token", token)
                .and()
                .body(profileBody)
                .when()
                .post("/api/profile");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

    }

    @Test
    public void postNewUserMap(){

        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("email", "mertens11@gmail.com" );
        requestMap.put("password", "Test1234" );
        requestMap.put("name", "mertens11" );
        requestMap.put("google", "mertens9google" );
        requestMap.put("facebook", "string" );
        requestMap.put("github", "string" );

        Response response = given().accept(ContentType.JSON)  // hey api send me body  as JSON format
                .and()
                .contentType(ContentType.JSON) // Hey api I am sending JSON format
                .and()
                .body(requestMap) // seriliziation
                .when()
                .post("/api/users");
        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        //assertTrue(response.body().asString().contains("token"));

        String token= response.path("token");

        Map<String, Object> profileBody = new HashMap<>();
        profileBody.put("company", "Siemens");
        profileBody.put("website", "www.seimens.com");
        profileBody.put("location", "Berlin");
        profileBody.put("status", "Developer");
        profileBody.put("skills", "Java, TestNG");
        profileBody.put("githubusername", "S");
        profileBody.put("youtube", "S");
        profileBody.put("twitter", "S");
        profileBody.put("facebook", "S");
        profileBody.put("linkedin", "S");
        profileBody.put("instagram", "S");

        response = given().accept(ContentType.JSON)  // hey api send me body  as JSON format
                .and()
                .contentType(ContentType.JSON) // Hey api I am sending JSON format
                .and()
                .header("x-auth-token", token)
                .and()
                .body(profileBody) // seriliziation
                .when()
                .post("/api/profile");
        assertEquals(response.statusCode(),200);
        // verify body
        int id = response.path("user.id");
         response = given().accept(ContentType.JSON)
                .and()
                .queryParam("id", id)
                .when()
                .get("/api/profile/userQuery");
         assertEquals(response.statusCode(),200);

         // verify with path Method
        assertEquals(response.path("name"),"mertens11");
        assertEquals(response.path("company"),"Siemens");

        // Veriy using hamcrest matcher
        given().accept(ContentType.JSON)
                .and()
                .queryParam("id", id)
                .when()
                .get("/api/profile/userQuery")
                .then().assertThat()
                .body("email", Matchers.equalTo("mertens11@gmail.com"),
                        "name",Matchers.equalTo("mertens11"),
                        "company",Matchers.equalTo("Siemens"));




    }
}
