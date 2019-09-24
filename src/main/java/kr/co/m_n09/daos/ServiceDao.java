package kr.co.m_n09.daos;

import kr.co.m_n09.dtos.ServiceDto;
import kr.co.m_n09.vos.Service;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceDao {

    List<ServiceDto> fetchAllServices();

    void createService(Service service);

    int fetchServiceIdByName(String name);

    void modifyService(Service service);

    int deleteService(int id);

    ServiceDto fetchServiceById(int id);
}
