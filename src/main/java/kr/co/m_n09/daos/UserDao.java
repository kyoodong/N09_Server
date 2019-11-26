package kr.co.m_n09.daos;

import kr.co.m_n09.vos.Token;
import kr.co.m_n09.vos.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    void createUser(User user);

    User selectUserByIdPw(
            @Param("id") String id,
            @Param("password") String password,
            @Param("serviceId") int serviceId);

    String selectSaltByUserId(@Param("id") String id, @Param("serviceId") int serviceId);

    List<User> loadAllUsersInService(int serviceId);

    void deleteUserById(String id);
}
