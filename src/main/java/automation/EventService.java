package automation;

import automation.deviceHandler.DeviceHandler;
import automation.enums.DeviceType;
import automation.httpClient.HttpClient;
import automation.pojos.Device;
import automation.pojos.Devices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EventService {

    public final String devices_url = "https://api.smartthings.com/v1/devices";

    @Autowired
    HttpClient client;

    final ObjectMapper mapper = new ObjectMapper();

    @Scheduled(fixedRate = 5000)
    public void poll() throws IOException {
        final Devices devices = mapper.readValue(client.response(devices_url),Devices.class);
        for(final Device device : devices.getItems()) {
            final DeviceHandler handler = DeviceType.getType(device.getDeviceTypeId()).getHandler();
            if(handler.deviceSupported() && handler.stateChanged(device)){
                System.out.println(handler.response(device));
            }
        }
    }
}
