//
//  AboutViewController.m
//  Interlacer
//
//  Created by Jeremy Brooks on 4/21/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

#import "AboutViewController.h"
#import "MBProgressHUD.h"

@implementation AboutViewController

@synthesize webView, button, versionLabel, backButton;


- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (IBAction)dismiss:(UIButton *)sender
{
    [self dismissModalViewControllerAnimated:YES];
}


- (IBAction)backButtonPressed:(UIButton *)backButton
{
    if (self.webView.canGoBack) {
        [self.webView goBack];
    } else {
        [self dismissModalViewControllerAnimated:YES];
    }
}


// 
// Show a spinner when user clicks a link that loads something
//
- (void)webViewDidStartLoad:(UIWebView *)theWebView
{
    [MBProgressHUD showHUDAddedTo:self.webView animated:YES];
}


//
// Dismiss the spinner when the content has loaded
//
- (void)webViewDidFinishLoad:(UIWebView *)theWebView
{
    [MBProgressHUD hideHUDForView:self.webView animated:YES];
}


// Display a message if the content could not be loaded
//
- (void)webView:(UIWebView *)theWebView didFailLoadWithError:(NSError *)error
{
    [MBProgressHUD hideHUDForView:self.webView animated:YES];
    
    [[[[UIAlertView alloc] initWithTitle:@"Error"
                                 message:@"Could not load content."
                                delegate:nil
                       cancelButtonTitle:@"OK"
                       otherButtonTitles:nil] autorelease] show];
}


//
// Detect a click on "mailto" link and handle it ourselves
// Other content is handled by the web view
//
- (BOOL)webView:(UIWebView *)theWebView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType
{
    BOOL handle = YES;
    
    if ([[[request URL] scheme] isEqualToString:@"mailto"]) {
        handle = NO;
        MFMailComposeViewController *mail = [[MFMailComposeViewController alloc] init];
        mail.mailComposeDelegate = self;
        [mail setToRecipients:[NSArray arrayWithObject:@"interlacerapp@jeremybrooks.net"]];
        [mail setSubject:@"Interlacer Feedback"];
        [self presentModalViewController:mail animated:YES];        
    }
    
    return handle;
}


//
// Dismiss the mail dialog and say thanks
//
- (void)mailComposeController:(MFMailComposeViewController*)controller didFinishWithResult:(MFMailComposeResult)result error:(NSError*)error
{
    if (result == MFMailComposeResultSent) {
        [[[[UIAlertView alloc] initWithTitle:@"Thanks!"
                                     message:@"Thank you for the feedback."
                                    delegate:nil
                           cancelButtonTitle:@"Dismiss"
                           otherButtonTitles:nil] autorelease] show];
    }
    
    [self dismissModalViewControllerAnimated:YES];
}


- (void)releaseOutlets
{
    // NOTE: Must set delegate to nil prior to release per Apple docs
    self.webView.delegate = nil;
    self.webView = nil;
    
    self.button = nil;
    self.versionLabel = nil;
    self.backButton = nil;
}



- (void)dealloc
{
    [self releaseOutlets];
    [super dealloc];
}

- (void)didReceiveMemoryWarning
{
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc that aren't in use.
}

#pragma mark - View lifecycle

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
    NSString *version = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleShortVersionString"];
    NSString *build = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleVersion"];
    self.versionLabel.text = [NSString stringWithFormat:@"Version %@ (%@)", version, build];
    
    
    // Load the about document
    NSString *path = [[NSBundle mainBundle] pathForResource:@"about" ofType:@"html"];
    [self.webView loadRequest:[NSURLRequest requestWithURL:[NSURL fileURLWithPath:path isDirectory:NO]]];
    
    // Set delegate
    self.webView.delegate = self;   
}

- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
    [self releaseOutlets];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

@end
