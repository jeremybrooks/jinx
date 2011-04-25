//
//  FullSizeImageViewController.m
//  Interlacer
//
//  Created by Jeremy Brooks on 4/25/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

#import "FullSizeImageViewController.h"


@implementation FullSizeImageViewController

@synthesize image;
@synthesize scrollView;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (UIView *)viewForZoomingInScrollView:(UIScrollView *)scrollView
{
    return imageView;
}

- (void)doubleTap:(UIPinchGestureRecognizer *)sender
{
    if (sender.state == UIGestureRecognizerStateRecognized) {
        if (self.scrollView.zoomScale == 1.0f) {
            [self.scrollView setZoomScale:self.scrollView.minimumZoomScale animated:YES];
        } else {
            [self.scrollView setZoomScale:1.0f animated:YES];
        }
    }
}

- (void)releaseOutlets
{
    self.scrollView = nil;
    self.image = nil;
}

- (void)dealloc
{
    [self releaseOutlets];
    [imageView release];
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
    self.title = [NSString stringWithFormat:@"%gx%g", self.image.size.width, self.image.size.height];

    self.scrollView.contentSize = self.image.size;
    imageView = [[UIImageView alloc] initWithImage:self.image];
    [self.scrollView addSubview:imageView];
    
    self.scrollView.delegate = self;
    
    // set min/max zoom, and zoom to show full image
    self.scrollView.maximumZoomScale = 3.0f;
    if (self.image.size.width > self.image.size.height) {
        self.scrollView.minimumZoomScale = self.scrollView.bounds.size.width / self.image.size.width;
    } else {
        self.scrollView.minimumZoomScale = self.scrollView.bounds.size.height / self.image.size.height;
    }
    self.scrollView.zoomScale = self.scrollView.minimumZoomScale;
    
    // support double tap
    UITapGestureRecognizer *doubletap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(doubleTap:)];
    doubletap.numberOfTapsRequired = 2;
    [self.scrollView addGestureRecognizer:doubletap];
    [doubletap release];
    
    [self.scrollView flashScrollIndicators];
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
