package org.yukung.perfect_java.chap15.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

public class MyNetworkInterface {
	
	public static void main(String[] args) {
		try {
			List<NetworkInterface> nifs = Collections.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface nif : nifs) {
				System.out.println(nif.getName());
				System.out.println(nif.isUp());
				byte[] macaddr = nif.getHardwareAddress();
				if (macaddr != null) {
					for (byte b : macaddr) {
						System.out.printf("%02x ", b);
					}
				}
				System.out.println();
				List<InetAddress> addrs = Collections.list(nif.getInetAddresses());
				for (InetAddress addr : addrs) {
					System.out.println(addr.getHostAddress());
				}
				System.out.println();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}
