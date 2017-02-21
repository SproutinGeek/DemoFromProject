package com.tml.demo.exception;

import android.content.Context;


import com.tml.demo.R;
import com.tml.demo.utils.DemoUtitities;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by 1021354 on 30-06-2015.
 * Factory used to create error messages from an Exception as a condition.
 */
public class ErrorMessageFactory {

    private ErrorMessageFactory() {
        //empty
    }

    /**
     * Creates a String representing an error message.
     *
     * @param context   Context needed to retrieve string resources.
     * @param exception An exception used as a condition to retrieve the correct error message.
     * @return {@link String} an error message.
     */
    public static String create(Context context, Exception exception) {
        exception.printStackTrace();
        String message = context.getString(R.string.exception_message_generic);
        if (exception instanceof XmlPullParserException) {
            message = context.getString(R.string.exception_message_xml_tag_parsing);
        }if (exception instanceof JSONException) {
            message = context.getString(R.string.exception_message_xml_tag_parsing);
        } else if (exception instanceof IOException) {
            message = context.getString(R.string.exception_message_xml_file_not_found);
        }
        //DemoUtitities.registerCrashes(context, exception);
        return message;
    }
}
