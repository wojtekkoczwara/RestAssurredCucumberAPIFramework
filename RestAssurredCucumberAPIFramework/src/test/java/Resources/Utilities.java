package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utilities {

    public static RequestSpecification request;

    public RequestSpecification requestSpecification() throws IOException {

        if(request == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

            request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).
                    addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).
                    addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.
                    logResponseTo(log)).build();
            return request;
        }
        return request;
    }

    public static String getGlobalValue(String key) throws IOException {

        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src//test//java//Resources//global.properties");
        prop.load(fileInputStream);
        return prop.getProperty(key);
    }

    public String getJsonPath (Response response, String key){

        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }

}
