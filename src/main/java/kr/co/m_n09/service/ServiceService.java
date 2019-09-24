package kr.co.m_n09.service;

import kr.co.m_n09.daos.ServiceDao;
import kr.co.m_n09.dtos.ServiceDto;
import kr.co.m_n09.vos.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService implements ServiceRepository {

    @Autowired
    private ServiceDao serviceDao;

    public List<ServiceDto> fetchAllServices() {
        return serviceDao.fetchAllServices();
    }

    public Service createService(Service service) {
        serviceDao.createService(service);
        return serviceDao.fetchServiceById(service.getId());
    }

    @Transactional
    public Service modifyService(Service service) {
        serviceDao.modifyService(service);
        return serviceDao.fetchServiceById(service.getId());
    }

    public int deleteService(int id) {
        return serviceDao.deleteService(id);
    }
}
