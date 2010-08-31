package net.jeremybrooks.jinx.dto;

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
public class Comments {

    
    private String photoId;
    private List<Comment> commentList;

}
