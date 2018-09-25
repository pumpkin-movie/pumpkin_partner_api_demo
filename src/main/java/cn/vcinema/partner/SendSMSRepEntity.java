package cn.vcinema.partner;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wangyong on 2018/9/25.
 */
public class SendSMSRepEntity {
    /**
     * 手机号
     */
    @JsonProperty("phone")
    private String phone = null;

    /**
     * 结果识别码
     * 0: 成功
     * 1. 失败
     * 当vac通知sp订购关系，但SP发现已经存在该订购关系时，SP返回VAC成功
     */
    @JsonProperty("ResultCode")
    private Integer resultCode;

    @JsonProperty("message")
    private String message = null;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
