package automation.deviceHandler.impl;

import automation.pojos.Device;
import automation.pojos.ValueTimeStamp;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class PowerOutletDeviceHandler extends GenericDeviceHandler {

    @Override
    protected ValueTimeStamp getPowerValueTimeStamp(final Device device, final ValueTimeStamp switchValueTimeStamp) throws IOException {
        final String deviceStatus = String.format(devices_status,device.getDeviceId());
        final String statusJsonResponse = client.response(deviceStatus);
        final JsonNode json = mapper.readTree(statusJsonResponse);
        final JsonNode switchNode = json.get("components").get("main").get("powerMeter").get("power");
        return mapper.treeToValue(switchNode, ValueTimeStamp.class);
    }

    @Override
    public boolean deviceSupported() {
        return true;
    }
}
