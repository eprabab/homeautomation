package automation.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Devices {
    private List<Device> items = new ArrayList<>();

    public List<Device> getItems() {
        return items;
    }

    public void setItems(List<Device> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Devices{" +
            "items=" + items +
            '}';
    }
}
