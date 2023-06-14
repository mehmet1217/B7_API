package apiTest.day6;

import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.security.PublicKey;
import java.util.Map;

public class gsonExample {

    @Test
    public void JsonToMap(){
        Gson gson=new Gson();

        String jsonBody="{\n" +
                "    \"id\": 256,\n" +
                "    \"email\": \"Sdet_BLG_2@gmail.com\",\n" +
                "    \"name\": \"BLG\",\n" +
                "    \"company\": \"Amazon\",\n" +
                "    \"status\": \"Developer\",\n" +
                "    \"profileId\": 178\n" +
                "}";

        System.out.println("jsonBody = " + jsonBody);

        //De- serialization

        //convert Json body to Map
        Map<String,Object> dataMap=gson.fromJson(jsonBody, Map.class);
        System.out.println("dataMap = " + dataMap);

        //convert json to object class
        EurotechUser eurotechUser=gson.fromJson(jsonBody, EurotechUser.class);
        System.out.println("eurotechUser.getCompany() = " + eurotechUser.getCompany());
        System.out.println("eurotechUser.getName() = " + eurotechUser.getName());

        //Serialization
        EurotechUser eurotechUser1=new EurotechUser(11,"pojo123@gmail.com","Jake","Google","EYT",111);

        String jsonUser=gson.toJson(eurotechUser1);
        System.out.println("jsonUser = " + jsonUser);

        /**De-serialization
         dataMap = {id=256.0, email=Sdet_BLG_2@gmail.com, name=BLG, company=Amazon, status=Developer, profileId=178.0}
         eurotechUser.getCompany() = Amazon
         eurotechUser.getName() = BLG
         * Serialization
         jsonUser = {"id":11.0,"email":"pojo123@gmail.com","name":"Jake","company":"Google","status":"EYT","profileId":111.0}
         */



    }
}
