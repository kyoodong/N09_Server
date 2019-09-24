package kr.co.m_n09.service;

import kr.co.m_n09.dtos.ServiceDto;
import kr.co.m_n09.vos.Service;

import java.util.List;

public interface ServiceRepository {

    List<ServiceDto> fetchAllServices();

    Service createService(Service service);

    Service modifyService(Service service);

    int deleteService(int id);
}
