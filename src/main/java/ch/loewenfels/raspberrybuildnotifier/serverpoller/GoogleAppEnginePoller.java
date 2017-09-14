package ch.loewenfels.raspberrybuildnotifier.serverpoller;


import java.time.LocalDateTime;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class GoogleAppEnginePoller extends Poller{

	protected BuildInformationDto pollLatestBuildState() {
		JsonParser parser = new JsonParser();
		return parser.get();
		
	}
	
	


}
