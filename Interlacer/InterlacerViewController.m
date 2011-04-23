//
//  InterlacerViewController.m
//  Interlacer
//
//  Created by Jeremy Brooks on 3/31/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

#import "InterlacerViewController.h"
#import "SourceImage.h"
#import "SourceImagePhoto.h"
#import "Constants.h"
#import "UIImage+Resize.h"
#import "MBProgressHUD.h"
#import "SettingsViewController.h"
#import "GradientView.h"
#import "IASKAppSettingsViewController.h"
#import "SourceImageColor.h"
#import "AboutViewController.h"

#import <MobileCoreServices/UTCoreTypes.h>

#import <AssetsLibrary/ALAsset.h>
#import <AssetsLibrary/ALAssetRepresentation.h>
#import <AssetsLibrary/ALAssetsLibrary.h>
#import <AssetsLibrary/AssetsLibrary.h>

#import <QuartzCore/QuartzCore.h>

@implementation InterlacerViewController

@synthesize previewImage;
@synthesize sourceImageView;
@synthesize model;
@synthesize slider, sliderLabel;
@synthesize mainButtonView;
@synthesize subButtonView;
@synthesize imageSettingsView;
@synthesize lastColorPicked;
@synthesize imageDetailView, imageDetailViewImage, imageDetailViewLabel, removeImageButton;
@synthesize statusLabel;
@synthesize shiftSlider, shiftSliderLabel;

#pragma  mark - UI Update Methods
- (void)updatePreviewImageView
{
    self.previewImage.image = self.model.processedImage;
}


- (void)updateSourcePhotoView
{
    
    for (UIView *view in self.sourceImageView.subviews) {
        [view removeFromSuperview];
    }
    
    if ([[self.model sourceImageArray] count] == 0) {
        GradientView *gv = [[GradientView alloc] initWithFrame:self.sourceImageView.bounds];
        gv.backgroundColor = [UIColor grayColor];
        [self.sourceImageView addSubview:gv];
        [gv release];
        
        self.statusLabel.text = @"Click + to add some images.";
        
    } else {
        // the size of the source image view is the size of space needed to display all imgages,
        // or the width of the view area, whichever is larger
        CGSize theSize = CGSizeMake(THUMB_SIZE * [[self.model sourceImageArray] count] , THUMB_SIZE);
        if (theSize.width < self.sourceImageView.frame.size.width) {
            theSize.width = self.sourceImageView.frame.size.width;
        }
                
        CGRect theRect = CGRectMake(0.0f, 0.0f, theSize.width, theSize.height);
        GradientView *gv = [[GradientView alloc] initWithFrame:theRect];        
        gv.backgroundColor = [UIColor grayColor];
        [self.sourceImageView addSubview:gv];
        
        int i = 0;
        for (id image in [self.model sourceImageArray]) {
            UIButton *imageButton = [UIButton buttonWithType:UIButtonTypeCustom];            
            imageButton.frame = CGRectMake(i * THUMB_SIZE, 0, THUMB_SIZE, THUMB_SIZE);
            imageButton.tag = i;
            [imageButton addTarget:self 
                            action:@selector(showImageDetailView:) 
                  forControlEvents:UIControlEventTouchUpInside];
            
            // customize the look of the button to match the source image class
            if ([image isKindOfClass:[SourceImagePhoto class]]) {                
                [imageButton setImage:[image thumbnail] forState:UIControlStateNormal];                
            } else if([image isKindOfClass:[SourceImageColor class]]) {
                [[imageButton layer] setCornerRadius:8.0f];
                [[imageButton layer] setMasksToBounds:YES];
                [[imageButton layer] setBorderWidth:1.0f];                
                imageButton.backgroundColor = ((SourceImageColor *)image).color;
            }
            
            [gv addSubview:imageButton];
            i++;
        }
        [gv release];
        
        // set up the scroll view
        self.sourceImageView.contentSize = theSize;
        
        CGSize size = [self.model smallestSizeForFinalImage];
        if (size.width == MAXFLOAT || size.height == MAXFLOAT) {
            // do not change the status label yet
        } else {
            self.statusLabel.text = [NSString stringWithFormat:@"Final image will be %gx%g.", size.width, size.height];
        }
    }
}


// Determine correct display value for slider label.
// If < MAX_DISPLAYED_ROW_HEIGHT, display the value.
// If > MAX_DISPLAYED_ROW_HEIGHT and < MAX_ROW_HEIGHT, display MAX_ROW_HEIGHT.
// If == MAX_ROW_HEIGHT, display "Random"
- (NSString *)determineSliderLabelText
{
    NSString * text;
    if (self.rowHeight < MAX_DISPLAYED_ROW_HEIGHT) {
        text = [NSString stringWithFormat:@"%d", self.rowHeight];
    } else if (self.rowHeight == MAX_ROW_HEIGHT) {
        text = @"Random";
    } else {
        text = [NSString stringWithFormat:@"%d", MAX_DISPLAYED_ROW_HEIGHT];
    }   
    
    return text;
}

- (void)createImage
{
    if ([self.model.sourceImageArray count] == 0 ||
        ([self.model.sourceImageArray count] < 2 && self.shiftValue == 0) ) {
        // this means that there is not enough going on to do any processing, so 
        // we remove the preview image (if there is one)
        self.previewImage.image = nil;
        
    } else {
        self.model.processedImage = nil;
        [self updatePreviewImageView];
        [MBProgressHUD showHUDAddedTo:self.view animated:YES];
        self.statusLabel.text = @"Interlacing images...";
        //MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:self.view animated:YES];
        //hud.labelText = @"Processing";
        
        dispatch_async( dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
            if ([self.model.sourceImageArray count] > 0) {
                
                CGColorSpaceRef colorspace = CGColorSpaceCreateDeviceRGB();
                
                // create the full size image
                int count = [self.model.sourceImageArray count];
                int width = [self.model smallestSizeForFinalImage].width;
                int height = [self.model smallestSizeForFinalImage].height;
                int loops = height / count;
                height = loops * count;     // The height will be rounded to the nearest whole number of complete rows
                
                // create space for the new image
                unsigned char *newImageData = malloc(height * width * BYTES_PER_PIXEL);            
                
                int startRow = 0;
                for (id sourceImage in self.model.sourceImageArray) {
                    NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
                    
                    // if random row height selected, and this is the first image,
                    // copy the entire image. Otherwise, there will be missing parts
                    if (rowHeight == MAX_ROW_HEIGHT && startRow == 0) {
                        [sourceImage copyImageDataTo:newImageData
                                             fromRow:0
                                            skipRows:0
                                           withWidth:width
                                           andHeight:height
                                        andRowHeight:1
                                            andShift:self.shiftValue];
                    } else {
                        
                        [sourceImage copyImageDataTo:newImageData
                                             fromRow:startRow
                                            skipRows:(count - 1)
                                           withWidth:width
                                           andHeight:height
                                        andRowHeight:self.rowHeight
                                            andShift:self.shiftValue];
                    }
                    startRow++;
                    
                    [pool drain];
                }
                
                // write back into an image
                CGContextRef ctx = CGBitmapContextCreate(newImageData,
                                                         width,
                                                         height,
                                                         8,
                                                         BYTES_PER_PIXEL * width,
                                                         colorspace,
                                                         kCGImageAlphaPremultipliedLast ); 
                
                CGImageRef imageRef = CGBitmapContextCreateImage (ctx);
                UIImage* rawImage = [UIImage imageWithCGImage:imageRef];  
                
                CGImageRelease(imageRef);
                CGColorSpaceRelease(colorspace);
                CGContextRelease(ctx);  
                
                free(newImageData);
                
                self.model.processedImage = rawImage;
                
                
                // create preview size image from image
                /*
                self.model.previewImage = [rawImage resizedImageWithContentMode:UIViewContentModeScaleAspectFit
                                                                         bounds:CGSizeMake(PREVIEW_SIZE, PREVIEW_SIZE)
                                                           interpolationQuality:kCGInterpolationHigh];
                */
                // save if auto-save is enabled
                if ([[NSUserDefaults standardUserDefaults] boolForKey:kAutoSaveProcessedImage]) {
                    ALAssetsLibrary *al = [[ALAssetsLibrary alloc] init];
                    [al writeImageToSavedPhotosAlbum:[rawImage CGImage]
                                         orientation:ALAssetOrientationUp
                                     completionBlock:^(NSURL *assetURL, NSError *error) {
                                         if (error == nil) {
                                             self.statusLabel.text = @"Image has been saved.";
                                         } else {
                                             self.statusLabel.text = @"Error while saving image.";
                                             NSLog(@"%@", [error description]);
                                         }
                                     }];
                    
                    [al release];
                }             
            }
            
            
            dispatch_async( dispatch_get_main_queue(), ^{
                // Add code here to update the UI/send notifications based on the
                // results of the background processing
                [MBProgressHUD hideHUDForView:self.view animated:YES];
                [self updatePreviewImageView];
                self.statusLabel.text = @"";
            });
        });
    }
}


#pragma mark - methods to show and hide button views
- (IBAction)showAddButtons:(UIButton *)sender
{
    // create a view from xib
    if (self.subButtonView == nil) {
        [[NSBundle mainBundle] loadNibNamed:@"AddImageButtonView" 
                                      owner:self 
                                    options:nil];
    }
    
    // put it in front of the current buttons, but off screen
    CGRect offScreen = CGRectMake(-self.mainButtonView.frame.size.width, self.mainButtonView.frame.origin.y, self.mainButtonView.frame.size.width, self.mainButtonView.frame.size.height);
    self.subButtonView.frame = offScreen;
    [self.view addSubview:self.subButtonView];
    
    // animate it onto the screen
    [UIView animateWithDuration:0.3
                          delay:0.0
                        options:UIViewAnimationCurveEaseInOut
                     animations:^(void) {
                         self.subButtonView.frame = self.mainButtonView.frame;
                     } completion:^(BOOL finished) {
                         //
                     }];
}

- (IBAction)hideAddButtons:(UIButton *)sender
{
    CGRect offScreen = CGRectMake(-self.mainButtonView.frame.size.width, self.mainButtonView.frame.origin.y, self.mainButtonView.frame.size.width, self.mainButtonView.frame.size.height);
    [UIView animateWithDuration:0.3
                          delay:0.0
                        options:UIViewAnimationCurveEaseInOut
                     animations:^(void) {
                         self.subButtonView.frame = offScreen;
                     } completion:^(BOOL finished) {
                         [self.subButtonView removeFromSuperview];
                         self.subButtonView = nil;
                     }];
}

- (IBAction)showImageSettings:(UIButton *)sender
{
    // create a view from xib
    if (self.imageSettingsView == nil) {
        [[NSBundle mainBundle] loadNibNamed:@"ImageSettingsView" 
                                      owner:self 
                                    options:nil];
    }
    
    // set the value of the slider label and position of slider
    self.slider.minimumValue = MIN_ROW_HEIGHT;
    self.slider.maximumValue = MAX_ROW_HEIGHT;
    self.slider.value = self.rowHeight;
    self.sliderLabel.text = [self determineSliderLabelText];
    
    self.shiftSlider.minimumValue = MIN_SHIFT_VALUE;
    self.shiftSlider.maximumValue = MAX_SHIFT_VALUE;
    self.shiftSlider.value = self.shiftValue;
    self.shiftSliderLabel.text = [NSString stringWithFormat:@"%d", self.shiftValue];
    
    // put it in front of the current buttons, but off screen
    // the y origin is calculated as the main button origin minus (move up the screen)
    // the difference in height between the settings view and the main button view.
    CGRect offScreen = CGRectMake(-self.mainButtonView.frame.size.width, 
                                  self.mainButtonView.frame.origin.y - 
                                  (self.imageSettingsView.frame.size.height - self.mainButtonView.frame.size.height), 
                                  self.imageSettingsView.frame.size.width, 
                                  self.imageSettingsView.frame.size.height);
    CGRect onScreen = CGRectMake(0, 
                                 offScreen.origin.y,
                                 offScreen.size.width,
                                 offScreen.size.height);
    self.imageSettingsView.frame = offScreen;
    [self.view addSubview:self.imageSettingsView];
    
    // animate it onto the screen
    [UIView animateWithDuration:0.3
                          delay:0.0
                        options:UIViewAnimationCurveEaseInOut
                     animations:^(void) {
                         self.imageSettingsView.frame = onScreen;
                     } completion:^(BOOL finished) {
                         //
                     }];
}

- (IBAction)hideImageSettings:(UIButton *)sender
{
    // the y origin is calculated as the main button origin minus (move up the screen)
    // the difference in height between the settings view and the main button view.
    CGRect offScreen = CGRectMake(-self.mainButtonView.frame.size.width, 
                                  self.mainButtonView.frame.origin.y - 
                                  (self.imageSettingsView.frame.size.height - self.mainButtonView.frame.size.height), 
                                  self.imageSettingsView.frame.size.width, 
                                  self.imageSettingsView.frame.size.height);
    
    [UIView animateWithDuration:0.3
                          delay:0.0
                        options:UIViewAnimationCurveEaseInOut
                     animations:^(void) {
                         self.imageSettingsView.frame = offScreen;
                     } completion:^(BOOL finished) {
                         [self.imageSettingsView removeFromSuperview];
                         self.imageSettingsView = nil;
                         [self createImage];
                     }];
}

- (IBAction)resetSliderButtonClicked:(UIButton *) sender
{
    self.rowHeight = MIN_ROW_HEIGHT;
    self.slider.value = self.rowHeight;
    self.sliderLabel.text = [NSString stringWithFormat:@"%d", self.rowHeight];
    
    self.shiftValue = DEFAULT_SHIFT_VALUE;
    self.shiftSlider.value = self.shiftValue;
    self.shiftSliderLabel.text = [NSString stringWithFormat:@"%d", self.shiftValue];
}

- (IBAction)rowHeightSliderChanged:(UISlider *)sender
{
    if (sender.value > MAX_DISPLAYED_ROW_HEIGHT && sender.value < MAX_ROW_HEIGHT) {
        self.rowHeight = MAX_DISPLAYED_ROW_HEIGHT;
    } else {
        self.rowHeight = sender.value;
    }
    self.sliderLabel.text = [self determineSliderLabelText];
}

- (IBAction)shiftValueSliderChanged:(UISlider *)sender
{
    self.shiftValue = sender.value;
    self.shiftSliderLabel.text = [NSString stringWithFormat:@"%d", self.shiftValue];
}

- (IBAction)showImageDetailView:(UIButton *)sender
{
    // create a view from xib
    if (self.imageDetailView == nil) {
        [[NSBundle mainBundle] loadNibNamed:@"ImageDetailView" 
                                      owner:self 
                                    options:nil];
    }
    
    // set the image and label
    // the label content depends on the type of source image
    id<SourceImage> source = [self.model.sourceImageArray objectAtIndex:sender.tag];
    
    if ([source isKindOfClass:[SourceImagePhoto class]]) {
        self.imageDetailViewImage.image = ((SourceImagePhoto *)source).thumbnail;
        CGSize size = [((SourceImagePhoto *)source) imageSize];
        
        self.imageDetailViewLabel.text = [NSString stringWithFormat:@"Image size: %g x %g", size.width, size.height];
    } else if ([source isKindOfClass:[SourceImageColor class]]) {
        self.imageDetailViewImage.backgroundColor = ((SourceImageColor *)source).color;
        const CGFloat * components = CGColorGetComponents([((SourceImageColor *)source) color].CGColor);
        int red = 255 * components[0];
        int blue = 255 * components[1];
        int green = 255 * components[2];
        self.imageDetailViewLabel.text = [NSString stringWithFormat:@"Color RGB: %d, %d, %d", red, green, blue];
    }
         
    // set the delete button tag so we know which image is being referenced
    self.removeImageButton.tag = sender.tag;
    
    // put it in front of the current source image view, but off screen
    CGRect offScreen = CGRectMake(-self.sourceImageView.frame.size.width, self.sourceImageView.frame.origin.y, self.sourceImageView.frame.size.width, self.sourceImageView.frame.size.height);
    self.imageDetailView.frame = offScreen;
    [self.view addSubview:self.imageDetailView];
    // animate it onto the screen
    [UIView animateWithDuration:0.3
                          delay:0.0
                        options:UIViewAnimationCurveEaseInOut
                     animations:^(void) {
                         self.imageDetailView.frame = self.sourceImageView.frame;
                     } completion:^(BOOL finished) {
                         //
                     }];
    }

- (IBAction)hideImageDetailView:(UIButton *)sender
{
    CGRect offScreen = CGRectMake(-self.sourceImageView.frame.size.width, self.sourceImageView.frame.origin.y, self.sourceImageView.frame.size.width, self.sourceImageView.frame.size.height);
    [UIView animateWithDuration:0.3
                          delay:0.0
                        options:UIViewAnimationCurveEaseInOut
                     animations:^(void) {
                         self.imageDetailView.frame = offScreen;
                     } completion:^(BOOL finished) {
                         [self.imageDetailView removeFromSuperview];
                         self.imageDetailView = nil;
                         [self updateSourcePhotoView];
                         [self createImage];
                     }];
}


- (IBAction)deleteImageFromList:(UIButton *)sender
{
    [self.model.sourceImageArray removeObjectAtIndex:sender.tag];
}

#pragma mark - Image Capture Methods

- (IBAction)openCamera:(UIButton *)sender
{
    // make sure this device has a camera
    if ([UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypeCamera]) {
        UIImagePickerController *picker = [[[UIImagePickerController alloc] init] autorelease];
        picker.mediaTypes = [NSArray arrayWithObject:(NSString *)kUTTypeImage];
        picker.sourceType = UIImagePickerControllerSourceTypeCamera;
        picker.allowsEditing = NO;
        
        // set up the overlay if user wants it
        if ([[NSUserDefaults standardUserDefaults] boolForKey:@"ShowPhotoOverlay"]) {
            UIImage *overlayImage = [self.model imageForOverlay];
            if (overlayImage != nil) {
                
                
                CGRect rect = [[UIScreen mainScreen] bounds];
                UIImageView *overlay = [[UIImageView alloc] initWithFrame:rect];
                overlay.image = overlayImage;
                overlay.alpha = OVERLAY_ALPHA;
                picker.cameraOverlayView = overlay;
                
                
                [overlay release];                
            }
        }
        
        picker.delegate = self;
        [self presentModalViewController:picker
                                animated:YES];
        
    } else {
        [[[[UIAlertView alloc] initWithTitle:@"Camera Unavailable" 
                                     message:@"The camera is unavailable on this device. Add some images from your photo library." 
                                    delegate:nil 
                           cancelButtonTitle:@"OK" 
                           otherButtonTitles:nil] autorelease]show];
    }
}


- (IBAction)openAlbum:(UIButton *)sender
{
    UIImagePickerController *picker = [[[UIImagePickerController alloc] init] autorelease];
    picker.mediaTypes = [NSArray arrayWithObject:(NSString *)kUTTypeImage];
    picker.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
    picker.allowsEditing = NO;
    picker.delegate = self;
    
    [self presentModalViewController:picker animated:YES];
}

//
// Implement the UIPickerControllerDelegate protocol
//
- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info
{
    
    [picker dismissModalViewControllerAnimated:YES];
    
    //SourceImagePhoto *sip = [[SourceImagePhoto alloc] init];
    //[self.model addSourceImage:sip];
    
    //[self updateSourcePhotoView];
        
    self.statusLabel.text = @"Preparing image for use...";
    
    [MBProgressHUD showHUDAddedTo:self.view animated:YES];
    
    // now do the time consuming image operations in the background
    dispatch_async( dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        NSAutoreleasePool *pool = [[NSAutoreleasePool alloc] init];
        
        SourceImagePhoto *sip = [[SourceImagePhoto alloc] init];
        
        UIImage *tempImage = [info objectForKey:UIImagePickerControllerOriginalImage];
                
        // metadata will be nil if the image came from photo album
        NSDictionary *metadata = [info objectForKey:UIImagePickerControllerMediaMetadata];
        
        if (metadata != nil && [[NSUserDefaults standardUserDefaults] boolForKey:kSaveCameraPhotos]) {
            // save photo to camera roll
            ALAssetsLibrary *al = [[ALAssetsLibrary alloc] init];
            [al writeImageToSavedPhotosAlbum:[tempImage CGImage]
                                    metadata:metadata
                             completionBlock:nil];
             [al release];
        }
        
        // Use the UIImage+Resize category to resize the image to its current size.
        // This corrects image orientation.
        CGSize size = CGSizeMake(tempImage.size.width, tempImage.size.height);
        sip.image = [tempImage resizedImage:size 
                       interpolationQuality:kCGInterpolationHigh];
        
        // Make the thumbnail
        sip.thumbnail = [tempImage resizedImageWithContentMode:UIViewContentModeScaleAspectFit
                                                        bounds:CGSizeMake(THUMB_SIZE, THUMB_SIZE)
                                          interpolationQuality:kCGInterpolationMedium];
        
        [self.model addSourceImage:sip];
        [sip release];

        [pool drain];
        
        dispatch_async( dispatch_get_main_queue(), ^{
            [MBProgressHUD hideHUDForView:self.view animated:YES];
            [self updateSourcePhotoView];
            [self createImage];
        });
    });
}

- (void)imagePickerControllerDidCancel:(UIImagePickerController *)picker
{
    [picker dismissModalViewControllerAnimated:YES];
}
//
// END Implement the UIPickerControllerDelegate protocol
//



#pragma mark -

- (IBAction)selectColor:(UIButton *)sender
{
    // Make a new ColorPickerViewController with the interface defined in the referenced nib:
    ColorPickerViewController *colorPickerViewController = 
    [[ColorPickerViewController alloc] initWithNibName:@"ColorPickerViewController" bundle:nil];
    
    // The ColorPickerViewController needs a delegate to send the results back to.
    // Here, we'll use self, and implement (colorPickerViewController: didSelectColor:) below.
    colorPickerViewController.delegate = self;
    
    // The defaults key helps you keep track of the color we're supposed to be selecting
    if (self.lastColorPicked == nil) {
        colorPickerViewController.defaultsColor = [UIColor grayColor];
    } else {
        colorPickerViewController.defaultsColor = self.lastColorPicked;
    }
    
    // Slides the color picker view in place.
    [self presentModalViewController:colorPickerViewController animated:YES];
    [colorPickerViewController release];
}

- (void)colorPickerViewController:(ColorPickerViewController *)colorPicker didSelectColor:(UIColor *)color 
{
    [colorPicker dismissModalViewControllerAnimated:YES];
    self.lastColorPicked = color;
    
    SourceImageColor *sic = [[SourceImageColor alloc] initWithColor:color];
    [self.model addSourceImage:sic];
    [sic release];
    
    [self updateSourcePhotoView];
    [self createImage];         
}

- (int)rowHeight
{
    return rowHeight;
}

- (void)setRowHeight:(int)newRowHeight
{
    if (newRowHeight < MIN_ROW_HEIGHT) {
        rowHeight = MIN_ROW_HEIGHT;
    } else if (newRowHeight > MAX_ROW_HEIGHT) {
        rowHeight = MAX_ROW_HEIGHT;
    } else {
        rowHeight = newRowHeight;
    }
}

- (int)shiftValue
{
    return shiftValue;
}

- (void)setShiftValue:(int)newShiftValue
{
    if (newShiftValue < MIN_SHIFT_VALUE) {
        shiftValue = MIN_SHIFT_VALUE;
    } else if (newShiftValue > MAX_SHIFT_VALUE) {
        shiftValue = MAX_SHIFT_VALUE;
    } else {
        shiftValue = newShiftValue;
    }
}

    






- (void)showSettings
{
    IASKAppSettingsViewController *settings = [[IASKAppSettingsViewController alloc] initWithNibName:@"IASKAppSettingsView" bundle:nil];
    settings.showDoneButton = NO;
    [self.navigationController pushViewController:settings animated:YES];
    [settings release];
}


//
// Save the processed image.
// This will only save the image when there is an image to save.
- (IBAction)saveProcessedImage:(UIButton *)sender
{
    
    if (self.model.processedImage != nil) {
        MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:self.view animated:YES];
        hud.labelText = @"Saving";
        
        ALAssetsLibrary *al = [[ALAssetsLibrary alloc] init];
        [al writeImageToSavedPhotosAlbum:[self.model.processedImage CGImage]
                             orientation:ALAssetOrientationUp
                         completionBlock:^(NSURL *assetURL, NSError *error) {
                             [MBProgressHUD hideHUDForView:self.view animated:YES];
                             if (error == nil) {
                                 self.statusLabel.text = @"Image has been saved.";
                             } else {
                                 self.statusLabel.text = @"Error while saving image.";
                                 NSLog(@"%@", [error description]);
                             }
                         }];
        
        [al release];
    }
}

- (IBAction)showAboutViewController:(UIButton *)sender
{
    AboutViewController *vc = [[AboutViewController alloc] init];
    vc.modalTransitionStyle = UIModalTransitionStyleFlipHorizontal;
    [self presentModalViewController:vc animated:YES];

    [vc release];
}


- (IBAction)clear:(UIButton *)sender
{
    [self.model clear];
    [self updatePreviewImageView];
    [self updateSourcePhotoView];
}



- (void)releaseObjects
{
    self.previewImage = nil;
    self.sourceImageView = nil;
    self.slider = nil;
    self.sliderLabel = nil;
    self.subButtonView = nil;
    self.mainButtonView = nil;
    self.imageSettingsView = nil;
    self.lastColorPicked = nil;
    self.imageDetailView = nil;
    self.imageDetailViewImage = nil;
    self.imageDetailViewLabel = nil;
    self.removeImageButton = nil;
    self.statusLabel = nil;
    self.shiftSlider = nil;
    self.shiftSliderLabel = nil;
}

- (void)dealloc
{
    [self releaseObjects];
    self.model = nil;
    [super dealloc];
}

- (void)didReceiveMemoryWarning
{
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc that aren't in use.
}

#pragma mark - View lifecycle


// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad
{
    [super viewDidLoad];
    if (self.model == nil) {
        self.model = [[Model alloc] init];
        self.rowHeight = MIN_ROW_HEIGHT;
        self.shiftValue = DEFAULT_SHIFT_VALUE;
    }
    [self updateSourcePhotoView];
}



- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
    [self releaseObjects];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}



@end
