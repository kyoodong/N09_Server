package kr.co.m_n09.dtos;

import kr.co.m_n09.vos.Popup;
import kr.co.m_n09.vos.Service;
import kr.co.m_n09.vos.UrlData;

import java.util.List;

public class ServiceDto extends Service {

    private List<UrlData> urlDataList;
    private List<Popup> popupList;

    public ServiceDto() {
    }

    public ServiceDto(int id, String name, List<UrlData> urlDataList) {
        super(id, name);
        this.urlDataList = urlDataList;
    }

    public List<UrlData> getUrlDataList() {
        return urlDataList;
    }

    public void setUrlDataList(List<UrlData> urlDataList) {
        this.urlDataList = urlDataList;
    }

    public List<Popup> getPopupList() {
        return popupList;
    }

    public void setPopupList(List<Popup> popupList) {
        this.popupList = popupList;
    }
}
