package automation.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Device {

    private String deviceId;

    private String deviceTypeId;

    @Override
    public String toString() {
        return "Device{" +
            "deviceId='" + deviceId + '\'' +
            ", deviceTypeId='" + deviceTypeId + '\'' +
            ", label='" + label + '\'' +
            '}';
    }

    private String label;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }
}
