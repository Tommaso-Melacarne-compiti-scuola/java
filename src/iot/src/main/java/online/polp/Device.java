package online.polp;

import online.polp.utils.DateUtils;
import online.polp.utils.InetUtils;

import java.net.Inet4Address;
import java.time.LocalDate;
import java.time.Period;

public class Device {
    private String name;
    private Inet4Address ipAddress;
    private Location location;
    private LocalDate batteryExpiration;
    private LocalDate batteryLastChanged;


    public Device(LocalDate batteryExpiration, String name, Inet4Address ipAddress, Location location, LocalDate batteryLastChanged) {
        this.batteryExpiration = batteryExpiration;
        this.name = name;
        this.ipAddress = ipAddress;
        this.location = location;
        this.batteryLastChanged = batteryLastChanged;
    }

    public static Device newNamedRandomDevice(String name) {
        Inet4Address randomIp = InetUtils.newRandomInet4Address();
        Location randomLocation = Location.newRandomLocation();
        LocalDate now = LocalDate.now();
        LocalDate randomBatteryExpiration = DateUtils.newRandomDateBounded(now, now.plusYears(2));
        LocalDate randomBatteryLastChanged = DateUtils.newRandomDateBounded(now.minusYears(1), now);

        return new Device(randomBatteryExpiration, name, randomIp, randomLocation, randomBatteryLastChanged);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBatteryExpiration() {
        return batteryExpiration;
    }

    public void setBatteryExpiration(LocalDate batteryExpiration) {
        this.batteryExpiration = batteryExpiration;
    }

    public Inet4Address getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(Inet4Address ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getBatteryLastChanged() {
        return batteryLastChanged;
    }

    public void setBatteryLastChanged(LocalDate batteryLastChanged) {
        this.batteryLastChanged = batteryLastChanged;
    }

    public Period getBatteryAge() {
        return batteryLastChanged.until(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Device{" +
                "name='" + name + '\'' +
                ", ipAddress=" + ipAddress +
                ", location=" + location +
                ", batteryExpiration=" + batteryExpiration +
                ", batteryLastChanged=" + batteryLastChanged +
                '}';
    }
}