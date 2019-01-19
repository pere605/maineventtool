package pere.maineventtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class MaineventtoolApplication {

  public static void main(String[] args) {
    SpringApplication.run(MaineventtoolApplication.class, args);
  }

}

