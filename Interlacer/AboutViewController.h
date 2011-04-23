//
//  AboutViewController.h
//  Interlacer
//
//  Created by Jeremy Brooks on 4/21/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MessageUI/MFMailComposeViewController.h>

@interface AboutViewController : UIViewController <UIWebViewDelegate, MFMailComposeViewControllerDelegate> {
    
}

@property (nonatomic, retain) IBOutlet UIWebView *webView;
@property (nonatomic, retain) IBOutlet UIButton *button;
@property (nonatomic, retain) IBOutlet UILabel *versionLabel;
@property (nonatomic, retain) IBOutlet UIButton *backButton;

- (IBAction)dismiss:(UIButton *)sender;
- (IBAction)backButtonPressed:(UIButton *)backButton;

@end
