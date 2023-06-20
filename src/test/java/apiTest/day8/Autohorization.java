package apiTest.day8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Autohorization {
    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";
    }

    @Test
    public void test1() {
        String loginBody = "{\n" +
                "  \"email\": \"mertens11@gmail.com\",\n" +
                "  \"password\": \"Test1234\"\n" +
                "}";
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(loginBody)
                .when()
                .post("/api/auth");

        assertEquals(response.statusCode(), 200);
        String token = response.path("token");
        System.out.println("token = " + token);

    }


    public static String getToken() {
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("email","mertens11@gmail.com");
        tokenMap.put("password","Test1234");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(tokenMap)
                .when()
                .post("/api/auth");

        assertEquals(response.statusCode(), 200);
        String token = response.path("token");

       return  token;
    }

    public static Map<String, Object> getAccessToken(String email, String password){
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("email",email);
        tokenMap.put("password",password);

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(tokenMap)
                .when()
                .post("/api/auth");
        String token = response.path("token");
        Map<String, Object> authorization = new HashMap<>();
        authorization.put("x-auth-token",token);

        return  authorization;
    }




}
