package net.itskev.springnativecounter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class Runner {

  private static final Random random = new Random();

  public Runner() {
    while (true) {
      String url = "http://spring-native-counter-backend.default.svc.cluster.local:8080/increase/" + random.nextInt(1, 10);

      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest
          .newBuilder(URI.create(url))
          .POST(HttpRequest.BodyPublishers.ofString(""))
          .build();

      try {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
          System.out.println(response.body());
        } else {
          System.out.println("Request failed!");
        }
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }

      try {
        Thread.sleep(random.nextInt(1000, 5000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
