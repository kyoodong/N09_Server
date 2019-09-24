package kr.co.m_n09.daos;

import kr.co.m_n09.dtos.ServiceDto;
import kr.co.m_n09.vos.Service;
import kr.co.m_n09.vos.UrlData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlDataDao {

    List<UrlData> fetchUrlDataListByServiceId();

    void createUrlData(UrlData urlData);

    void modifyUrlData(UrlData urlData);

    int deleteUrlData(int id);

    UrlData fetchUrlDataById(int id);
}
