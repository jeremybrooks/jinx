//
//  InterlacerAppDelegate.h
//  Interlacer
//
//  Created by Jeremy Brooks on 3/31/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

#import <UIKit/UIKit.h>

@class InterlacerViewController;

@interface InterlacerAppDelegate : NSObject <UIApplicationDelegate> {

}

@property (nonatomic, retain) IBOutlet UIWindow *window;

@property (nonatomic, retain) IBOutlet InterlacerViewController *viewController;

@property (nonatomic, retain) UINavigationController *navCon;

@end
