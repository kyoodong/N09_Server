package kr.co.m_n09.vos;

public class UrlData {

    private int id;
    private String name;
    private String url;
    private int serviceId;


    public UrlData() {}

    public UrlData(int id, String name, String url, int serviceId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.serviceId = serviceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
