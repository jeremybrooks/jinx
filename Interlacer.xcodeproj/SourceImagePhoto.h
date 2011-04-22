//
//  SourceImagePhoto.h
//  Interlacer
//
//  Created by Jeremy Brooks on 3/31/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

#import "SourceImage.h"

@interface SourceImagePhoto : NSObject <SourceImage> {
    @private
    UIImage *image;
}

@property (nonatomic, retain) UIImage *thumbnail;
@property (nonatomic, retain) UIImage *image;

- (CGSize)imageSize;

@end
