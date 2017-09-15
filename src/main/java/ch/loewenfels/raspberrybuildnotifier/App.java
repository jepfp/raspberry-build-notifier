package ch.loewenfels.raspberrybuildnotifier;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

import ch.loewenfels.raspberrybuildnotifier.serverpoller.GoogleAppEnginePoller;
import ch.loewenfels.raspberrybuildnotifier.serverpoller.Poller;

import humannotifier.AudioNotifier;
import humannotifier.ConsoleNotifier;

class EchoTask extends TimerTask {
    private final Poller poller;

    public EchoTask() {
        poller = new GoogleAppEnginePoller();
        poller.addObserver(new ConsoleNotifier());
        poller.addObserver(new AudioNotifier());
        System.out.println("Amount of observers: " + poller.countObservers());
    }

    @Override
    public void run() {
        System.out.println(new Date() + " running ...");
        poller.pollAndNotify();
    }
}

public class App implements Daemon {
    private static Timer timer = null;

    public static void main(final String[] args) {
        timer = new Timer();
        timer.schedule(new EchoTask(), 0, 1000 * 60 * 5);
    }

    @Override
    public void init(final DaemonContext context) throws DaemonInitException, Exception {
        System.out.println("init...");
    }

    @Override
    public void start() throws Exception {
        System.out.println("starting ...");
        main(null);
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stopping ...");
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void destroy() {
        System.out.println("done.");
    }
}
