package stepDefinitions;

import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDefinition extends Utilities {

    RequestSpecification requestWithBody;

    ResponseSpecification responseSpecification;

    Response response;

    TestDataBuild data = new TestDataBuild();

    JsonPath js;

    static String placeId;

    @Given("Add Place Payload with {string} {string} {string}")
    public void addPlacePayload(String name, String language, String address) throws IOException {

        requestWithBody = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));

    }

    @When("User calls {string} with {string} http request")
    public void userCallsWithHttpRequest(String resource, String httpMethod) {

        //post place from serialized JSON with response and request specification
        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());

        responseSpecification = responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).
                expectContentType(ContentType.JSON).build();

        if(httpMethod.equalsIgnoreCase("POST")) {
            response = requestWithBody.when().post(resourceAPI.getResource());
        }
        else if (httpMethod.equalsIgnoreCase("GET")){
            response = requestWithBody.when().get(resourceAPI.getResource());
        }
    }

    @Then("The API call is success with status code {int}")
    public void theAPICallIsSuccessWithStatusCode(int arg0) {

        Assert.assertEquals(response.getStatusCode(),200);

    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String keyValue, String expectedvalue) {

        Assert.assertEquals(getJsonPath(response, keyValue), expectedvalue);
    }

    @And("verify place_Id created maps to {string} using {string}")
    public void verifyPlace_IdCreatedMapsToUsing(String name, String resource) throws IOException {

        placeId = getJsonPath(response, "place_id");
        requestWithBody = given().spec(requestSpecification()).queryParam("place_id", placeId);
        userCallsWithHttpRequest(resource, "GET");
        String nameFromJson = getJsonPath(response, "name");
        Assert.assertEquals(name, nameFromJson);
    }


    @Given("DeletePlace payload")
    public void deleteplacePayload() throws IOException {

        requestWithBody = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));

    }
}
