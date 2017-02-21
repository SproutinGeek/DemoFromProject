package com.tml.demoframwork;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.tml.demoframwork.common.BaseBL;
import com.tml.demoframwork.dto.ContentCategoryDTO;
import com.tml.demoframwork.dto.DetailManualDTO;
import com.tml.demoframwork.dto.ImageDTO;
import com.tml.demoframwork.dto.SubTitleDTO;
import com.tml.demoframwork.dto.TitleDTO;
import com.tml.demoframwork.exception.DefaultErrorBundle;
import com.tml.demoframwork.exception.ErrorBundle;
import com.tml.demoframwork.listeners.DataAccessInterface;
import com.tml.demoframwork.listeners.DetailmanualDataListener;
import com.tml.demoframwork.task.DataAccessAsyncTask;
import com.tml.demoframwork.utils.FrameworkConstants;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Quick guide Business logic
 * Created by 1021354 on 21-07-2015.
 */
public class QuickGuideBL extends BaseBL {

    /**
     * Constructor of QuickGuideBL
     *
     * @param mContext
     */
    public QuickGuideBL(@NonNull Context mContext) {
        super(mContext);
    }


    /**
     * Get the details of one entry with ID */
    public void getDetailManualDetailsByID(final int contentID, final DetailmanualDataListener detailmanualDataListener){
        dataAccessAsyncTask = new DataAccessAsyncTask(mContext, new DataAccessInterface() {

            DetailManualDTO detailManualDTO = null;
            @Override
            public void callDataAccess() throws JSONException {
                detailManualDTO = new JsonParserBL(mContext).getObjectFromJsonFile(FrameworkConstants.VEHICLE_MANUAL_FILES_PATH
                        + "/" + "1.json");
            }

            @Override
            public void dataAccessCallBack(Exception exception) {
                ErrorBundle errorBundle = null;
                if(exception != null){
                    errorBundle = new DefaultErrorBundle(exception);
                }
                detailmanualDataListener.databaseDetailManualDTOReceiver(detailManualDTO, errorBundle);
            }
        });

        dataAccessAsyncTask.execute();

    }

    /**
     * Json parser Business logic.
     * Created by 1021354 on 03-08-2015.
     */
    public static class JsonParserBL extends BaseBL {
        /**
         * Context is the one mandatory thing for creating the bl implementations
         *
         * @param mContext for getting the context
         */
        public JsonParserBL(@NonNull Context mContext) {
            super(mContext);
        }

        /**
         * Get Json object from filename
         *
         * @param fileName
         * @return
         * @throws JSONException
         */
        public JSONObject getJsonFromFile(@NonNull String fileName) throws JSONException {
            InputStream is = null;
            JSONObject jsonObject = null;
            try {
                is = mContext.getAssets().open(fileName);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                // Convert the buffer into a string.
                String content = new String(buffer);
                jsonObject = new JSONObject(content);
            } catch (IOException e) {
                e.printStackTrace();
                //Toast.makeText(mContext, "ERR: " + e.getMessage() + "CLASS : " + e.getClass(), Toast.LENGTH_LONG).show();
            }
            return jsonObject;
        }


        /**
         * Parse and get {@link DetailManualDTO} from using {@link AssetManager} assets folder
         *
         * @param fileName {@link String }
         * @return (@link DetailManualDTO)
         * @throws JSONException
         */
        public DetailManualDTO getObjectFromJsonFile(@NonNull String fileName) throws JSONException {
            JSONObject jsonObject = getJsonFromFile(fileName);
            DetailManualDTO detailManualDTO = new DetailManualDTO();
            try {
                detailManualDTO.setContent_Id(TextUtils.isEmpty(jsonObject.getString(FrameworkConstants
                        .XML_CONTENT_TAG_CONTENT_ID)) ? 0 : Integer.valueOf(jsonObject
                        .getString(FrameworkConstants.XML_CONTENT_TAG_CONTENT_ID).trim()));

                detailManualDTO.setContentCategoryDTO(new ContentCategoryDTO(TextUtils.isEmpty(jsonObject
                        .getString(FrameworkConstants.XML_CONTENT_TAG_PRIMARY_CATOGARY)) ? "" : jsonObject
                        .getString(FrameworkConstants.XML_CONTENT_TAG_PRIMARY_CATOGARY).trim()));

                if (jsonObject.has(FrameworkConstants.JSON_CONTENT_TAG_FEATURE)) {
                    detailManualDTO.setContentHeading(jsonObject.getString(FrameworkConstants.JSON_CONTENT_TAG_FEATURE));
                }
            } catch (JSONException e) {
                throw new JSONException("Content_Category_Empty");
            }
            if (jsonObject.has(FrameworkConstants.XML_CONTENT_TAG_SHORT_DESCRIPTION)) {
                detailManualDTO.setContentShortDescription(jsonObject.getString(FrameworkConstants.XML_CONTENT_TAG_SHORT_DESCRIPTION).replace("\\r\\n", ""));
            }
            if (jsonObject.has(FrameworkConstants.JSON_CONTENT_TAG_TITLE)) {
                try {
                    JSONArray titleJsonArray = null;
                    titleJsonArray = jsonObject.getJSONArray(FrameworkConstants.JSON_CONTENT_TAG_TITLE);
                    detailManualDTO.setTitleDTOs(parseTitleFromJson(titleJsonArray));
                } catch (JSONException e) {

                }
            }
            if (jsonObject.has(FrameworkConstants.JSON_CONTENT_TAG_THUMBNAIL_NAME)) {
                detailManualDTO.setImageThumbnailPath(FrameworkConstants.THUMBNAIL_IMAGE_FILES_FOLDER + jsonObject.getString(FrameworkConstants.JSON_CONTENT_TAG_THUMBNAIL_NAME));
            }

            return detailManualDTO;
        }

        /**
         * Parsing the Image from the Json.
         *
         * @param imageJson
         * @return
         * @throws JSONException
         */
        private ImageDTO imageParsingFromJson(@NonNull JSONObject imageJson) throws JSONException {
            ImageDTO imageDTO = new ImageDTO();
            if (imageJson.has(FrameworkConstants.JSON_CONTENT_TAG_IMAGE_PATH)) {
                imageDTO.setFilePath(imageJson.getString(FrameworkConstants.JSON_CONTENT_TAG_IMAGE_PATH));
            }
            if (imageJson.has(FrameworkConstants.JSON_CONTENT_TAG_DESCRIPTION)) {
                imageDTO.setImageDescription(imageJson.getString(FrameworkConstants.JSON_CONTENT_TAG_DESCRIPTION));
            }
            if (imageJson.has(FrameworkConstants.JSON_CONTENT_TAG_IMAGE_CAPTION)) {
                imageDTO.setImageCaption(imageJson.getString(FrameworkConstants.JSON_CONTENT_TAG_IMAGE_CAPTION));
            }
            if (imageJson.has(FrameworkConstants.JSON_CONTENT_TAG_NOTE)) {
                imageDTO.setNotes(imageJson.getString(FrameworkConstants.JSON_CONTENT_TAG_NOTE));
            }
            if (imageJson.has(FrameworkConstants.JSON_CONTENT_TAG_WARNING)) {
                imageDTO.setWarnings(imageJson.getString(FrameworkConstants.JSON_CONTENT_TAG_WARNING));
            }
            if (imageJson.has(FrameworkConstants.XML_CONTENT_TAG_MAINTENENCE_TIPS)) {
                imageDTO.setMaintenanceTips(imageJson.getString(FrameworkConstants.XML_CONTENT_TAG_MAINTENENCE_TIPS));
            }
            return imageDTO;
        }

        /**
         * Parse all the subtitles in the Json
         *
         * @param subTitleJsonArray
         * @return list of Subtitles
         * @throws JSONException
         */
        private List<SubTitleDTO> parseAllSubTitlesInJson(@Nullable JSONArray subTitleJsonArray) throws JSONException {
            List<SubTitleDTO> subTitleDTOs = new ArrayList<>();
            if (subTitleJsonArray != null) {
                SubTitleDTO subTitleDTO = null;
                for (int i = 0; i < subTitleJsonArray.length(); i++) {
                    JSONObject subTitleJson = subTitleJsonArray.getJSONObject(i);
                    subTitleDTO = new SubTitleDTO();
                    if (subTitleJson.has(FrameworkConstants.JSON_CONTENT_TAG_IMAGE)) {
                        ImageDTO imageDTO = null;
                        JSONObject imageJson = null;
                        try {
                            imageJson = subTitleJson.getJSONObject(FrameworkConstants.JSON_CONTENT_TAG_IMAGE);
                            if (imageJson != null) {
                                imageDTO = imageParsingFromJson(imageJson);
                                subTitleDTO.setContentImage(imageDTO);
                            }
                        } catch (JSONException e) {
                        }
                    }

                    if (subTitleJson.has(FrameworkConstants.JSON_CONTENT_TAG_SUBTITLE_NAME)) {
                        subTitleDTO.setTitleValue(subTitleJson.getString(FrameworkConstants.JSON_CONTENT_TAG_SUBTITLE_NAME));
                    }
                    subTitleDTOs.add(subTitleDTO);
                }
            }
            return subTitleDTOs;
        }

        private List<TitleDTO> parseTitleFromJson(@NonNull JSONArray titleJsonArray) throws JSONException {
            List<TitleDTO> titleDTOs = new ArrayList<>();
            if (titleJsonArray != null) {
                TitleDTO titleDTO = null;
                for (int i = 0; i < titleJsonArray.length(); i++) {
                    titleDTO = new TitleDTO();
                    JSONObject titleJson = titleJsonArray.getJSONObject(i);
                    if (titleJson.has(FrameworkConstants.JSON_CONTENT_TAG_TITLE_VALUE)) {
                        titleDTO.setTitleValue(titleJson.getString(FrameworkConstants.JSON_CONTENT_TAG_TITLE_VALUE));
                    }
                    if (titleJson.has(FrameworkConstants.XML_CONTENT_TAG_INTRODUCTION)) {
                        titleDTO.setIntroduction(titleJson.getString(FrameworkConstants.XML_CONTENT_TAG_INTRODUCTION));
                    }
                    if (titleJson.has(FrameworkConstants.JSON_CONTENT_TAG_SUBTITLE)) {
                        try {
                            JSONArray subTitleJsonArray = null;
                            subTitleJsonArray = titleJson.getJSONArray(FrameworkConstants.JSON_CONTENT_TAG_SUBTITLE);
                            if (subTitleJsonArray != null) {
                                titleDTO.setSubTitleDTOs(parseAllSubTitlesInJson(subTitleJsonArray));
                            }
                        } catch (JSONException e) {

                        }
                    }
                    titleDTOs.add(titleDTO);
                }
            }
            return titleDTOs;
        }



        /**
         * Getting the list of XML files in the folder
         *
         * @return list of file names present in assets folder
         * @throws IOException to get all the XML file names from assets folder
         */
        public String[] getXmlFileNamesList(@NonNull String folderName) throws IOException {
            Resources resources = mContext.getResources();
            AssetManager assetManager = resources.getAssets();
            String fileList[] = assetManager.list(folderName);
            return fileList;
        }
    }


}
