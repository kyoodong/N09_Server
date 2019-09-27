package kr.co.m_n09.vos;

public class Token {

    private String deviceId;
    private String token;


    public Token() {

    }

    public Token(String deviceId, String token) {
        this.deviceId = deviceId;
        this.token = token;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
