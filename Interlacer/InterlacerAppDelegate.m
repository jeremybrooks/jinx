//
//  InterlacerAppDelegate.m
//  Interlacer
//
//  Created by Jeremy Brooks on 3/31/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

#import "InterlacerAppDelegate.h"

#import "InterlacerViewController.h"
#import "Constants.h"

@implementation InterlacerAppDelegate


@synthesize window=_window;

@synthesize viewController=_viewController;

@synthesize navCon;

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    // Set up preference defaults
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    [defaults registerDefaults:[NSDictionary dictionaryWithObjectsAndKeys:
                                [NSNumber numberWithBool:NO], kShowPhotoOverlay,
                                [NSNumber numberWithBool:YES], kSaveCameraPhotos,
                                [NSNumber numberWithBool:NO], kAutoSaveProcessedImage,
                                nil]];
                        
    
    [[UIApplication sharedApplication] setStatusBarHidden:YES];
    
    self.navCon = [[[UINavigationController alloc] init] autorelease];
    [self.navCon.navigationBar setTintColor:[UIColor colorWithRed:48.0f/255.0f
                                                            green:26.0f/255.0f
                                                             blue:79.0f/255.0f
                                                            alpha:1.0f]];
    
    // Add the interlacer view to the nav controller, and add a right button to show settings
    InterlacerViewController *ilvc = [[InterlacerViewController alloc] init];
    ilvc.title = @"Interlacer";
    
    NSString *imgPath = [[NSBundle mainBundle] pathForResource:@"gear" ofType:@"png"];
    UIBarButtonItem *settingsButton = 
        [[UIBarButtonItem alloc] initWithImage:[UIImage imageWithContentsOfFile:imgPath]
                                         style:UIBarButtonItemStyleBordered
                                        target:ilvc
                                        action:@selector(showSettings)];
    
    ilvc.navigationItem.rightBarButtonItem = settingsButton;
    [settingsButton release];

    [self.navCon pushViewController:ilvc animated:NO];
    
    [ilvc release];
     
    [self.window addSubview:self.navCon.view];
     
    [self.window makeKeyAndVisible];
    
    return YES;
}

- (void)applicationWillResignActive:(UIApplication *)application
{
    /*
     Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
     Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
     */
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    /*
     Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later. 
     If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
     */
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    /*
     Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
     */
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    /*
     Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
     */
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    /*
     Called when the application is about to terminate.
     Save data if appropriate.
     See also applicationDidEnterBackground:.
     */
}

- (void)dealloc
{
    [_window release];
    [_viewController release];
    [navCon release];
    [super dealloc];
}

@end
