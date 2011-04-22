//
//  SourceImagePhoto.m
//  Interlacer
//
//  Created by Jeremy Brooks on 3/31/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

#import "SourceImagePhoto.h"
#import "Constants.h"
#import "UIImage+Resize.h"


@interface SourceImagePhoto()
@end

@implementation SourceImagePhoto

@synthesize thumbnail;
@synthesize image;


// 
// This is the designated initializer.
//
// The thumbnail will be set to a generic waiting image, allowing immediate display of
// something while the real thumbnail is generated.
- (id)init
{
    self = [super init];
    if (self) {
        thumbnail = [UIImage imageNamed:@"wait.jpg"];
    }
    
    return self;
}


- (CGSize)imageSize
{
    return [self.image size];
}

/*
- (void)copyImageDataForImage:(UIImage *)theImage to:(unsigned char *)targetImage fromRow:(int)startRow skipRows:(int)skip withWidth:(int)width andHeight:(int)height andRowHeight:(int)rowHeight
{
    // convert image data to a char array
    CGImageRef imageRef = [theImage CGImage];
    NSUInteger imageWidth = CGImageGetWidth(imageRef);
    NSUInteger imageHeight = CGImageGetHeight(imageRef);
    CGColorSpaceRef colorSpace = CGColorSpaceCreateDeviceRGB();
    unsigned char *rawData = malloc(imageWidth * imageHeight * BYTES_PER_PIXEL);
    NSUInteger bytesPerRow = BYTES_PER_PIXEL * imageWidth;
    NSUInteger bitsPerComponent = 8;
    CGContextRef context = CGBitmapContextCreate(rawData, 
                                                 imageWidth, 
                                                 imageHeight,
                                                 bitsPerComponent, 
                                                 bytesPerRow, 
                                                 colorSpace,
                                                 kCGImageAlphaPremultipliedLast | kCGBitmapByteOrder32Big);
    CGColorSpaceRelease(colorSpace);
    
    CGContextDrawImage(context, CGRectMake(0, 0, imageWidth, imageHeight), imageRef);
    CGContextRelease(context);
    // end convert image data to a char array
    
    int rowRemainder = imageWidth - width;
    int sourceIndex = imageWidth * BYTES_PER_PIXEL * startRow;
    int targetIndex = width * BYTES_PER_PIXEL * startRow;
    
    // loop until we reach height rows
    for (int row = startRow; row < height; row+=rowHeight*(skip+1)) {
        
        // copy rowHeight rows of data from source to target
        // if rowHeight is > remaning rows, rowHeight = rows
        if (rowHeight > height-row) {
            rowHeight = height - row;
        }
        
        // copy rowHeight rows of data
        for (int j = 0; j < rowHeight; j++) {
            for (int k = 0; k < width * BYTES_PER_PIXEL; k++) {
                targetImage[targetIndex] = rawData[sourceIndex];
                targetIndex++;
                sourceIndex++;
            }
            // move source index to the end of the row in the source image
            sourceIndex += rowRemainder * BYTES_PER_PIXEL;
        }
        
        // move indexes skip rows
        sourceIndex += imageWidth * BYTES_PER_PIXEL * skip * rowHeight;
        targetIndex += width * BYTES_PER_PIXEL * skip * rowHeight;
    }
    
    free(rawData);    
}
*/
/*
- (void)copyPreviewImageDataTo:(unsigned char *)targetImage fromRow:(int)startRow skipRows:(int)skip  withWidth:(int)width andHeight:(int)height andRowHeight:(int)rowHeight
{
    [self copyImageDataForImage:self.previewImage
                             to:targetImage
                        fromRow:startRow
                       skipRows:skip
                      withWidth:width
                      andHeight:height
                   andRowHeight:rowHeight];
}
*/
/*
- (void)copyImageDataTo:(unsigned char *)targetImage fromRow:(int)startRow skipRows:(int)skip withWidth:(int)width andHeight:(int)height andRowHeight:(int)rowHeight
{
    
    
    \\[self copyImageDataForImage:self.image
    \\                        to:targetImage
    \\                    fromRow:startRow
    \\                   skipRows:skip
    \\                  withWidth:width
    \\                 andHeight:height
    \\               andRowHeight:rowHeight];
    
    int targetImageSize = width * height * BYTES_PER_PIXEL;
    
    // convert image data to a char array
    CGImageRef imageRef = [self.image CGImage];
    NSUInteger imageWidth = CGImageGetWidth(imageRef);
    NSUInteger imageHeight = CGImageGetHeight(imageRef);
    CGColorSpaceRef colorSpace = CGColorSpaceCreateDeviceRGB();
    unsigned char *rawData = malloc(imageWidth * imageHeight * BYTES_PER_PIXEL);
    int sourceImageSize = imageWidth * imageHeight * BYTES_PER_PIXEL;
    NSUInteger bytesPerRow = BYTES_PER_PIXEL * imageWidth;
    NSUInteger bitsPerComponent = 8;
    CGContextRef context = CGBitmapContextCreate(rawData, 
                                                 imageWidth, 
                                                 imageHeight,
                                                 bitsPerComponent, 
                                                 bytesPerRow, 
                                                 colorSpace,
                                                 kCGImageAlphaPremultipliedLast | kCGBitmapByteOrder32Big);
    CGColorSpaceRelease(colorSpace);
    
    CGContextDrawImage(context, CGRectMake(0, 0, imageWidth, imageHeight), imageRef);
    CGContextRelease(context);
    // end convert image data to a char array
    
    int rowRemainder = imageWidth - width;
    int sourceIndex = imageWidth * BYTES_PER_PIXEL * startRow;
    int targetIndex = width * BYTES_PER_PIXEL * startRow;
    
    // loop until we reach height rows
    for (int row = startRow; row < height; row+=rowHeight*(skip+1)) {
        
        // copy rowHeight rows of data from source to target
        // if rowHeight is > remaning rows, rowHeight = rows
        if (rowHeight > height-row) {
            rowHeight = height - row;
        }
        
        // copy rowHeight rows of data
        for (int j = 0; j < rowHeight; j++) {
            for (int k = 0; k < width * BYTES_PER_PIXEL; k++) {
                // If the index has run past the size of the image, do not copy data
                // this will prevent a crash in the case of unexpected image problems.
                // The resulting image will not look right, but that is better than a crash.
                if (targetIndex > targetImageSize || sourceIndex > sourceImageSize) {
                    break;
                }
                targetImage[targetIndex] = rawData[sourceIndex];
                targetIndex++;
                sourceIndex++;
            }
            // move source index to the end of the row in the source image
            sourceIndex += rowRemainder * BYTES_PER_PIXEL;
        }
        
        // move indexes skip rows
        sourceIndex += imageWidth * BYTES_PER_PIXEL * skip * rowHeight;
        targetIndex += width * BYTES_PER_PIXEL * skip * rowHeight;
    }
    
    free(rawData);
}
*/

/*
 * startRow and skipRows do not take into account the rowHeight.
 * This method should adjust for rowHeight as needed.
 */
- (void)copyImageDataTo:(unsigned char *)targetImage fromRow:(int)startRow skipRows:(int)skip  withWidth:(int)width andHeight:(int)height andRowHeight:(int)rowHeight andShift:(int)shift
{
    BOOL shouldShift = YES && shift != 0;
    BOOL random = (rowHeight == MAX_ROW_HEIGHT);
    if (random) {
        rowHeight = 0;
    }
    //skip = rowHeight;
    int targetImageSize = width * height * BYTES_PER_PIXEL;
    
    // convert image data to a char array
    CGImageRef imageRef = [self.image CGImage];
    NSUInteger imageWidth = CGImageGetWidth(imageRef);
    NSUInteger imageHeight = CGImageGetHeight(imageRef);
    CGColorSpaceRef colorSpace = CGColorSpaceCreateDeviceRGB();
    unsigned char *rawData = malloc(imageWidth * imageHeight * BYTES_PER_PIXEL);
    int sourceImageSize = imageWidth * imageHeight * BYTES_PER_PIXEL;
    NSUInteger bytesPerRow = BYTES_PER_PIXEL * imageWidth;
    NSUInteger bitsPerComponent = 8;
    CGContextRef context = CGBitmapContextCreate(rawData, 
                                                 imageWidth, 
                                                 imageHeight,
                                                 bitsPerComponent, 
                                                 bytesPerRow, 
                                                 colorSpace,
                                                 kCGImageAlphaPremultipliedLast | kCGBitmapByteOrder32Big);
    CGColorSpaceRelease(colorSpace);
    
    CGContextDrawImage(context, CGRectMake(0, 0, imageWidth, imageHeight), imageRef);
    CGContextRelease(context);
    // end convert image data to a char array
    
    int rowRemainder = imageWidth - width;
    int sourceIndex = imageWidth * BYTES_PER_PIXEL * startRow * rowHeight;
    int targetIndex = width * BYTES_PER_PIXEL * startRow * rowHeight;
    
    // loop until we reach height rows
    //for (int row = startRow; row < height; row+=rowHeight*(skip+1)) {
    for (int row = startRow * rowHeight; row < height; ) {
        
        if (random) {
            rowHeight = (arc4random() % MAX_DISPLAYED_ROW_HEIGHT) + 1;
            skip = (arc4random() % MAX_DISPLAYED_ROW_HEIGHT) + 1;
        }
        
        // copy rowHeight rows of data from source to target
        // if rowHeight is > remaning rows, rowHeight = rows
        if (rowHeight > height-row) {
            rowHeight = height - row;
        }
        
        // copy rowHeight rows of data
        if (shouldShift) {
            // if shift > 0, pixels are moved to the right, and end of row wraps to beginning
            if (shift > 0) {
                for (int j = 0; j < rowHeight; j++) {
                    int sourceRowStart = sourceIndex;
                    
                    // move source to end of row minus shift,
                    sourceIndex += (width - shift) * BYTES_PER_PIXEL;
                    for (int k = 0; k < shift * BYTES_PER_PIXEL; k++) {
                        if (targetIndex > targetImageSize || sourceIndex > sourceImageSize) {
                            break;
                        }
                        targetImage[targetIndex] = rawData[sourceIndex];
                        targetIndex++;
                        sourceIndex++;
                    }
                    
                    // now move source to beginning of row, and copy up to the shift point
                    sourceIndex = sourceRowStart;
                    for (int k = 0; k < (width - shift) * BYTES_PER_PIXEL; k++) {
                        if (targetIndex > targetImageSize || sourceIndex > sourceImageSize) {
                            break;
                        }
                        targetImage[targetIndex] = rawData[sourceIndex];
                        targetIndex++;
                        sourceIndex++;
                    }
                    
                    // done with a row of data, so move source to end of row
                    sourceIndex += shift * BYTES_PER_PIXEL;
                }
            } // end if shift > 0
            
            // if shift < 0, pixels are moved to the left, and beginning of row wraps to end
            else if (shift < 0) {
                for (int j = 0; j < rowHeight; j++) {
                    int sourceRowStart = sourceIndex;
                    
                    // move source shift places
                    sourceIndex += abs(shift) * BYTES_PER_PIXEL;
                    // copy from current source to end of row
                    for (int k = 0; k < (width - abs(shift)) * BYTES_PER_PIXEL; k++) {
                        if (targetIndex > targetImageSize || sourceIndex > sourceImageSize) {
                            break;
                        }
                        targetImage[targetIndex] = rawData[sourceIndex];
                        targetIndex++;
                        sourceIndex++;
                    }
                    
                    // now move source to beginning of row, and copy up to shift point
                    sourceIndex = sourceRowStart;
                    for (int k = 0; k < abs(shift) * BYTES_PER_PIXEL; k++) {
                        if (targetIndex > targetImageSize || sourceIndex > sourceImageSize) {
                            break;
                        }
                        targetImage[targetIndex] = rawData[sourceIndex];
                        targetIndex++;
                        sourceIndex++;
                    }
                    
                    // done with a row of data, so move source to end of row
                    sourceIndex += (width - abs(shift)) * BYTES_PER_PIXEL;
                }
            } // end if shift < 0
            
            
        } else {
            for (int j = 0; j < rowHeight; j++) {
                for (int k = 0; k < width * BYTES_PER_PIXEL; k++) {
                    // If the index has run past the size of the image, do not copy data
                    // this will prevent a crash in the case of unexpected image problems.
                    // The resulting image will not look right, but that is better than a crash.
                    if (targetIndex > targetImageSize || sourceIndex > sourceImageSize) {
                        break;
                    }
                    targetImage[targetIndex] = rawData[sourceIndex];
                    targetIndex++;
                    sourceIndex++;
                }
                // move source index to the end of the row in the source image
                sourceIndex += rowRemainder * BYTES_PER_PIXEL;
            }
        }
        
        // toggle the shift flag, if shift is required
        shouldShift = (!shouldShift) && (shift != 0);
        
        // skip rows and increment row variable
        // if skip is random, it indicates the number of rows to skip.
        // if not random, it is the number of rowHeight rows to skip.
        if (random) {
            sourceIndex += imageWidth * BYTES_PER_PIXEL * skip;
            targetIndex += width * BYTES_PER_PIXEL * skip;
            row += skip;
        } else {
            sourceIndex += imageWidth * BYTES_PER_PIXEL * skip * rowHeight;
            targetIndex += width * BYTES_PER_PIXEL * skip * rowHeight;
            row += skip * rowHeight;
        }
        row += rowHeight;
    }
    
    free(rawData);
}

- (void)dealloc
{
    self.thumbnail = nil;
    self.image = nil;
    [super dealloc];
}

@end
