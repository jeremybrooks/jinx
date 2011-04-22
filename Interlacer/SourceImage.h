//
//  SourceImage.h
//  Interlacer
//
//  Created by Jeremy Brooks on 3/31/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//


@protocol SourceImage <NSObject>
@required

- (UIImage *)thumbnail;
- (CGSize)imageSize;
//- (void)copyImageDataTo:(unsigned char *)targetImage fromRow:(int)startRow skipRows:(int)skip  withWidth:(int)width andHeight:(int)height andRowHeight:(int)rowHeight;
- (void)copyImageDataTo:(unsigned char *)targetImage fromRow:(int)startRow skipRows:(int)skip  withWidth:(int)width andHeight:(int)height andRowHeight:(int)rowHeight andShift:(int)shift;
@end
