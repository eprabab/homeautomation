package automation.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValueTimeStamp {

    @Override
    public String toString() {
        return "ValueTimeStamp{" +
            "value='" + value + '\'' +
            ", timestamp='" + timestamp + '\'' +
            '}';
    }

    private String value;

    private String timestamp;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
