package testCase;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

import java.io.InputStream;
import java.util.List;


public class APITestNo3 {

    @Test
    public void successValidateJSONSchema(){
        InputStream jsonSchema = getClass().getClassLoader().getResourceAsStream("Json/JsonSchema.json");

        Response response = RestAssured.get("https://api.punkapi.com/v2/beers");
        List<String> data = response.jsonPath().get();

        int statusCode = response.getStatusCode();

        //assert status code
        Assert.assertEquals(statusCode, 200);

        //assert json schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));

        //assert number of data
        Assert.assertEquals(data.size(), 25);

        //print name
        System.out.println(response.jsonPath().get("name").toString());
    }

}
