package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

public class APITestNo2 {

    @Test
    public void successGetValue() {

        List<Integer> numberData = Arrays.asList(20,5,1);

        for (int i=0;i<numberData.size();i++) {
            Response response = RestAssured.get("https://api.punkapi.com/v2/beers?page=2&per_page="+String.valueOf(numberData.get(i)));

            int statusCode = response.getStatusCode();
            List<String> data = response.jsonPath().get();

            //assert status code
            Assert.assertEquals(statusCode, 200);

            //assert number of data
            Assert.assertEquals(data.size(), numberData.get(i));
        }
    }
}
