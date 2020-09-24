package automation.deviceHandler.impl;

import automation.httpClient.HttpClient;
import automation.pojos.Device;
import automation.pojos.ValueTimeStamp;
import automation.deviceHandler.DeviceHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GenericDeviceHandler implements DeviceHandler {


    public final String devices_status = "https://api.smartthings.com/v1/devices/%s/status";

    final HttpClient client = new HttpClient();

    final ObjectMapper mapper = new ObjectMapper();

    protected final Map<String,String> deviceStatusMap = new HashMap<>();

    @Override
    public String response(final Device device) throws IOException {
        final String deviceStatus = String.format(devices_status,device.getDeviceId());
        return client.response(deviceStatus);
    }

    @Override
    public boolean stateChanged(final Device device) throws IOException {
        final String deviceStatus = String.format(devices_status,device.getDeviceId());
        final String statusJsonResponse = client.response(deviceStatus);
        final JsonNode json = mapper.readTree(statusJsonResponse);
        final JsonNode switchNode = json.get("components").get("main").get("switch").get("switch");
        final ValueTimeStamp valueTimeStampValue =  mapper.treeToValue(switchNode, ValueTimeStamp.class);

        if(deviceStatusMap.get(device.getDeviceId()) == null) {
            deviceStatusMap.put(device.getDeviceId(), valueTimeStampValue.getValue());
            return true;
        }

        if(checkStateChange(valueTimeStampValue,device,deviceStatusMap)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deviceSupported() {
        return true;
    }

    protected boolean checkStateChange(final ValueTimeStamp valueTimeStampValue, final Device device , final Map<String,String> stateMap) {
        if (!valueTimeStampValue.getValue().equals(stateMap.get(device.getDeviceId()))) {
            System.out.println("ValueTimeStamp state change for "+device.getLabel() +
                " old state : " +stateMap.get(device.getDeviceId()) +
                " new state : "+ valueTimeStampValue.getValue());
            stateMap.put(device.getDeviceId(), valueTimeStampValue.getValue());
            return true;
        }
        return false;
    }
}
