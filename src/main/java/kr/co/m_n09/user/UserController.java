package kr.co.m_n09.user;

import kr.co.m_n09.vos.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity registerToken(@RequestBody Token token) {
        userService.registerToken(token);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
