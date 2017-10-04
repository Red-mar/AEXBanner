package sample.Classes;

import javafx.application.Platform;

import java.util.Timer;

public class BannerController {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;

    public BannerController(AEXBanner banner) {

        this.banner = banner;
        this.effectenbeurs = new MockEffectenbeurs();

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        // TODO
        pollingTimer.scheduleAtFixedRate(new GetKoersen(), 0, 2000);
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
                for(IFonds koers : effectenbeurs.getKoersen()) {
                    koersen += (koers.getNaam() + ": " + koers.getKoers() + ", ");
                }

                banner.setKoersen(koersen);
            });
        }
    }
}

