package kr.co.m_n09.service;

import kr.co.m_n09.dtos.ServiceDto;
import kr.co.m_n09.vos.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("")
    public List<ServiceDto> fetchAllServices() {
        return serviceService.fetchAllServices();
    }

    @GetMapping("{id}")
    public ServiceDto fetchServiceById(@PathVariable int id) {
        return serviceService.fetchServiceById(id);
    }

    @GetMapping("id/name/{name}")
    public int fetchServiceIdByName(@PathVariable String name) {
        return serviceService.fetchServiceIdByName(name);
    }

    @PostMapping("")
    public Service createService(@RequestBody Service service) {
        return serviceService.createService(service);
    }

    @PutMapping("{id}")
    public Service modifyService(@PathVariable int id, @RequestBody Service service) {
        service.setId(id);
        return serviceService.modifyService(service);
    }

    @DeleteMapping("{id}")
    public Integer deleteService(@PathVariable int id) {
        return serviceService.deleteService(id);
    }
}
