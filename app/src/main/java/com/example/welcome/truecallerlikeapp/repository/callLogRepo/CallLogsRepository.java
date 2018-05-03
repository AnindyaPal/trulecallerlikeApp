package com.example.welcome.truecallerlikeapp.repository.callLogRepo;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

import com.example.welcome.truecallerlikeapp.repository.models.CallLogModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CallLogsRepository {

    Context mContext;

    public CallLogsRepository(Context mContext){
        this.mContext = mContext;
    }
    public List<CallLogModel> getAllCallLogs() {

        List<CallLogModel> callLogModels = new ArrayList<>();
        Cursor managedCursor = mContext.getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);

        while (managedCursor.moveToNext()) {
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }

            CallLogModel callLogModel = new CallLogModel();
            callLogModel.setCallDayTime(getStartAsString(callDayTime));
            callLogModel.setDir(dir);
            callLogModel.setCallDuration(callDuration);
            callLogModel.setPhNumber(phNumber);
            callLogModels.add(callLogModel);

        }
        managedCursor.close();
        return callLogModels;

    }

    private String getStartAsString(Date start) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String reportDate = df.format(start);
        return reportDate;
    }
}
