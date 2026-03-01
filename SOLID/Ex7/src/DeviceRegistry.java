// import java.util.*;

// public class DeviceRegistry {
//     private final java.util.List<SmartClassroomDevice> devices = new ArrayList<>();

//     public void add(SmartClassroomDevice d) { devices.add(d); }

//     public SmartClassroomDevice getFirstOfType(String simpleName) {
//         for (SmartClassroomDevice d : devices) {
//             if (d.getClass().getSimpleName().equals(simpleName)) return d;
//         }
//         throw new IllegalStateException("Missing: " + simpleName);
//     }
// }

import java.util.*;
import java.util.stream.Collectors;

public class DeviceRegistry {

    private final List<Object> devices = new ArrayList<>();

    public void add(Object device) {
        devices.add(device);
    }

    public <T> List<T> getAll(Class<T> capability) {
        return devices.stream()
                .filter(capability::isInstance)
                .map(capability::cast)
                .collect(Collectors.toList());
    }
}