package net.jeremybrooks.jinx.dto;

import java.util.List;

/**
 * <namespaces page="1" total="5" perpage="500" pages="1">
 * @author emeraldjava
 *
 */
public class Namespaces {
	
	private int page;
	private int pages;
	private int total; 
	private int perpage;
	private List<Namespace> namespaces;
	
	public List<Namespace> getNamespaces() {
		return namespaces;
	}
	public void setNamespaces(List<Namespace> namespaces) {
		this.namespaces = namespaces;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
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
		builder.append("Namespaces[page=");
		builder.append(page);
		builder.append(", pages=");
		builder.append(pages);
		builder.append(", total=");
		builder.append(total);
		builder.append(", perpage=");
		builder.append(perpage);
		builder.append("]");
		return builder.toString();
	}

}
