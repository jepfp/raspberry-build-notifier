package humannotifier;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class ConsoleNotifier extends HumanNotifier {

	@Override
	protected void notifyHumanBeing(BuildInformationDto dto) {
		System.out.println("Result of last poll: " + dto.toString());
	}

}
