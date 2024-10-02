package online.polp;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DeviceManager deviceManager = new DeviceManager();

        // Add 10 random devices to the device manager
        for (int i = 0; i < 10; i++) {
            Device device = Device.newNamedRandomDevice("Device " + i);
            deviceManager.addDevice(device);
        }

        // Delete a random sensor by IP address
        Device randomDevice1 = deviceManager.getRandomDevice();
        deviceManager.removeByIp(randomDevice1.getIpAddress());

        // Find a device by IP address
        Device randomDevice2 = deviceManager.getRandomDevice();
        Device foundDevice = deviceManager.getByIp(randomDevice2.getIpAddress());
        System.out.println("Found device: " + foundDevice);

        // Get all devices in a specific area
        Location topLeft = new Location(50, 50);
        Location bottomRight = new Location(40, 60);
        List<Device> devicesInArea = deviceManager.getDevicesInArea(topLeft, bottomRight);
        System.out.println("Devices in area: " + devicesInArea);


        // Get all devices with a battery older than 1 year
        Period period = Period.ofYears(1);
        List<Device> devicesWithOldBattery = deviceManager.getDevicesWithBatteryOlderThan(period);
        System.out.println("Devices with battery older than " + period.getYears() + " years: " + devicesWithOldBattery);


        LocalDate a = LocalDate.of(2021, 1, 1);
        LocalDate b = LocalDate.of(2031, 1, 1);
        Period period2 = Period.between(a, b);
        System.out.println(a.until(b).getYears());
    }
}