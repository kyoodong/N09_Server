package kr.co.m_n09.user;

import com.google.api.client.util.Base64;
import kr.co.m_n09.vos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("services/{serviceId}")
    public User signUp(@RequestBody User user, @PathVariable int serviceId) throws Exception {
        user.setServiceId(serviceId);
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(user.getPassword().toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        user.setSalt(Base64.encodeBase64String(salt));
        user.setPassword(Base64.encodeBase64String(factory.generateSecret(spec).getEncoded()));
        return userService.signUp(user);
    }

    @GetMapping("login")
    public User login(@RequestParam String id, @RequestParam String password, @RequestParam int serviceId) {
        return userService.getUserByIdPw(id, password, serviceId);
    }
}
