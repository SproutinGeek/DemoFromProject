package com.tml.demoframwork.listeners;

import android.support.annotation.UiThread;

import com.tml.demoframwork.dto.DetailManualDTO;
import com.tml.demoframwork.exception.ErrorBundle;


public interface DetailmanualDataListener {
    /**
     * Receives the call when call the details of the manual fetched from the DB
     *
     * @param detailManualDTO
     */
    @UiThread
    void databaseDetailManualDTOReceiver(DetailManualDTO detailManualDTO, ErrorBundle errorBundle);
}
