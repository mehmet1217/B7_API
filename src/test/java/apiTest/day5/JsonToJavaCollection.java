package apiTest.day5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.responseSpecification;
import static org.testng.Assert.*;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;


public class JsonToJavaCollection {

    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";
    }

    @Test
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and()
                .queryParam("id", 528)
                .when()
                .get("api/profile/userQuery");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

       /**
           * converting json to Java -->  De-Serilization
           * Bunun icin pom.xml icine mvnrepository den Gson diye bir dependency yükledik
           * Bu islemle Json yapisindaki bir elementi Java ya ceviriyoruz
        */
        Map<String, Object> jsonDataMap = response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);

    }
}
