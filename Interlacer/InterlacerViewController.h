//
//  InterlacerViewController.h
//  Interlacer
//
//  Created by Jeremy Brooks on 3/31/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Model.h"
#import "ColorPickerViewController.h"


@interface InterlacerViewController : UIViewController <UIImagePickerControllerDelegate, UINavigationControllerDelegate,
    ColorPickerViewControllerDelegate> 
{
    @private
    int rowHeight;
    int shiftValue;
    Model *model;
    
}

@property (nonatomic, assign) int rowHeight;
@property (nonatomic, assign) int shiftValue;

@property (nonatomic, retain) IBOutlet UIImageView *previewImage;
@property (nonatomic, retain) IBOutlet UIScrollView *sourceImageView;
@property (nonatomic, retain) IBOutlet UISlider *slider;
@property (nonatomic, retain) IBOutlet UILabel *sliderLabel;
@property (nonatomic, retain) Model *model;
@property (nonatomic, retain) IBOutlet UIView *subButtonView;
@property (nonatomic, retain) IBOutlet UIView *mainButtonView;
@property (nonatomic, retain) IBOutlet UIView *imageSettingsView;
@property (nonatomic, retain) IBOutlet UIView *imageDetailView;
@property (nonatomic, retain) IBOutlet UILabel *statusLabel;

// for the shift slider
@property (nonatomic, retain) IBOutlet UISlider *shiftSlider;
@property (nonatomic, retain) IBOutlet UILabel *shiftSliderLabel;

// for the image detail view
@property (nonatomic, retain) IBOutlet UIImageView *imageDetailViewImage;
@property (nonatomic, retain) IBOutlet UILabel *imageDetailViewLabel;
@property (nonatomic, retain) IBOutlet UIButton *removeImageButton;


@property (nonatomic, retain) UIColor *lastColorPicked;

- (IBAction)showAddButtons:(UIButton *)sender;
- (IBAction)hideAddButtons:(UIButton *)sender;

// actions to handle settings view
- (IBAction)showImageSettings:(UIButton *)sender;
- (IBAction)hideImageSettings:(UIButton *)sender;
- (IBAction)rowHeightSliderChanged:(UISlider *)sender;
- (IBAction)shiftValueSliderChanged:(UISlider *)sender;
- (IBAction)resetSliderButtonClicked:(UIButton *) sender;

// Actions for the add button view
- (IBAction)openCamera:(UIButton *)sender;
- (IBAction)openAlbum:(UIButton *)sender;
- (IBAction)selectColor:(UIButton *)sender;

// actions to handle image detail view
- (IBAction)showImageDetailView:(UIButton *)sender;
- (IBAction)hideImageDetailView:(UIButton *)sender;
- (IBAction)deleteImageFromList:(UIButton *)sender;


- (IBAction)saveProcessedImage:(UIButton *)sender;
- (IBAction)clear:(UIButton *)sender;


@end
