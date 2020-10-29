package automation.metrics;

import com.google.common.util.concurrent.AtomicDouble;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApplicationMetrics {
    private MeterRegistry meterRegistry;

    final Map<String, AtomicDouble> deviceMetrics = new HashMap<>();

    public ApplicationMetrics(final MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void processMetrics(final String name,final Float value){
        if(!deviceMetrics.containsKey(name)) {
            AtomicDouble atomicDouble = new AtomicDouble(value);
            deviceMetrics.put(name,atomicDouble);
            meterRegistry.gauge(name,atomicDouble);
        }
        deviceMetrics.get(name).set(value);
    }
}
