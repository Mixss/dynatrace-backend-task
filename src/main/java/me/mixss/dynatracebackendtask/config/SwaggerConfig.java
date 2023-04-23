package me.mixss.dynatracebackendtask.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPIConfig(){
        Contact contact = new Contact();
        contact.setEmail("michalziemiec@wp.pl");
        contact.setName("Michał Ziemiec");

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Server URL in Local environment");

        Info info = new Info()
                .title("Currency API")
                .contact(contact)
                .version("1.0")
                .description("This REST API fetches data from the Narodowy Bank Polski's public API and returns relevant information from them (http://api.nbp.pl/).")
                ;

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }
}
