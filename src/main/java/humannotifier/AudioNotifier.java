package humannotifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class AudioNotifier extends HumanNotifier {

	@Override
	protected void notifyHumanBeing(BuildInformationDto dto) {
		switch (dto.jobStatus) {
		case FAILURE: {
			final String wavName = "build-failed.wav";
			final ClassLoader classLoader = getClass().getClassLoader();
			final File wavFile = new File(classLoader.getResource(wavName).getFile());
			Process process = null;
			try {
				if (dto.jobStatus.equals(BuildInformationDto.JobStatus.FAILURE)) {
					ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c",
							"aplay " + wavFile.getAbsolutePath());
					processBuilder.start();
				}
			} catch (IOException e) {
				System.err.println("IO Error occurred on exec process");
				e.printStackTrace();
			}

			// try(BufferedWriter writer = new BufferedWriter(new
			// OutputStreamWriter(process.getOutputStream()))) {
			// writer.write("schreibe auf konsole");
			// writer.newLine();
			// } catch (IOException e) {
			// System.err.println("IO error occureed on write to sysin");
			// e.printStackTrace();
			// }
		}
		case SUCCESS: {
			;
		}
		}
	}

}
