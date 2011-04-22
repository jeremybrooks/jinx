//
//  Model.m
//  Interlacer
//
//  Created by Jeremy Brooks on 3/31/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

#import "Model.h"
#import "Constants.h"
#import "SourceImage.h"
#import "SourceImagePhoto.h"

@implementation Model

@synthesize sourceImageArray;
@synthesize processedImage;
//@synthesize previewImage;

- (id)init
{
    self = [super init];
    if (self) {
        sourceImageArray = [[NSMutableArray alloc] init];
    }
    return self;
}

- (void)clear
{
    [self.sourceImageArray removeAllObjects];
    self.processedImage = nil;
//    self.previewImage = nil;
}


- (void)addSourceImage:(id <SourceImage>)sourceImage
{
    [self.sourceImageArray addObject:sourceImage];
}


//
// Find the smallest width and height of all the images in the array.
// The size is not the smallest image, but the smallest width and height
// of all the images.
//
// If there are only color swatches in the image list, the returned size
// will be the screen size.
//
// If there are no images in the list, the returned size will be zero x zero.
//
- (CGSize)smallestSizeForFinalImage
{
    CGSize size = CGSizeMake(MAXFLOAT, MAXFLOAT);
    
    for (id theImage in self.sourceImageArray) {
        if ([theImage isKindOfClass:[SourceImagePhoto class]]) {
            
            CGSize imageSize = [theImage imageSize];
            if (imageSize.width < size.width) {
                size.width = imageSize.width;
            }
            if (imageSize.height < size.height) {
                size.height = imageSize.height;
            }
        }
        
    }
    
    if (size.width == MAXFLOAT || size.height == MAXFLOAT) {
        // no images were found, so if there is anything in the array,
        // it is a color swatch
        if ([self.sourceImageArray count] > 0) {
            size.width = [UIScreen mainScreen].bounds.size.width * [UIScreen mainScreen].scale;
            size.height = [UIScreen mainScreen].bounds.size.height * [UIScreen mainScreen].scale;
        }
    }
    
    return size;
}


- (CGSize)largestSizeForFinalImage
{
    CGSize size = CGSizeMake(MAXFLOAT, MAXFLOAT);
    
    for (id theImage in self.sourceImageArray) {
        if ([theImage isKindOfClass:[SourceImagePhoto class]]) {
            
            CGSize imageSize = [theImage imageSize];
            if (imageSize.width > size.width) {
                size.width = imageSize.width;
            }
            if (imageSize.height > size.height) {
                size.height = imageSize.height;
            }
        }
    }
    
    if (size.width == MAXFLOAT || size.height == MAXFLOAT) {
        // no images were found, so if there is anything in the array,
        // it is a color match
        if ([self.sourceImageArray count] > 0) {
            size.width = [UIScreen mainScreen].bounds.size.width * [UIScreen mainScreen].scale;
            size.height = [UIScreen mainScreen].bounds.size.height * [UIScreen mainScreen].scale;
        }
    }
    
    return size;
}


//
// Return the first image in the array.
//
- (UIImage *)imageForOverlay
{
    UIImage *image = nil;
    
    for (id theImage in self.sourceImageArray) {
        if ([theImage isKindOfClass:[SourceImagePhoto class]]) {
            image = [theImage image];
            break;
        }
    }
    
    return image;
}


- (void)dealloc {
    [sourceImageArray release];
    [processedImage release];
    //[previewImage release];
    [super dealloc];
}
@end
