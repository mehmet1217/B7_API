package apiTest.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class DevExTestWithParameters {
     String devExURL = "http://eurotech.study";
     String petStoreURL = "https://petstore.swagger.io/v2";

/**    @BeforeClass
    public void beforeClass(){
        baseURI = "http://eurotech.study";
    }
    @Test
    public void test1(){
        Response response = get(baseURI + "/api/profile"); // basina given() da yazilabilir
        response.prettyPrint();
    }
*/
    @Test
    public void petParamPetStore1(){
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(petStoreURL + "/pet/13");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);

    }

    @Test
    public void pathParamPetStore2(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("petID", 13)
                .when()
                .get(petStoreURL + "/pet/{petID}");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);
    }

    @Test
    public void pathParam(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("userID", 213)
                .when()
                .get(devExURL + "/api/profile/user/{userID}");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);
        assertTrue(response.body().asString().contains("sylvester"));

    }



    @Test
    public void queryParamWithMap(){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("status","pending");
        Response response = given().accept(ContentType.JSON).and().queryParams(queryMap).when().get(petStoreURL + "/pet/findByStatus");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);
    }

    @Test
    public void queryParamDevEx(){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("id","213");
        queryMap.put("name","sylvester");
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams(queryMap)
                .when()
                .get(devExURL + "/api/profile/userQuery");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);
    }



}
