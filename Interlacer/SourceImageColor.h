//
//  SourceImageColor.h
//  Interlacer
//
//  Created by Jeremy Brooks on 4/8/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SourceImage.h"

@interface SourceImageColor : NSObject <SourceImage> {
    @private
    CGSize imageSizeMax;
    UIColor *color;
}

@property (nonatomic, retain) UIImage *thumbnail;
@property (nonatomic, readonly) UIColor *color;

- (id)initWithColor:(UIColor *)aColor;

@end
