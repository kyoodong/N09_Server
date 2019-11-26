package kr.co.m_n09.user;

import com.google.api.client.util.Base64;
import kr.co.m_n09.daos.UserDao;
import kr.co.m_n09.vos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.List;

@Service
public class UserService implements UserRepository {

    @Autowired
    private UserDao userDao;

    @Transactional
    public User signUp(User user) throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(user.getPassword().toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        user.setSalt(Base64.encodeBase64String(salt));
        user.setPassword(Base64.encodeBase64String(factory.generateSecret(spec).getEncoded()));
        userDao.createUser(user);
        return userDao.selectUserByIdPw(user.getId(), user.getPassword(), user.getServiceId());
    }

    public User getUserByIdPw(String id, String password, int serviceId) throws Exception {
        String salt = userDao.selectSaltByUserId(id, serviceId);
        if (salt == null)
            return null;

        KeySpec spec = new PBEKeySpec(password.toCharArray(), Base64.decodeBase64(salt), 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        password = Base64.encodeBase64String(factory.generateSecret(spec).getEncoded());
        return userDao.selectUserByIdPw(id, password, serviceId);
    }

    public List<User> loadAllUsersInService(int serviceId) throws Exception {
        return userDao.loadAllUsersInService(serviceId);
    }

    public Boolean dropUser(String userId) throws Exception {
        userDao.deleteUserById(userId);
        return true;
    }
}
