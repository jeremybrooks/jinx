/*
 * Jinx is Copyright 2010-2025 by Jeremy Brooks and Contributors
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

import java.util.Objects;

/**
 * @author Jeremy Brooks
 */
public class JinxLogger {

  private static LogInterface logger = null;


  /**
   * @return the logger
   */
  public static LogInterface getLogger() {
    if (logger == null) {
      logger = new DefaultLogger();
    }
    return logger;
  }


  /**
   * @param logger the logger to set
   */
  public static void setLogger(LogInterface logger) {
    JinxLogger.logger = Objects.requireNonNullElseGet(logger, DefaultLogger::new);
  }
}
