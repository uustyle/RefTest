package Dto;
import CustomAnnotation.CustomAnnotation;
import CustomAnnotation.Order;

public class SubHoge {
	@Order(value=2)
    @CustomAnnotation("a")
    private String subname;

	@Order(value=1)
    @CustomAnnotation("b")
    private int subvalue;

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public int getSubvalue() {
		return subvalue;
	}

	public void setSubvalue(int subvalue) {
		this.subvalue = subvalue;
	}




}