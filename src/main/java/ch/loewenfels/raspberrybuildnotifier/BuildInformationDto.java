package ch.loewenfels.raspberrybuildnotifier;

import java.time.LocalDateTime;

public class BuildInformationDto {
	public enum JobStatus {
		SUCCESS, FAILURE
	}

	public String name;
	public JobStatus jobStatus;
	public LocalDateTime resultDateTime;

	public BuildInformationDto(String name, JobStatus jobStatus, LocalDateTime resultDateTime) {
		this.name = name;
		this.jobStatus = jobStatus;
		this.resultDateTime = resultDateTime;
	}

	public BuildInformationDto(String name, String jobStatus, LocalDateTime resultDateTime) {
		this(name, JobStatus.valueOf(jobStatus), resultDateTime);
	}

	public BuildInformationDto() {
	}

	@Override
	public String toString() {
		return "[jobname: " + name + " jobStatus: " + jobStatus + " resultDateTime: " + resultDateTime + " ]";
	}

}
