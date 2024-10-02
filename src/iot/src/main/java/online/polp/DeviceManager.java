package online.polp;

import online.polp.utils.RandomUtils;

import java.net.Inet4Address;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class DeviceManager {
    List<Device> devices = new ArrayList<>();

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void removeByIp(Inet4Address ip) {
        devices.removeIf(device -> device.getIpAddress().equals(ip));
    }

    public Device getByIp(Inet4Address ip) {
        return devices
                .stream()
                .filter(device -> device.getIpAddress().equals(ip))
                .findFirst()
                .orElse(null);
    }


    /**
     * Returns a list of devices that are in the area defined by the top left and bottom right corners.
     *
     * @param topLeft     The top left corner of the area
     * @param bottomRight The bottom right corner of the area
     * @return A list of devices that are in the area defined by the top left and bottom right corners
     */
    public List<Device> getDevicesInArea(Location topLeft, Location bottomRight) {
        return devices
                .stream()
                .filter(device -> {
                    Location location = device.getLocation();

                    return location.getLatitude() <= topLeft.getLatitude() &&
                            location.getLatitude() >= bottomRight.getLatitude() &&
                            location.getLongitude() >= topLeft.getLongitude() &&
                            location.getLongitude() <= bottomRight.getLongitude();
                })
                .toList();
    }

    public List<Device> getDevicesWithBatteryOlderThan(Period period) {
        return devices
                .stream()
                .filter(device -> device.getBatteryAge().getDays() > period.getDays())
                .toList();
    }

    public List<Device> getDevices() {
        return devices;
    }

    public Device getRandomDevice() {
        return devices.get(RandomUtils.random.nextInt(devices.size()));
    }
}
