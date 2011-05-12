package net.jeremybrooks.jinx.dto;

/**
 * <predicate usage="20" namespaces="1">elbow</predicate>
 * @author emeraldjava
 *
 */
public class Predicate {
	
	private int usage;
	private int namespaces;
	private String value;
	public int getUsage() {
		return usage;
	}
	public void setUsage(int usage) {
		this.usage = usage;
	}
	public int getNamespaces() {
		return namespaces;
	}
	public void setNamespaces(int namespaces) {
		this.namespaces = namespaces;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Predicate[usage=");
		builder.append(usage);
		builder.append(", namespaces=");
		builder.append(namespaces);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}