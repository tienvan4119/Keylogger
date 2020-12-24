package Previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;

public class test {
    public static void main(String[] args) throws IOException {
        InetAddress ip_local = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(ip_local);

        byte[] mac = network.getHardwareAddress();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }

        System.out.println(sb);
    }
}
