package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.util.Observable;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public abstract class Poller extends Observable{

	protected abstract BuildInformationDto pollLatestBuildState();
	
	public void pollAndNotify() {
		BuildInformationDto latestBuildState = pollLatestBuildState();
		setChanged();
		notifyObservers(latestBuildState);
	}
}
