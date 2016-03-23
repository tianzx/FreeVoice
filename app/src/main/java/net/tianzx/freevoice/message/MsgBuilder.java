package net.tianzx.freevoice.message;

/**
 * Created by tianzx on 2016/3/23.
 */
public class MsgBuilder {
    public static final String ONLINE_REQUEST ="1";
    public static final String ACCEPT_REQUEST ="2";

    private StringBuilder msgBuilder =  new StringBuilder();


    public MsgBuilder(String code){
        msgBuilder.append(code);
    }

    public MsgBuilder setIp(String ip) {
        msgBuilder.append(ip);
        return this;
    }
    public MsgBuilder setSimNum(String simNum) {
        msgBuilder.append(","+simNum);
        return this;
    }
    public MsgBuilder setShowImg(String showImg){
        msgBuilder.append(","+showImg);
    }
    public String buildMsg() {
        return msgBuilder.toString();
    }
}
