package br.edu.utfpr.bowlinggame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    protected Application() {
        //do nothing
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
