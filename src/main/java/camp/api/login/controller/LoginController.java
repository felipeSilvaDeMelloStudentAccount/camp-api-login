package camp.api.login.controller;

import camp.api.login.model.User;
import camp.api.login.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class LoginController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody User user) {
        log.info("login controller");

        if (userService.authenticateUser(user.getEmail(), user.getPassword())) {
            String jwtToken = generateJwtToken(user.getEmail());
            return ResponseEntity.ok(jwtToken);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String generateJwtToken(String username) {
        // Implement JWT token generation logic here
        // You can use libraries like jjwt for this purpose
        return "124341";
    }
}
