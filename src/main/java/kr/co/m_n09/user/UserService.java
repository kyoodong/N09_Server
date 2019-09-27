package kr.co.m_n09.user;

import kr.co.m_n09.daos.ServiceDao;
import kr.co.m_n09.daos.UserDao;
import kr.co.m_n09.dtos.ServiceDto;
import kr.co.m_n09.vos.Service;
import kr.co.m_n09.vos.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class UserService implements UserRepository {

    @Autowired
    private UserDao userDao;

    public void registerToken(Token token) {
        userDao.createToken(token);
    }
}
