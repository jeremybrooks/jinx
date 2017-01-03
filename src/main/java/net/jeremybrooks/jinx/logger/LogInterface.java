/*
 * Jinx is Copyright 2010-2017 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.logger;

/**
 * Defines the interface that Jinx uses to log messages.
 *
 * By default, Jinx will not log anything. If you wish to see Jinx log output,
 * you can implement this class and then tell Jinx what class to use for logging
 * by calling <code>JinxLogger.setLogger(your class instance)</code>.
 * 
 * @author jeremyb
 */
public interface LogInterface {

    /**
     * Log a message.
     *
     * @param message the message.
     */
    public void log(String message);


    /**
     * Log a message along with an Exception.
     *
     * @param message the message.
     * @param t the cause of the error.
     */
    public void log(String message, Throwable t);
    
}
