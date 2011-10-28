package net.jeremybrooks.jinx.dto;

import java.util.List;

/**
 * <predicates page="1" pages="1" total="3" perpage="500">
 * @author emeraldjava
 */
public class Predicates {

	private int page;
	private int total; 
	private int perpage;
	private List<Predicate> predicates;
	
	public List<Predicate> getPredicates() {
		return predicates;
	}
	public void setPredicates(List<Predicate> values) {
		this.predicates = values;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPerpage() {
		return perpage;
	}
	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Predicates[page=");
		builder.append(page);
		builder.append(", total=");
		builder.append(total);
		builder.append(", perpage=");
		builder.append(perpage);
		builder.append("]");
		return builder.toString();
	}
}