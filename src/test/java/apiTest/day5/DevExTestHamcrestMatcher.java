package apiTest.day5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;
import static io.restassured.RestAssured.baseURI;

public class DevExTestHamcrestMatcher {
    String devExURL = "http://eurotech.study";
    /**
     Task
     Given accept content type application json
     And query param id 528
     When user sends GET request to /api/profile/userQuery
     Then status code is 200
     */

    @Test
    public void testTask(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and()
                .queryParam("id", 528)
                .when()
                .get(devExURL + "/api/profile/userQuery");
        assertEquals(response.statusCode(),200);
    }

    @BeforeClass
    public void beforeClass(){
        baseURI = "http://eurotech.study";
    }

    @Test
    public void getOneUser(){
        given().accept(ContentType.JSON)
                .queryParam("id",528)
                .when()
                .get("/api/profile/userQuery")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .contentType("application/json; charset=utf-8");
    }

    @Test
    public void getIneUser_HamcrestMatcher(){
        /**
         * {
         *     "id": 528,
         *     "email": "eurotech@gmail.com",
         *     "name": "Teacher",
         *     "company": "Eurotech Study",
         *     "status": "Instructor",
         *     "profileId": 276
         * }
         */
        given().accept(ContentType.JSON).queryParam("id",528)
                .get("/api/profile/userQuery")
                .then().assertThat()
                .statusCode(200)
                .and().assertThat().contentType("application/json; charset=utf-8")
                .and().assertThat()
                .body("id", Matchers.equalTo(528),
                        "email",Matchers.equalTo("eurotech@gmail.com"),
                        "name",Matchers.equalTo("Teacher"),
                        "company",Matchers.equalTo("Eurotech Study"),
                        "status",Matchers.equalTo("Instructor"),
                        "profileId",Matchers.equalTo(276));
    }

    @Test
    public void hamcrest2() {
        /**{
         "id": 25,
         "email": "jrdev@gmail.com",
         "name": "Jr. Dev",
         "company": "google",
         "status": "Junior Developer",
         "profileId": 1
         }*/
        given().accept(ContentType.JSON)
                .queryParam("id", 25)
                .when()
                .log().all()
                .get("/api/profile/userQuery")
                .then()
                .assertThat().statusCode(200)
                .and()
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .and()
                .header("ETag", equalTo("W/\"71-gLRrgzE02ZoB4TdrNnm1Irq0Rhc\""))
                .and()
                .header("Date", notNullValue())
                .and()
                .body("id", equalTo(25),
                        "email", equalTo("jrdev@gmail.com"),
                        "name", equalTo("Jr. Dev"))
                .log().all();


    }

    @Test
    public void hamcrestBody(){
        given().accept(ContentType.JSON).when()
                .get("api/profile")
                .then()
                .assertThat().statusCode(200)
                .and()
                .contentType("application/json; charset=utf-8")
                .and()
                .body("user.email",hasItem("alex@gmail.com"))
                .log().all();
    }

    @Test
    public void hamcrestBodyTest2(){
        /**
         * "Mike Masters","Necip","CraigDavid","sylvester"
         */

        given().accept(ContentType.JSON)
                .when()
                .get("api/profile")
                .then()
                .assertThat().statusCode(200)
                .and()
                .contentType("application/json; charset=utf-8")
                .and()
                .body("user.name",hasItems("Mike Masters","Necip","CraigDavid","sylvester"))
                .log().all();
    }

    @Test
    public void hamcrestBodyTest3(){
        /**
         verify second company is Eurotech
         verify that second skill's fifth skill is API
         verify that 373rd website is www.eurotech.study
         verify that 373rd email is  instructorihsan@eurotech.com
         verify that 373rd experience's 1st title is  PATCH-SDET
         verify that 373rd experience's 2nd description is  Do it yourself
         verify that 373rd experience's 3rd title is  SDET
         */

        given().accept(ContentType.JSON)
                .when()
                .log().all()
                .get("api/profile")
                .then()
                .assertThat().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .and()
                .body("company[1]",equalTo("Eurotech"),"skills[1][4]",equalTo("API"))
                .and()
                .body("website[372]",equalTo("www.eurotech.study"),
                        "user[372].email",equalTo("instructorihsan@eurotech.com"),
                        "experience[372].title[0]",equalTo("PATCH-SDET"),
                        "experience[372].description[1]",equalTo("Do it yourself"),
                        "experience[372].title[2]",equalTo("SDET"));


    }
}
