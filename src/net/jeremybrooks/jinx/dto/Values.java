package net.jeremybrooks.jinx.dto;

import java.util.List;

public class Values {

	private String namespace;
	private String predicate; 
	
	private int page;
	private int total; 
	private int perpage; 
	private int pages;

	private List<Value> values;
	
	public List<Value> getValues() {
		return values;
	}
	public void setValues(List<Value> values) {
		this.values = values;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getPredicate() {
		return predicate;
	}
	public void setPredicate(String predicate) {
		this.predicate = predicate;
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
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Values [namespace=");
		builder.append(namespace);
		builder.append(", predicate=");
		builder.append(predicate);
		builder.append(", page=");
		builder.append(page);
		builder.append(", total=");
		builder.append(total);
		builder.append(", perpage=");
		builder.append(perpage);
		builder.append(", pages=");
		builder.append(pages);
		builder.append(", values=");
		builder.append(values.size());
		builder.append("]");
		return builder.toString();
	}	
}