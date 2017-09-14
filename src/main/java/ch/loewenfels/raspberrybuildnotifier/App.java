package ch.loewenfels.raspberrybuildnotifier;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.daemon.*;

class EchoTask extends TimerTask {
    @Override
    public void run() {
        System.out.println(new Date() + " running ...");
    }
}

public class App implements Daemon {

    private static Timer timer = null;

    public static void main(String[] args) {
        timer = new Timer();
        timer.schedule(new EchoTask(), 0, 1000);
    }

    @Override
    public void init(DaemonContext context)
            throws DaemonInitException, Exception{
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
