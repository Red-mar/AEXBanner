package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import sample.Classes.IFonds;
import sample.Classes.MockEffectenbeurs;
import sample.src.fontyspublisher.RemotePublisher;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;
import java.util.Timer;

public class AEXServer {

    private static final int portNumber = 4321;
    private static final String bindingName = "MockEffectenbeurs";
    private Registry registry = null;
    private MockEffectenbeurs effectenbeurs = null;

    private RemotePublisher publisher;

    public AEXServer(){
        System.out.println("Server: Port number " + portNumber);

        try {
            publisher = new RemotePublisher();
            publisher.registerProperty("koersen");
            registry = LocateRegistry.createRegistry(portNumber);
            registry.rebind(bindingName, publisher);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        try {
            effectenbeurs = new MockEffectenbeurs();
            System.out.println("Server: Effectenbeurs created");
        } catch (RemoteException ex) {
            ex.printStackTrace();
            System.out.println("Server: Cannot create effectenbeurs");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            effectenbeurs = null;
        }

        Timer timer = new Timer();
        timer.schedule(new GetKoersen(effectenbeurs, publisher), 0, 2500);
/*
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }

        try {
            registry.rebind(bindingName, effectenbeurs);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind effectenbeurs");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
        */
    }



    // Print IP addresses and network interfaces
    private static void printIPAddresses() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("Server: IP Address: " + localhost.getHostAddress());
            // Just in case this host has multiple IP addresses....
            InetAddress[] allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null && allMyIps.length > 1) {
                System.out.println("Server: Full list of IP addresses:");
                for (InetAddress allMyIp : allMyIps) {
                    System.out.println("    " + allMyIp);
                }
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server: Cannot get IP address of local host");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }

        try {
            System.out.println("Server: Full list of network interfaces:");
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                System.out.println("    " + intf.getName() + " " + intf.getDisplayName());
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    System.out.println("        " + enumIpAddr.nextElement().toString());
                }
            }
        } catch (SocketException ex) {
            System.out.println("Server: Cannot retrieve network interface list");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }
    }

    public static void main(String[] args){
        System.out.println("Welcome to the server.");

        printIPAddresses();

        AEXServer server = new AEXServer();

    }
}
