package kr.co.m_n09.urlData;

import kr.co.m_n09.daos.UrlDataDao;
import kr.co.m_n09.vos.UrlData;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class UrlDataService implements UrlDataRepository {

    @Autowired
    private UrlDataDao urlDataDao;

    public UrlData createUrlData(UrlData urlData) {
        urlDataDao.createUrlData(urlData);
        return urlDataDao.fetchUrlDataById(urlData.getId());
    }

    public UrlData modifyUrlData(UrlData urlData) {
        urlDataDao.modifyUrlData(urlData);
        return urlDataDao.fetchUrlDataById(urlData.getId());
    }

    public int deleteUrlData(int id) {
        return urlDataDao.deleteUrlData(id);
    }
}
