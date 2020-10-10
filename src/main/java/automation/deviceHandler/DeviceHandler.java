package automation.deviceHandler;

import automation.pojos.Device;

import java.io.IOException;
import java.util.Map;

public interface DeviceHandler {

    String response(final Device device) throws IOException;

    Map<String,String> getMappedResponse(final Device device) throws IOException;

    boolean deviceSupported();
}
