
public class EhcacheDto {

	int jobNo;

	int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public EhcacheDto(int jobNo, int status) {
		this.jobNo = jobNo;
		this.status = status;

	}

	public int getJobNo() {
		return jobNo;
	}

	public void setJobNo(int jobNo) {
		this.jobNo = jobNo;
	}

}
