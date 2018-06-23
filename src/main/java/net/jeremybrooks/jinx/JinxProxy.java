/*
 * Jinx is Copyright 2010-2018 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx;

/**
 * Encapsulates http proxy settings for Jinx to use.
 *
 * Created by jeremyb on 6/27/14.
 */
public class JinxProxy {

    private String proxyHost;
    private int proxyPort;
    private String proxyUser;
    private char[] proxyPassword;

    private JinxProxy(){}

    public JinxProxy(String proxyHost, int proxyPort, String proxyUser, char[] proxyPassword) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.proxyUser = proxyUser;
        this.proxyPassword = proxyPassword;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUser() {
        return proxyUser;
    }

    public void setProxyUser(String proxyUser) {
        this.proxyUser = proxyUser;
    }

    public char[] getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(char[] proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("JinxProxy{");
        sb.append("proxyHost='").append(proxyHost).append('\'');
        sb.append(", proxyPort=").append(proxyPort);
        sb.append(", proxyUser='").append(proxyUser).append('\'');
        sb.append(", proxyPassword=").append(proxyPassword == null ? "[null]" : "[********]");
        sb.append('}');
        return sb.toString();
    }
}
