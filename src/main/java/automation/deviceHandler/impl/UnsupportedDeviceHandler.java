package automation.deviceHandler.impl;

import automation.deviceHandler.DeviceHandler;
import automation.pojos.Device;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class UnsupportedDeviceHandler implements DeviceHandler {
    @Override
    public String response(Device device) throws IOException {
        return "UNSUPPORTED_DEVICE";
    }

    @Override
    public Map<String, String> getMappedResponse(Device device) throws IOException {
        return Collections.emptyMap();
    }

    @Override
    public boolean deviceSupported() {
        return false;
    }
}
