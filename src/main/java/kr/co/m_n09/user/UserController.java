package kr.co.m_n09.user;

import kr.co.m_n09.vos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public User signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    @GetMapping("login")
    public User login(@RequestParam String id, @RequestParam String password) {
        return userService.getUserByIdPw(id, password);
    }
}
