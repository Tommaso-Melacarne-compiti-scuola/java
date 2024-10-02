package online.polp.utils;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class InetUtils {
    private InetUtils() {
        // This class should not be instantiated
    }

    public static Inet4Address newRandomInet4Address() {
        byte[] ip = new byte[4];
        for (int i = 0; i < 4; i++) {
            ip[i] = (byte) RandomUtils.random.nextInt(256);
        }

        try {
            return (Inet4Address) Inet4Address.getByAddress(ip);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
