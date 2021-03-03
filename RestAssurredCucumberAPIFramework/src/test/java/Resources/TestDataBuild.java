package Resources;

import stepDefinitions.AddPlace;
import stepDefinitions.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language, String address){

        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setPhone_number("(+48) 500-123-245");
        addPlace.setWebsite("https://www.wykop.pl");
        addPlace.setName(name);
        List<String> typesList = new ArrayList<>();
        typesList.add("shop");
        typesList.add("park");
        typesList.add("disco");

        Location location = new Location();
        location.setLat(50.00);
        location.setLng(35.3434);
        addPlace.setLocation(location);

        return addPlace;
    }

    public String deletePlacePayload(String placeID){
        return "\n" +
                "\n" +
                "{\n" +
                "   \"place_id\": \"" + placeID + "\"\n" +
                "}\n" +
                "\n";
    }
}
