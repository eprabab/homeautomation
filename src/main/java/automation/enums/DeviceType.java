package automation.enums;

import automation.deviceHandler.DeviceHandler;
import automation.deviceHandler.impl.GenericDeviceHandler;
import automation.deviceHandler.impl.PowerOutletDeviceHandler;
import automation.deviceHandler.impl.UnsupportedDeviceHandler;

import java.util.HashMap;
import java.util.Map;

public enum DeviceType {
    SWITCH("d7fae0ce-9276-4418-b41c-743bf2083692"){

        private DeviceHandler handler = new GenericDeviceHandler();
        public DeviceHandler getHandler() {
            return handler;
        }
    },
    POWER_OUTLET("575cca07-1d9f-4b3d-9fda-c663d8d53051"){
        private DeviceHandler handler = new PowerOutletDeviceHandler();
        public DeviceHandler getHandler() {
            return handler;
        }
    },
    MOBILE("8a9d4b1e3bfce38a013bfce42d360015");

    private DeviceHandler handler = new UnsupportedDeviceHandler();

    private static Map<String,DeviceType> type = new HashMap<>();

    private final String id;

    DeviceType(final String id) {
        this.id = id;
    }

    public static DeviceType getType(final String id) {
        if(type.isEmpty()) {
            for(final DeviceType deviceType:DeviceType.values()) {
                type.put(deviceType.id,deviceType);
            }
        }
        return type.get(id)== null ? MOBILE:type.get(id);
    }

    public DeviceHandler getHandler() {
        return handler;
    }
}
