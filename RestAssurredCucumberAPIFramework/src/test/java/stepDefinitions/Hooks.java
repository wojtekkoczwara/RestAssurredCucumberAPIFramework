package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

        //code that run, when place_id is null, and only then
        // code that give me place id

        String name = "Michael";
        StepDefinition stepDefinition = new StepDefinition();

        if(StepDefinition.placeId == null){
            stepDefinition.addPlacePayload(name, "USA", "America");
            stepDefinition.userCallsWithHttpRequest("AddPlaceAPI", "POST");
            stepDefinition.verifyPlace_IdCreatedMapsToUsing(name, "GetPlaceAPI");
        }
    }


}
