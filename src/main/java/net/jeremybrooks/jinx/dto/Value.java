package net.jeremybrooks.jinx.dto;

/**
 * <value usage="4" namespace="taxonomy" predicate="common" first_added="1244232796" last_added="1244232796">maui chaff flower</value>
 * @author assure
 *
 */
public class Value {
	
	private String namespace;
	private String predicate;
	private int firstadded;
	private int lastadded;
	private int usage;
	private String value;
	
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
	public int getFirstadded() {
		return firstadded;
	}
	public void setFirstadded(int firstadded) {
		this.firstadded = firstadded;
	}
	public int getLastadded() {
		return lastadded;
	}
	public void setLastadded(int lastadded) {
		this.lastadded = lastadded;
	}
	public int getUsage() {
		return usage;
	}
	public void setUsage(int usage) {
		this.usage = usage;
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
		builder.append("Value [");
		if (namespace != null) {
			builder.append("namespace=");
			builder.append(namespace);
			builder.append(", ");
		}
		if (predicate != null) {
			builder.append("predicate=");
			builder.append(predicate);
			builder.append(", ");
		}
		builder.append("firstadded=");
		builder.append(firstadded);
		builder.append(", lastadded=");
		builder.append(lastadded);
		builder.append(", usage=");
		builder.append(usage);
		builder.append(", ");
		if (value != null) {
			builder.append("value=");
			builder.append(value);
		}
		builder.append("]");
		return builder.toString();
	}
}
