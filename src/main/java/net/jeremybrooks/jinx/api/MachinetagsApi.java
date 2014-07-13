/*
 * Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors
 *
 * Jinx is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jinx is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Jinx.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.machinetags.Namespaces;
import net.jeremybrooks.jinx.response.machinetags.Pairs;
import net.jeremybrooks.jinx.response.machinetags.Predicates;
import net.jeremybrooks.jinx.response.machinetags.Values;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jeremyb on 7/10/14.
 */
public class MachinetagsApi {
    private Jinx jinx;

    public MachinetagsApi(Jinx jinx) {
        this.jinx = jinx;
    }

    /**
     * <a href="https://www.flickr.com/services/api/flickr.machinetags.getNamespaces.html">flickr.machinetags.getNamespaces</a>
     * <p/>
     * Return a list of unique namespaces, optionally limited by a given predicate, in alphabetical order.
     * <p/>
     * This method does not require authentication.
     *
     * @param predicate (Optional) Limit the list of namespaces returned to those that have this predicate.
     * @param perPage   Number of photos to return per page. If this argument is less than 1, it defaults to 100. The maximum allowed value is 500.
     * @param page      The page of results to return. If this argument is less than 1, it defaults to 1.
     * @param sign      if true, the request will be signed.
     * @return object containing a list of unique namespaces.
     * @throws JinxException if there are any errors.
     */
    public Namespaces getNamespaces(String predicate, int perPage, int page, boolean sign) throws JinxException {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.machinetags.getNamespaces");
        if (!JinxUtils.isNullOrEmpty(predicate)) {
            params.put("predicate", predicate);
        }
        if (perPage > 0) {
            params.put("per_page", Integer.toString(perPage));
        }
        if (page > 0) {
            params.put("page", Integer.toString(page));
        }
        return jinx.flickrGet(params, Namespaces.class, sign);
    }

    /**
     * <a href="https://www.flickr.com/services/api/flickr.machinetags.getPairs.html">flickr.machinetags.getPairs</a>
     * <p/>
     * Return a list of unique namespace and predicate pairs, optionally limited by predicate or namespace, in alphabetical order.
     * <p/>
     * This method does not require authentication.
     *
     * @param namespace (Optional) Limit the list of pairs returned to those that have this namespace.
     * @param predicate (Optional) Limit the list of pairs returned to those that have this predicate.
     * @param perPage   Number of photos to return per page. If this argument is less than 1, it defaults to 100. The maximum allowed value is 500.
     * @param page      The page of results to return. If this argument is less than 1, it defaults to 1.
     * @param sign      if true, the request will be signed.
     * @return object containing a list of unique namespace and predicate parts.
     * @throws JinxException if there are any errors.
     */
    public Pairs getPairs(String namespace, String predicate, int perPage, int page, boolean sign) throws JinxException {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.machinetags.getPairs");
        if (!JinxUtils.isNullOrEmpty(namespace)) {
            params.put("namespace", namespace);
        }
        if (!JinxUtils.isNullOrEmpty(predicate)) {
            params.put("predicate", predicate);
        }
        if (perPage > 0) {
            params.put("per_page", Integer.toString(perPage));
        }
        if (page > 0) {
            params.put("page", Integer.toString(page));
        }
        return jinx.flickrGet(params, Pairs.class, sign);
    }

    /**
     * <a href="https://www.flickr.com/services/api/flickr.machinetags.getPredicates.html">flickr.machinetags.getPredicates</a>
     * <p/>
     * Return a list of unique predicates, optionally limited by a given namespace.
     * <p/>
     * This method does not require authentication.
     * namespace (Optional)
     * <p/>
     * per_page (Optional)
     * Number of photos to return per page. If this argument is omitted, it defaults to 100. The maximum allowed value is 500.
     * page (Optional)
     * The page of results to return. If this argument is omitted, it defaults to 1.
     *
     * @param namespace (Optional) Limit the list of predicates returned to those that have this namespace.
     * @param perPage   Number of photos to return per page. If this argument is less than 1, it defaults to 100. The maximum allowed value is 500.
     * @param page      The page of results to return. If this argument is less than 1, it defaults to 1.
     * @param sign      if true, the request will be signed.
     * @return object containing a list of unique predicates.
     * @throws JinxException if there are any errors.
     */
    public Predicates getPredicates(String namespace, int perPage, int page, boolean sign) throws JinxException {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.machinetags.getPredicates");
        if (!JinxUtils.isNullOrEmpty(namespace)) {
            params.put("namespace", namespace);
        }
        if (perPage > 0) {
            params.put("per_page", Integer.toString(perPage));
        }
        if (page > 0) {
            params.put("page", Integer.toString(page));
        }
        return jinx.flickrGet(params, Predicates.class, sign);
    }

    /**
     * <a href="https://www.flickr.com/services/api/flickr.machinetags.getRecentValues.html">flickr.machinetags.getRecentValues</a>
     * <p/>
     * Fetch recently used (or created) machine tags values.
     * <p/>
     * This method does not require authentication.
     *
     * @param namespace  (Optional) A namespace that all values should be restricted to.
     * @param predicate  (Optional) A predicate that all values should be restricted to.
     * @param addedSince (Optional) Only return machine tags values that have been added since this timestamp, in epoch seconds.
     * @param sign       if true, the request will be signed.
     * @return object containing a list of recently used or created machine tags values.
     * @throws JinxException if there are any errors.
     */
    public Values getRecentValues(String namespace, String predicate, String addedSince, boolean sign) throws JinxException {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.machinetags.getRecentValues");
        if (!JinxUtils.isNullOrEmpty(namespace)) {
            params.put("namespace", namespace);
        }
        if (!JinxUtils.isNullOrEmpty(predicate)) {
            params.put("predicate", predicate);
        }
        if (!JinxUtils.isNullOrEmpty(addedSince)) {
            params.put("added_since", addedSince);
        }
//        if (perPage > 0) {
//            params.put("per_page", Integer.toString(perPage));
//        }
//        if (page > 0) {
//            params.put("page", Integer.toString(page));
//        }
        return jinx.flickrGet(params, Values.class, sign);
    }

    /**
     * <a href="https://www.flickr.com/services/api/flickr.machinetags.getValues.html">flickr.machinetags.getValues</a>
     * <p/>
     * Return a list of unique values for a namespace and predicate.
     * <p/>
     * This method does not require authentication.
     *
     * @param namespace (Required) The namespace that all values should be restricted to.
     * @param predicate (Required) The predicate that all values should be restricted to.
     * @param perPage   Number of photos to return per page. If this argument is less than 1, it defaults to 100. The maximum allowed value is 500.
     * @param page      The page of results to return. If this argument is less than 1, it defaults to 1.
     * @param sign      if true, the request will be signed.
     * @return object containing a list of unique values for a namespace and predicate.
     * @throws JinxException if required parameters are missing or if there are any errors.
     */
    public Values getValues(String namespace, String predicate, int perPage, int page, boolean sign) throws JinxException {
        JinxUtils.validateParams(namespace, predicate);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.machinetags.getValues");
        if (!JinxUtils.isNullOrEmpty(namespace)) {
            params.put("namespace", namespace);
        }
        if (!JinxUtils.isNullOrEmpty(predicate)) {
            params.put("predicate", predicate);
        }
        if (perPage > 0) {
            params.put("per_page", Integer.toString(perPage));
        }
        if (page > 0) {
            params.put("page", Integer.toString(page));
        }
        return jinx.flickrGet(params, Values.class, sign);
    }
}
