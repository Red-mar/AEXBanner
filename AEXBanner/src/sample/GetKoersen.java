package sample;

import javafx.application.Platform;
import sample.Classes.IEffectenbeurs;
import sample.Classes.IFonds;
import sample.src.fontyspublisher.RemotePublisher;

import java.rmi.RemoteException;

public class GetKoersen extends java.util.TimerTask {

    private IEffectenbeurs effectenbeurs;
    private RemotePublisher publisher;

    public GetKoersen(IEffectenbeurs effectenbeurs, RemotePublisher publisher){
        this.effectenbeurs = effectenbeurs;
        this.publisher = publisher;
    }

    @Override
    public void run() {

            String koersen = "";
            try {
                for(IFonds koers : effectenbeurs.getKoersen()) {
                    koersen += (koers.getNaam() + ": " + koers.getKoers() + ", ");
                }

                publisher.inform("koersen", null, koersen);
                System.out.println("Informing listeners.");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

}
