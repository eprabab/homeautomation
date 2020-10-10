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
import java.util.Random;

public class GenericDeviceHandler implements DeviceHandler {


    public final String devices_status = "https://api.smartthings.com/v1/devices/%s/status";

    final HttpClient client = new HttpClient();

    final ObjectMapper mapper = new ObjectMapper();

    protected final Map<String,String> responseMap = new HashMap<>();

    @Override
    public String response(final Device device) throws IOException {
        final String deviceStatus = String.format(devices_status,device.getDeviceId());
        return client.response(deviceStatus);
    }

    @Override
    public Map<String,String> getMappedResponse(final Device device) throws IOException {
        final ValueTimeStamp switchValueTimeStamp = getSwitchValueTimeStamp(device);
        final ValueTimeStamp powerValueTimeStamp = getPowerValueTimeStamp(device,switchValueTimeStamp);
        responseMap.put("DeviceName",device.getLabel());
        responseMap.put("SwitchStatus",switchValueTimeStamp.getValue());
        responseMap.put("Timestamp",switchValueTimeStamp.getTimestamp());
        responseMap.put("powerConsumption",powerValueTimeStamp.getValue());
        return responseMap;
    }

    protected ValueTimeStamp getPowerValueTimeStamp(final Device device, final ValueTimeStamp switchValueTimeStamp) throws IOException {
        boolean isON = switchValueTimeStamp.getValue().equalsIgnoreCase("on");
        final Float f = isON ? new Random().nextFloat()*(7 - 5) + 5 : 0;
        final ValueTimeStamp valueTimeStamp = new ValueTimeStamp();
        valueTimeStamp.setValue(f.toString());
        return valueTimeStamp;
    }

    private ValueTimeStamp getSwitchValueTimeStamp(Device device) throws IOException {
        final String deviceStatus = String.format(devices_status,device.getDeviceId());
        final String statusJsonResponse = client.response(deviceStatus);
        final JsonNode json = mapper.readTree(statusJsonResponse);
        final JsonNode switchNode = json.get("components").get("main").get("switch").get("switch");
        return mapper.treeToValue(switchNode, ValueTimeStamp.class);
    }

    @Override
    public boolean deviceSupported() {
        return true;
    }
}
