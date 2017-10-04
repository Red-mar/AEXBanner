package sample.Classes;

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
        for(IFonds koers : ffectenbeurs.getKoersen()) {
            banner.setKoersen(koers.getNaam() + ": " + koers.getKoers() + ", ");
        }
    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO

    }
}

