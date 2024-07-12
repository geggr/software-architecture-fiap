package br.com.fiap.totem_express.presentation.testing;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;


@Controller
public class HelloController {

    @Operation(
        summary = "Te dá oi",
        description = "Te da oi ou oi estranho dependendo do parametro"
    )
    @GetMapping("/api/hello-world")
    public ResponseEntity<Greeting> getMethodName(@RequestParam Optional<String> name) {
        return ResponseEntity.of(
                name.map(Greeting::new)
                .or(() -> Optional.ofNullable(new Greeting("Stranger"))));
    }
    
    /**
     * GreetString greetinging
     */
    public record Greeting(String name) {
        public String getGreeting(){
            return "Hello "+ name + "!";
        }
    }
    
}
