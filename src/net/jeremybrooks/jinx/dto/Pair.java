package net.jeremybrooks.jinx.dto;

/**
 * <pair namespace="aero" predicate="airline" usage="1093">aero:airline</pair>
 * @author emeraldjava
 *
 */
public class Pair {

	private String namespace;
	private int usage;
	private String predicates;
	private String value;
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public int getUsage() {
		return usage;
	}
	public void setUsage(int usage) {
		this.usage = usage;
	}
	public String getPredicates() {
		return predicates;
	}
	public void setPredicates(String predicates) {
		this.predicates = predicates;
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
		builder.append("Pair[usage=");
		builder.append(usage);
		builder.append(", predicates=");
		builder.append(predicates);
		builder.append(", namespace=");
		builder.append(namespace);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}
