package cn.vcinema.partner;


/**
 * 海南联通response实体
 * Created by wangyong on 2018/5/25.
 */
public class HNUnicomRspEntity {
    /**
     * 流水号，与OrderRelationUpdateNotifyReq的流水号相同
     */
    private String recordSequenceID = null;

    /**
     * 结果识别码
     * 0: 成功
     * 1. 失败
     * 当vac通知sp订购关系，但SP发现已经存在该订购关系时，SP返回VAC成功
     */
    private Integer resultCode;

    private String message = null;

    public String getRecordSequenceID() {
        return recordSequenceID;
    }

    public void setRecordSequenceID(String recordSequenceID) {
        this.recordSequenceID = recordSequenceID;
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
