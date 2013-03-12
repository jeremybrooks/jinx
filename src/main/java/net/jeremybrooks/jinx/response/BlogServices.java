package net.jeremybrooks.jinx.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Response from the Flickr blogs.getServices API request.
 *
 * @author Jeremy Brooks
 */
public class BlogServices extends Response {
	private static final long serialVersionUID = 5614928721323005674L;

	private Services services;


	/**
	 * Get the list of services.
	 *
	 * @return list of services, or null if nothing was found.
	 */
	public List<Service> getServiceList() {
		return services.service == null ? null : services.service;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("classname=").append(this.getClass().getName());
		sb.append(",services=[");
		for (Service service : this.getServiceList()) {
			sb.append(service.toString());
		}
		sb.append("],");
		sb.append(super.toString());
		return sb.toString();
	}


	private class Services {
		private List<Service> service;
	}

	/**
	 * Represents a blogging service.
	 * Each service has an ID and a Name.
	 */
	public class Service {
		private String id;
		@SerializedName("_content")
		private String name;

		/**
		 * Get the ID of the service.
		 * @return id of the service.
		 */
		public String getId() {
			return id;
		}

		/**
		 * Get the name of the service.
		 * Note: this field is called "_content" in the response document. We call it name here.
		 * @return name of the service.
		 */
		public String getName() {
			return name;
		}


		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("{id=\"").append(id).append('\"');
			sb.append(",name=\"").append(name).append('\"').append('}');
			return sb.toString();
		}
	}
}
