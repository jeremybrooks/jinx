//
//  Model.h
//  Interlacer
//
//  Created by Jeremy Brooks on 3/31/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

#import "SourceImage.h"

@interface Model : NSObject {
@private
    NSMutableArray *sourceImageArray;
    UIImage *processedImage;
    //UIImage *previewImage;
}

@property (nonatomic, readonly) NSMutableArray *sourceImageArray;
@property (nonatomic, retain) UIImage *processedImage;
//@property (nonatomic, retain) UIImage *previewImage;

- (void)addSourceImage:(id<SourceImage>)sourceImage;
- (UIImage *)imageForOverlay;
- (CGSize)smallestSizeForFinalImage;
- (void)clear;

@end
