package kr.co.m_n09.urlData;

import kr.co.m_n09.vos.UrlData;

public interface UrlDataRepository {

    UrlData createUrlData(UrlData urlData);

    UrlData modifyUrlData(UrlData urlData);

    int deleteUrlData(int id);
}
