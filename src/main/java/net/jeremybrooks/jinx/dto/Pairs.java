package net.jeremybrooks.jinx.dto;

import java.util.List;

/**
 * <pairs page="1" total="1228" perpage="500" pages="3">
 * @author emeraldjava
 *
 */
public class Pairs {
	
	private int page;
	private int pages;
	private int total; 
	private int perpage;
	private List<Pair> pairs;
	
	public List<Pair> getPairs() {
		return pairs;
	}
	public void setPairs(List<Pair> pairs) {
		this.pairs = pairs;
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
		builder.append("Pairs[page=");
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
