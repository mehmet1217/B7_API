package apiTest.day5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;


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

    @Test
    public void test2() {
        Response response = get(baseURI + "/api/profile");
        assertEquals(response.statusCode(), 200);

        List<Map<String, Object>> allBody = response.body().as(List.class);
        //System.out.println("allBody = " + allBody);

        // print and verify is Third company = "Siemens"
        String expectedCompany = "Siemens";
        String actuallCompany = (String) allBody.get(2).get("company");
        /**{
         "id": 4,
         "company": "Siemens",
         "website": null,
         "year": 0,
         "location": "Ankara",
         "status": "Intern",
         "skills": [
         "Java",
         "Selenium",
         "TestNG",
         "Cucumber",
         "API"
         ],
         "bio": null,
         "githubusername": null,
         "social": {},
         "date": "2022-06-28T18:30:18.996Z",
         "userId": 17,
         "education": [],
         "experience": [],
         "user": {
         "id": 17,
         "name": "GOKHAN YILDIRIM",
         "email": "gokhanyildirim@gmail.com",
         "password": "$2a$10$KpOyVEezG6Eo.Mk2qhAJAO1808CtUQBKo68HO/koJNngPnv6sTn2a",
         "avatar": "//www.gravatar.com/avatar/b8eb1d988a52c2ab45e0548e32e9ca86?s=200&r=pg&d=mm",
         "date": "2022-06-28T18:29:44.970Z",
         "google": null,
         "github": null,
         "facebook": null,
         "profileId": 4
         }
         },*/

        System.out.println("actuallCompany = " + actuallCompany);
        assertEquals(actuallCompany, expectedCompany);

        Map<String, Object> thirdUserInfo= allBody.get(2); // 3 kullanici ile ilgili body ye ulastik
        System.out.println("thirdUserInfo = " + thirdUserInfo);

        Map<String, Object> userInfo = (Map<String, Object>) thirdUserInfo.get("user"); // body icindeki user ile ilgili bilgiler
        System.out.println("userInfo = " + userInfo);

        Object name = userInfo.get("name");
        System.out.println("name = " + name);
        Object email = userInfo.get("email");
        System.out.println("email = " + email);

        List<String> skills = (List<String>) thirdUserInfo.get("skills"); // 3. body icindeki skills list ine ulastik
        System.out.println("skills = " + skills);

        // kendi kullanici hesabini bul
        // verify id, name, mail, skills

    }

    @Test
    public void task(){
        /** class Task
         *  GET request /api/profile
         *  //verify 6. users
         *  company : Amazon
         *  Email :sdet_blg@gmail.com
         */

        Response response = get(baseURI+"/api/profile");
        List<Map<String,Object>> allBody = response.body().as(List.class);
        Map<String,Object> sixthUser = allBody.get(5);
        System.out.println("sixthUser = " + sixthUser);

        String actuallCompay = (String) sixthUser.get("company");
        System.out.println("actuallCompay = " + actuallCompay);

        assertEquals(actuallCompay,"Amazon");


        //Map<String,Object> blgInfo = (Map<String, Object>) sixthUser.get("user");
        Map<String,Object> blgInfo = (Map<String, Object>) allBody.get(5).get("user");
        System.out.println("blgInfo = " + blgInfo);
        String actuallEmail = (String) blgInfo.get("email");
        System.out.println("actuallEmail = " + actuallEmail);
        assertEquals(actuallEmail,"sdet_blg@gmail.com");


    }
}
