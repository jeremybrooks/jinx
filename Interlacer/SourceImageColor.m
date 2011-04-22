//
//  SourceImageColor.m
//  Interlacer
//
//  Created by Jeremy Brooks on 4/8/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

#import "SourceImageColor.h"
#import "Constants.h"

@implementation SourceImageColor

@synthesize thumbnail;
@synthesize color;

- (id)initWithColor:(UIColor *)aColor
{
    self = [super init];
    if (self) {
        imageSizeMax = CGSizeMake(MAXFLOAT, MAXFLOAT);
        color = aColor;
        [color retain];
    }
    
    return self;
}

- (CGSize)imageSize
{
    return imageSizeMax;
}
/*
- (void)doCopyTo:(unsigned char *)targetImage fromRow:(int)startRow skipRows:(int)skip  withWidth:(int)width andHeight:(int)height andRowHeight:(int)rowHeight 
{
    const CGFloat * components = CGColorGetComponents(self.color.CGColor);
    
    int targetIndex = width * BYTES_PER_PIXEL * startRow;
    
    // loop until we reach height rows
    for (int row = startRow; row < height; row+=rowHeight*(skip+1)) {
        
        // if rowHeight is > remaning rows, rowHeight = rows
        if (rowHeight > height-row) {
            rowHeight = height - row;
        }
        
        // copy rowHeight rows of data
        for (int j = 0; j < rowHeight; j++) {
            for (int k = 0; k < width; k++) {
                targetImage[targetIndex++] = components[0]*255;
                targetImage[targetIndex++] = components[1]*255;
                targetImage[targetIndex++] = components[2]*255;
                targetImage[targetIndex++] = components[3]*255;
            }
        }
        
        // move indexes skip rows
        targetIndex += width * BYTES_PER_PIXEL * skip * rowHeight;
    }
    
}
 */
/*
- (void)copyPreviewImageDataTo:(unsigned char *)targetImage fromRow:(int)startRow skipRows:(int)skip  withWidth:(int)width andHeight:(int)height andRowHeight:(int)rowHeight
{
    [self doCopyTo:targetImage
           fromRow:startRow
          skipRows:skip
         withWidth:width
         andHeight:height
      andRowHeight:rowHeight];
}*/
/*
- (void)copyImageDataTo:(unsigned char *)targetImage fromRow:(int)startRow skipRows:(int)skip  withWidth:(int)width andHeight:(int)height andRowHeight:(int)rowHeight
{
   // [self doCopyTo:targetImage
   //        fromRow:startRow
   //       skipRows:skip
   //      withWidth:width
   //      andHeight:height
   //   andRowHeight:rowHeight];
    const CGFloat * components = CGColorGetComponents(self.color.CGColor);
    
    int targetIndex = width * BYTES_PER_PIXEL * startRow;
    
    // loop until we reach height rows
    for (int row = startRow; row < height; row+=rowHeight*(skip+1)) {
        
        // if rowHeight is > remaning rows, rowHeight = rows
        if (rowHeight > height-row) {
            rowHeight = height - row;
        }
        
        // copy rowHeight rows of data
        for (int j = 0; j < rowHeight; j++) {
            for (int k = 0; k < width; k++) {
                targetImage[targetIndex++] = components[0]*255;
                targetImage[targetIndex++] = components[1]*255;
                targetImage[targetIndex++] = components[2]*255;
                targetImage[targetIndex++] = components[3]*255;
            }
        }
        
        // move indexes skip rows
        targetIndex += width * BYTES_PER_PIXEL * skip * rowHeight;
    }
    
}
 */


/*
 * startRow and skipRows do not take into account the rowHeight.
 * This method should adjust for rowHeight as needed.
 */
- (void)copyImageDataTo:(unsigned char *)targetImage fromRow:(int)startRow skipRows:(int)skip  withWidth:(int)width andHeight:(int)height andRowHeight:(int)rowHeight andShift:(int)shift
{
    BOOL random = (rowHeight == MAX_ROW_HEIGHT);
    if (random) {
        rowHeight = 0;
    }
    
    const CGFloat * components = CGColorGetComponents(self.color.CGColor);
    
    int targetIndex = width * BYTES_PER_PIXEL * startRow * rowHeight;
    
    //skip = rowHeight;
    
    // loop until we reach height rows
    //for (int row = startRow; row < height; row+=rowHeight*(skip+1)) {
    for (int row = startRow * rowHeight; row < height; ) {
        if (random) {
            rowHeight = (arc4random() % MAX_DISPLAYED_ROW_HEIGHT) + 1;
            skip = (arc4random() % MAX_DISPLAYED_ROW_HEIGHT) + 1;
        }
        
        // if rowHeight is > remaning rows, rowHeight = rows
        if (rowHeight > height-row) {
            rowHeight = height - row;
        }
        
        // copy rowHeight rows of data
        for (int j = 0; j < rowHeight; j++) {
            for (int k = 0; k < width; k++) {
                targetImage[targetIndex++] = components[0]*255;
                targetImage[targetIndex++] = components[1]*255;
                targetImage[targetIndex++] = components[2]*255;
                targetImage[targetIndex++] = components[3]*255;
            }
        }
        
        // skip rows and increment row variable
        // if skip is random, it indicates the number of rows to skip.
        // if not random, it is the number of rowHeight rows to skip.
        if (random) {
            targetIndex += width * BYTES_PER_PIXEL * skip;
            row += skip;
        } else {
            targetIndex += width * BYTES_PER_PIXEL * skip * rowHeight;
            row += skip * rowHeight;
        }
        row += rowHeight;
    }
}

- (void) dealloc
{
    self.thumbnail = nil;
    [color release];
    [super dealloc];
}
@end
