package cn.vcinema.partner;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * ResponseEntity
 */
public class ResponseEntity<T> {
    @JsonProperty("message")
    private String message = null;

    @JsonProperty("content")
    private T content = null;

    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("date")
    private String date = null;
    SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    public ResponseEntity() {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        timestamp = millis + "";

        date = sf1.format(new Date());
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ResponseEntity message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get message
     *
     * @return message
     **/
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseEntity content(T content) {
        this.content = content;
        return this;
    }

    /**
     * Get content
     *
     * @return content
     **/
    public Object getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public ResponseEntity timestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Get timestamp
     *
     * @return timestamp
     **/
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ResponseEntity date(String date) {
        this.date = date;
        return this;
    }

    /**
     * Get date
     *
     * @return date
     **/
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    /**
     * Get statusCode
     *
     * @return statusCode
     **/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseEntity responseEntity = (ResponseEntity) o;
        return Objects.equals(this.message, responseEntity.message) &&
                Objects.equals(this.content, responseEntity.content) &&
                Objects.equals(this.timestamp, responseEntity.timestamp) &&
                Objects.equals(this.date, responseEntity.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, content, timestamp, date);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResponseEntity {\n");

        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    date: ").append(toIndentedString(date)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public ResponseEntity(String message, T content, T extendedContent, String errorCode, String errorInfo) {
        this.message = message;
        this.content = content;
        this.timestamp = System.currentTimeMillis() + "";
        this.date = sf1.format(new Date());
    }
}

