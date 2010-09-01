/*
 * Jinx is Copyright 2010 by Jeremy Brooks
 *
 * This file is part of Jinx.
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
package net.jeremybrooks.jinx.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Sample of the comments XML:
 *
 <?xml version="1.0" encoding="utf-8" ?>
 <rsp stat="ok">
   <comments photo_id="4727219469">
     <comment id="4956757-4727219469-72157624216005927"
       author="60872307@N00"
       authorname="neocles"
       datecreate="1277310024"
       permalink="http://www.flickr.com/photos/jeremybrooks/4727219469/#comment72157624216005927">
         Fabulous!
     </comment>
     <comment id="4956757-4727219469-72157624341466216"
       author="39474253@N00"
       authorname="B.S. Wise"
       datecreate="1277317862"
       permalink="http://www.flickr.com/photos/jeremybrooks/4727219469/#comment72157624341466216">
         great sign and shot...
     </comment>
   </comments>
 </rsp>
 * @author jeremyb
 */
public class Comments implements Serializable {

    
    private String photoId;
    private List<Comment> commentList;

}
