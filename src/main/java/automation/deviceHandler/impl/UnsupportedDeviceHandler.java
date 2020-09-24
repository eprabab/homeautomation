package automation.deviceHandler.impl;

import automation.deviceHandler.DeviceHandler;
import automation.pojos.Device;

import java.io.IOException;

public class UnsupportedDeviceHandler implements DeviceHandler {
    @Override
    public String response(Device device) throws IOException {
        return "UNSUPPORTED_DEVICE";
    }

    @Override
    public boolean stateChanged(Device device) throws IOException {
        return false;
    }

    @Override
    public boolean deviceSupported() {
        return false;
    }
}
