package automation.deviceHandler.impl;

import automation.pojos.Device;
import automation.pojos.ValueTimeStamp;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PowerOutletDeviceHandler extends GenericDeviceHandler {

    final Map<String,String> powerStateMap = new HashMap<>();

    @Override
    public boolean stateChanged(final Device device) throws IOException {

        final ValueTimeStamp valueTimeStamp = powerStateMap(device);
        if(powerStateMap.get(device.getDeviceId()) == null) {
            powerStateMap.put(device.getDeviceId(), valueTimeStamp.getValue());
            return true;
        }

        if(super.stateChanged(device) || checkStateChange(valueTimeStamp,device,powerStateMap)){
            return true;
        }
        return false;
    }

    private ValueTimeStamp powerStateMap(final Device device) throws IOException {
        final String deviceStatus = String.format(devices_status,device.getDeviceId());
        final String statusJsonResponse = client.response(deviceStatus);
        final JsonNode json = mapper.readTree(statusJsonResponse);
        final JsonNode switchNode = json.get("components").get("main").get("powerMeter").get("power");
        return mapper.treeToValue(switchNode, ValueTimeStamp.class);
    }
}
