package kr.co.m_n09.user;

import kr.co.m_n09.daos.UserDao;
import kr.co.m_n09.vos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserRepository {

    @Autowired
    private UserDao userDao;

    @Transactional
    public User signUp(User user) {
        userDao.createUser(user);
        return userDao.selectUserByIdPw(user.getId(), user.getPassword(), user.getServiceId());
    }

    public User getUserByIdPw(String id, String password, int serviceId) {
        return userDao.selectUserByIdPw(id, password, serviceId);
    }
}
