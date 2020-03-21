package org.jgrosshardt.rest.client;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class RestAPIClient {
    private static final String ServerURL = "https://localhost";
    private static final int port = 8080;

    private Client client;
    private Response response;

    private String URL;

    public RestAPIClient() {
        URL = ServerURL + ":" + port + "/";

        client = ClientBuilder.newClient();
    }

    public void close() {
        client.close();
    }

    public void getRequest(String path) {
        response = client.target(URL + path).request().get();

    }

    public void testArtistDeserialization() {
        response = client.target(baseUrl + "jsonString").request().get();
        this.assertResponse(baseUrl + "jsonString", response);

        Jsonb jsonb = JsonbBuilder.create();

        String expectedString = "{\"name\":\"foo\",\"albums\":"
                + "[{\"title\":\"album_one\",\"artist\":\"foo\",\"ntracks\":12}]}";
        Artist expected = jsonb.fromJson(expectedString, Artist.class);

        String actualString = response.readEntity(String.class);
        Artist[] actual = jsonb.fromJson(actualString, Artist[].class);

        assertEquals(expected.name, actual[0].name,
                "Expected names of artists does not match");

        response.close();
    }

    public void testJsonBAlbumCount() {
        String[] artists = {"dj", "bar", "foo"};
        for (int i = 0; i < artists.length; i++) {
            response = client.target(targetUrl + artists[i]).request().get();
            this.assertResponse(targetUrl + artists[i], response);

            int expected = i;
            int actual = response.readEntity(int.class);
            assertEquals(expected, actual, "Album count for " + artists[i] + " does not match");

            response.close();
        }
    }

    public void testJsonBAlbumCountForUnknownArtist() {
        response = client.target(targetUrl + "unknown-artist").request().get();

        int expected = -1;
        int actual = response.readEntity(int.class);
        assertEquals(expected, actual, "Unknown artist must have -1 albums");

        response.close();
    }

    public void testJsonPArtistCount() {
        response = client.target(targetUrl).request().get();
        this.assertResponse(targetUrl, response);

        int expected = 3;
        int actual = response.readEntity(int.class);
        assertEquals(expected, actual, "Expected number of artists does not match");

        response.close();
    }
}
