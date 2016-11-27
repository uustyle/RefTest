package Dto;
import java.util.List;

import CustomAnnotation.CustomAnnotation;
import CustomAnnotation.Order;

public class Hoge {
	@Order(value=2)
    @CustomAnnotation("a")
    private String name;

	@Order(value=1)
    @CustomAnnotation("b")
    private int value;

	@Order(value=3)
    @CustomAnnotation("b")
	private SubHoge subHoge;

	@Order(value=4)
    @CustomAnnotation("b")
	private List<SubHoge> subHogeList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public SubHoge getSubHoge() {
		return subHoge;
	}

	public void setSubHoge(SubHoge subHoge) {
		this.subHoge = subHoge;
	}

	public List<SubHoge> getSubHogeList() {
		return subHogeList;
	}

	public void setSubHogeList(List<SubHoge> subHogeList) {
		this.subHogeList = subHogeList;
	}





}