package net.jeremybrooks.jinx.dto;

/**
 * <namespace usage="6538" predicates="13">aero</namespace>
 * @author emeraldjava
 *
 */
public class Namespace {

	private int usage;
	private int predicates;
	private String value;
	public int getUsage() {
		return usage;
	}
	public void setUsage(int usage) {
		this.usage = usage;
	}
	public int getPredicates() {
		return predicates;
	}
	public void setPredicates(int predicates) {
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
		builder.append("Namespace[usage=");
		builder.append(usage);
		builder.append(", predicates=");
		builder.append(predicates);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}
