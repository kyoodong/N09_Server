package kr.co.m_n09.user;

import kr.co.m_n09.vos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("services/{serviceId}")
    public User signUp(@RequestBody User user, @PathVariable int serviceId) throws Exception {
        user.setServiceId(serviceId);
        return userService.signUp(user);
    }

    @GetMapping("login")
    public User login(@RequestParam String id, @RequestParam String password, @RequestParam int serviceId) throws Exception {
        return userService.getUserByIdPw(id, password, serviceId);
    }

    @GetMapping("services/{serviceId}")
    public List<User> loadAllUsersInService(@PathVariable int serviceId) throws Exception {
        return userService.loadAllUsersInService(serviceId);
    }

    @DeleteMapping("")
    public Boolean dropUser(@RequestParam String userId) throws Exception {
        return userService.dropUser(userId);
    }
}
