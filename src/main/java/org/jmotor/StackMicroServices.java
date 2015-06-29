package org.jmotor;

import mousio.etcd4j.EtcdClient;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.UUID;

/**
 * Component:
 * Description:
 * Date: 2015/6/19
 *
 * @author Andy Ai
 */
public class StackMicroServices {
    public static void main(String[] args) throws SocketException, UnknownHostException {
        InetAddress inetAddress = localInet4Address();
        String host = "0.0.0.0";
        if (inetAddress != null) {
            host = inetAddress.getHostAddress();
        }
        final URI uri = UriBuilder.fromUri("http://" + host + "/").port(9998).build();
        final ResourceConfig config = new ResourceConfig();
        config.packages("org.jmotor.restlet");
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                server.shutdown();
            }
        });
        try {
            server.start();
            final String instanceId = UUID.randomUUID().toString();
            final String serviceInstanceKey = "registry/stacks/v1/" + instanceId;
            final EtcdClient etcd = new EtcdClient(UriBuilder.fromUri("http://192.168.59.103/").port(4001).build());
            etcd.put(serviceInstanceKey, "http://" + host + ":9998/").send();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static InetAddress localInet4Address() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                    return inetAddress;
                }
            }
        }
        return null;
    }
}
