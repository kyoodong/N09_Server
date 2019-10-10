package kr.co.m_n09.vos;

public class Popup {

    private int id;
    private String name;
    private String imageUrl;
    private String linkUrl;

    public Popup() {

    }
    public Popup(int id, String name, String imageUrl, String linkUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
