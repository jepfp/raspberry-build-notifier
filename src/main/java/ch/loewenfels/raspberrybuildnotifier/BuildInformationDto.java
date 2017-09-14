package ch.loewenfels.raspberrybuildnotifier;

import java.time.LocalDateTime;

public class BuildInformationDto {
	public enum JobStatus {
		SUCCESS, FAILURE
	}

	public String jobname;
	public JobStatus jobStatus;
	public LocalDateTime resultDateTime;
	
	@Override
	public String toString() {
		return "[jobname: " + jobname + " jobStatus: " + jobStatus + " resultDateTime: " + resultDateTime + " ]";
	}

}
