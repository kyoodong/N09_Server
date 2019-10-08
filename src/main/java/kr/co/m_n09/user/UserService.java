package kr.co.m_n09.user;

import kr.co.m_n09.daos.TokenDao;
import kr.co.m_n09.daos.UserDao;
import kr.co.m_n09.vos.Token;
import kr.co.m_n09.vos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserRepository {

    @Autowired
    private UserDao userDao;

    public User signUp(User user) {
        userDao.createUser(user);
        return userDao.selectUserByIdPw(user.getId(), user.getPassword());
    }

    public User getUserByIdPw(String id, String password) {
        return userDao.selectUserByIdPw(id, password);
    }
}
