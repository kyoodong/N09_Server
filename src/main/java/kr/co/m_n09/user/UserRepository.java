package kr.co.m_n09.user;

import kr.co.m_n09.vos.User;

public interface UserRepository {

    User signUp(User user);

    User getUserByIdPw(String id, String password, int serviceId);
}
