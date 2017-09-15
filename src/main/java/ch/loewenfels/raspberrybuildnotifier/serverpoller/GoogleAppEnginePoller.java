package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class GoogleAppEnginePoller extends Poller {
    @Override
    protected BuildInformationDto pollLatestBuildState() {
        final JsonParser parser = new JsonParser();
        return parser.get();
    }
}
