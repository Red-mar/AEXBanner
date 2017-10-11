package sample.Classes;

import javafx.application.Platform;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;

public class BannerController {

    private AEXBanner banner;
    private Timer pollingTimer;

    private static final String bindingName = "MockEffectenbeurs";
    private Registry registry = null;
    private IEffectenbeurs effectenbeurs = null;

    private String ipAddress = "localhost";
    private int portNumber = 1099;

    public BannerController(AEXBanner banner) {

        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }

        if (registry != null) {
            System.out.println("Client: Registry located");
        } else {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }

        if (registry != null) {
            try {
                effectenbeurs = (IEffectenbeurs) registry.lookup(bindingName);
            } catch (RemoteException ex) {
                System.out.println("Client: Cannot bind student administration");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                effectenbeurs = null;
            } catch (NotBoundException ex) {
                System.out.println("Client: Cannot bind student administration");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
                effectenbeurs = null;
            }
        }

        if (effectenbeurs != null) {
            System.out.println("Client: Effecten beurs bound");
        } else {
            System.out.println("Client: Effecten beurs is null pointer");
        }

        if (effectenbeurs != null) {
            testEffectenbeurs();
        }

        this.banner = banner;

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        // TODO
        pollingTimer.scheduleAtFixedRate(new GetKoersen(), 0, 2000);
    }

    private void testEffectenbeurs() {
        try {
            System.out.println(effectenbeurs.getKoersen());
        } catch (RemoteException ex){
            ex.printStackTrace();
        }
    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO

    }

    class GetKoersen extends java.util.TimerTask {

        @Override
        public void run() {
            Platform.runLater(() -> {
                String koersen = "";
                try {
                    for(IFonds koers : effectenbeurs.getKoersen()) {
                        koersen += (koers.getNaam() + ": " + koers.getKoers() + ", ");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                banner.setKoersen(koersen);
            });
        }
    }
}

