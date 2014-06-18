# Jinx - Flickr API for Java

Jinx is a Java interface to the Flickr API. The project goals are:

 * Simple and straightforward to use
 * Complete coverage of the Flickr API
 * Minimal library requirements

You can find an example of how to use the Jinx library here: <https://github.com/jeremybrooks/jinxexamples>

There is an older version of Jinx, build 20110503, found here: <http://jeremybrooks.net/jinx/>. Version 0.6.0 and higher are a major rewrite of Jinx. They will not be compatible with old builds available on jeremybrooks.net.

## Notes About Jinx

### Objects vs Primitives
The Flickr API does not return all possible data for every API call. The returned values often depend on what parameters were passed in. In general, Jinx will return null to indicate that there was no value returned by Flickr. This means your code needs to pay attention to return types like Integer and Boolean, since they can return null.

The Flickr API returns boolean values as 1 or 0. Jinx will translate these to the Java Boolean type. The object Boolean is used rather than the primitive boolean so that a value of "not returned" can be represented.
For example, the Photos object can return information about who can see geotag data. However, this is considered extra data, and is not returned unless you specifically ask for it. If you did not ask for the data, Flickr will not return the data, and Jinx will return null when you call a method that returns the data.

### Errors
--todo--

# REQUIREMENTS
You must be using Java 1.6 or higher.

If you are using Maven, just include this in your pom.xml file

	<dependency>
		<groupId>net.jeremybrooks</groupId>
		<artifactId>jinx</artifactId>
		<version>0.6.0</version>
	</dependency>

If you are not using Maven, you will need these libraries, and their dependencies:
  
  * Gson 2.2.2+ -- deserializes json documents in to Java objects
  * Signpost 1.2.1.2 -- provides OAuth services

# VERSION HISTORY

## Version 0.6.0 supports the following API's
  * activity
  * blogs (have not really tested the postPhoto method, though)
  * cameras
  * oauth
  * photos
  * photosets
  * photosets.comments


# KNOWN ISSUES
If a user has no blogs set up, calling the getBlogList() method will throw an Exception. This is due to different data structures returned by Flickr if a user has blogs or does not have blogs. This issue has been reported to Flickr.

# API IMPLEMENTATION STATUS

## Available In Latest Release
  * activity
  * blogs (have not really tested the postPhoto method, though)
  * cameras
  * oauth
  * photos
  * photosets
  * photosets.comments

## Available In Latest Source
 * collections
 * commons
 * contacts
 * favorites
 * galleries

## Not Yet Implemented
  * groups
  * groups.members
  * groups.pools
  * interestingness
  * machinetags (partial)
  * panda
  * people
  * photos.comments
  * photos.geo          (NOTE: Some of the methods in photos.geo do not work correctly.
                               They also do not work in the Flickr API Explorer,
                               so there is likely little that can be done about it.)
  * photos.licenses
  * photos.notes
  * photos.people
  * photos.transform
  * photos.upload
  * places
  * prefs
  * reflection
  * stats
  * tags
  * test
  * urls


# BUILDING FROM SOURCE
To build the project, use Maven. The jar file will end up in the "target" directory:

	mvn clean package

If the tests are failing and you want to build the jar anyway, add -DskipTests:

	mvn clean package -DskipTests


# JINX PROJECT CONTRIBUTORS - READ THIS
Flickr apps need an API key to work properly. Jinx is no exception. To run the unit tests, you need to go to Flickr and apply for an API key. Once you have this key, you need to do the following:

  * Copy the file test/resources/flickr.properties to test/resources/secret.properties
  * Fill in the key, secret, and a valid full path where the oauth token will be saved
  * Uncomment the call to testGetOauthAccessToken() in OAuthApiTest
  * Run OAuthApiTest
  * Authorize the application
  * Enter the verification code and allow the test to complete

You should now be able to run the rest of the tests. The secret.properties file is excluded by the .gitignore file, so your secrets should not end up on github.

NOTE: The test token will need WRITE and DELETE access to your photostream! Do not authorize the test application if you are concerned about possible data loss due to test errors!

The code is organized as follows:

Package net.jeremybrooks.jinx
	Contains the main Jinx class. Keeps the API key, secret, and OAuth info during runtime. Other classes use this to call Flickr.
	Contains constants
	Contains the OAuthAccesToken class, used to store and load the oauth access token info

Package net.jeremybrooks.jinx.api
	Contains the classes that correspond to the Flickr API.
	Each class should correspond to a section of the Flickr API, and each method in the class should correspond to a method in the Flickr API.

Package net.jeremybrooks.jinx.response
	Contains the objects that are returned from the API classes. Each object should represent the response returned by the call to a Flickr API method.
	Response objects should extend net.jeremybrooks.jinx.response
	Some methods can share return values, for example the methods in the ActivityApi.

There should be a test for each Response object. The response object tests should use sample json responses to test that the deserialization works as expected. Test json documents are in the test/resources/response folder.
The response objects represent deserialized json documents, and can have quite complex object graphs. Care should be taken to expose the data in a way that makes sense in the Java world. For example, the perms field in the OAuthCredentials json document looks like this:

	{ "oauth": {
		"token": { "_content": "72157632940881881-6db7bec3c46b67b2" },
		"perms": { "_content": "delete" },
		"user": { "nsid": "85853333@N00", "username": "Jeremy Brooks", "fullname": "Jeremy Brooks" } }, "stat": "ok" }

When deserialized to Java objects, the permissions string is named _content and is inside a "Perms" object inside an "Oauth" object. If left alone, users of Jinx would have to do this to get the value:

	response.getOauth().getPerms().get_Content();

Rather than make the user remember which object the perms value is found in, the OAuthCredentials response object provides a getPerms() method that returns a String. The complexity of the object graph is hidden from the user. This results in somewhat ugly and complex classes, but the view to the Jinx user is clean and simple.

Generally, response objects are read only. They will have getters, but not setters. The Gson library is used to deserialize json documents into Java objects, and operates on the fields directly.

## Coding Conventions

This project attempts to follow the Sun (now Oracle) [Java coding conventions](http://www.oracle.com/technetwork/java/codeconvtoc-136057.html).

Since this library uses Gson to deserialize JSON documents into Java classes, the Java classes contain a lot of inner classes. This can be confusing at first glance. The classes in the package net.jeremybrooks.jinx.response should follow these guidelines:

 * All classes should implement java.io.Serializable, including inner classes
 * Private inner classes should be named with a beginning underscore
 * Objects should be returned as opposed to primitives; null should be returned when the data was not provided by Flickr
 * Getter methods should be careful to avoid null pointers when returning values, especially when returning values from inner classes
 * In general, these classes will have getters but not setters


# LICENSE
Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors

Jinx is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Jinx is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Jinx.  If not, see <http://www.gnu.org/licenses/>.

# TODO
 * Enable/disable logging of requests and responses
