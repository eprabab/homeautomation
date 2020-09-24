package automation.deviceHandler;

import automation.pojos.Device;

import java.io.IOException;

public interface DeviceHandler {
    String response(final Device device) throws IOException;

    boolean stateChanged(final Device device) throws IOException;

    boolean deviceSupported();
}
