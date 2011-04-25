//
//  FullSizeImageViewController.h
//  Interlacer
//
//  Created by Jeremy Brooks on 4/25/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface FullSizeImageViewController : UIViewController <UIScrollViewDelegate> {
    @private
    UIImageView *imageView;
}
@property (nonatomic, retain) UIImage *image;
@property (nonatomic, retain) IBOutlet UIScrollView *scrollView;

@end
