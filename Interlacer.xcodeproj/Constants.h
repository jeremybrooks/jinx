//
//  Constants.h
//  Interlacer
//
//  Created by Jeremy Brooks on 3/31/11.
//  Copyright 2011 Jeremy Brooks. All rights reserved.
//

/*
 TODO
 Add grid overlay option
 
 
 */

#define THUMB_SIZE 75.0
#define PREVIEW_SIZE 265.0

#define BYTES_PER_PIXEL 4

#define OVERLAY_ALPHA 0.5f

// MAX_DISPLAYED_ROW_HEIGHT is the maximum allowed row height
// MAX_ROW_HEIGHT triggers Random mode
// MAX_ROW_HEIGHT should be larger than MAX_DISPLAYED_ROW_HEIGHT so that
// there is a perceived dead spot before switching to random
#define MIN_ROW_HEIGHT 1
#define MAX_DISPLAYED_ROW_HEIGHT 15
#define MAX_ROW_HEIGHT 17


#define DEFAULT_SHIFT_VALUE 0
#define MIN_SHIFT_VALUE -50
#define MAX_SHIFT_VALUE 50

// constants for application settings keys
#define kShowPhotoOverlay @"ShowPhotoOverlay"
#define kSaveCameraPhotos @"SaveCameraPhotos"
#define kAutoSaveProcessedImage @"AutoSaveProcessedImage"

