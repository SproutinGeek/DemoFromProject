package com.tml.demoframwork.utils;

/**
 * Created by 1021354 on 22-06-2015.
 * For keeping the constants .
 */
public class FrameworkConstants {
    public static final int DB_CONTENT_TYPE_VEHICLE_MANUAL = 1;
    public static final int DB_CONTENT_TYPE_IVI_MANUAL = 2;
    public static final int DB_CONTENT_TYPE_QUICK_GUIDE = 3;
    public static final int DB_CONTENT_TYPE_MAINTENANCE_TIP = 4;
    public static final int CONTENT_TYPE_SAFETY_ID = 5;
    public static final String DB_RECENT_SEARCH_LIMIT = "6";

    //Data from XML
    public static final String CONTENT_TYPE_SAFETY_VALUE = "SAFETY";
    public static final String CONTENT_MODE_ADD = "ADD";
    public static final String CONTENT_MODE_UPDATE = "UPDATE";
    public static final String CONTENT_MODE_DELETE = "DELETE";

    /*Constant Strings*/
    public static final String DB_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DB_DATE_FORMAT = "yyyy-MM-dd";


    /*Constant XML TAGS*/
    public static final String XML_CONTENT_TAG_DATA_SET = "data-set";
    public static final String XML_CONTENT_TAG_RECORD = "record";
    public static final String XML_CONTENT_TAG_CONTENT_ID = "ContentID";
    public static final String XML_CONTENT_TAG_PRIMARY_CATOGARY = "PrimaryContentCategory";
    public static final String XML_CONTENT_TAG_MAINTENENCE_TIPS = "MaintenanceTips";
    public static final String XML_CONTENT_TAG_FEATURE = "Feature";
    public static final String XML_CONTENT_TAG_TITLE = "Title";
    public static final String XML_CONTENT_TAG_INTRODUCTION = "Introduction";
    public static final String XML_CONTENT_TAG_SHORT_DESCRIPTION = "ShortDescription";
    public static final String XML_CONTENT_TAG_PHOTO = "Photo";
    public static final String XML_CONTENT_TAG_IMAGE_FILE_NAME = "FileName";
    public static final String XML_CONTENT_TAG_MODE = "mode";
    public static final String XML_CONTENT_TAG_KEYWORDS = "Keywords";
    /*Conatants JSon tags*/
    public static final String JSON_CONTENT_TAG_FEATURE = "Feature";
    public static final String JSON_CONTENT_TAG_SUBTITLE = "Sub-Title";
    public static final String JSON_CONTENT_TAG_SUBTITLE_NAME = "Sub-title-value";
    public static final String JSON_CONTENT_TAG_DESCRIPTION = "Description";
    public static final String JSON_CONTENT_TAG_TITLE_VALUE = "Title-Value";
    public static final String JSON_CONTENT_TAG_IMAGE = "Image";
    public static final String JSON_CONTENT_TAG_IMAGE_PATH = "Image-Path";
    public static final String JSON_CONTENT_TAG_IMAGE_CAPTION = "Image-Caption";
    public static final String JSON_CONTENT_TAG_NOTE = "Note";
    public static final String JSON_CONTENT_TAG_WARNING = "Warning";
    public static final String JSON_CONTENT_TAG_KEYWORDS = "Keywords";
    public static final String JSON_CONTENT_TAG_TITLE = "Title";
    public static final String JSON_CONTENT_TAG_VIDEO = "Video";
    public static final String JSON_CONTENT_TAG_VIDEO_NAME = "VideoName";
    public static final String JSON_CONTENT_TAG_VIDEO_URL = "URL";
    public static final String JSON_CONTENT_TAG_THUMBNAIL_NAME = "Image-Thumbnail";
    public static final String JSON_CONTENT_TAG_USEFUL_TIPS = "UsefulTips";
    public static final String JSON_CONTENT_TAG_TIP_CATEGORY = "TipCategory";
    public static final String JSON_CONTENT_TAG_TIPS = "Tips";
    public static final String JSON_CONTENT_TAG_TIP = "Tip";

    // Infobites tags
    public static final String VEHICLE_INFOBITES_JSON_FILE = "json/InfoBites/VehicleInfoBites.json";
    public static final String JSON_CONTENT_TAG_VEHICLE_INFO_BITES = "VehicleInfoBites";
    public static final String JSON_CONTENT_TAG_INFO_BITE = "InfoBite";


    //XML file paths
    public static final String IVI_JSON_FILES_PATH = "json/IVI_Manual";
    public static final String TIPS_JSON_FILES_PATH = "json/Tips";
    public static final String VEHICLE_MANUAL_FILES_PATH = "json/Vehicle_Manual";
    public static final String THUMBNAIL_IMAGE_FILES_FOLDER = "images/Thumbmails/";
    public static final String ACTUAL_IMAGE_FILES_FOLDER = "images/Content_Images/";
    public static final String VISUAL_GUIDE_FILES_PATH ="json/Visual_Guide";
    public static final String VISUAL_GUIDE_IMAGES_PATH = "images/Visual_Guide";
    public static final String TELLTALE_FILES_PATH = "json/Telltale";
    public static final String TELLTALE_IMAGES_PATH = "images/Telltale";

    public static final int ID_WARNINGS_AND_INDICATORS = 0;
    public static final String TELLTALE_CONTENT_FILE_NAME = "telltale_information.json";
    public static final String COCKPIT_INFORMATION_FILE_NAME = "cockpit_information.json";
    public static final String INFOTAINMENT_INFORMATION_FILE_NAME = "infotainment_information.json";
    public static final String STORAGE_INFORMATION_FILE_NAME = "storage_information.json";
    public static final String INSTRUMENT_CLUSTER_INFORMATION_FILE_NAME = "instrument_cluster_information.json";

    // Session Variables
    public static String[] SELECTED_CATEGORIES;

}
