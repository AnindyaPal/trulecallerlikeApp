package com.example.welcome.truecallerlikeapp.callReceivers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.example.welcome.truecallerlikeapp.views.CallLogActivity;
import com.example.welcome.truecallerlikeapp.views.OverlayDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayOverlayReceiver extends PhoneCallReceiver {
    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
        Intent launch_intent = new  Intent(ctx.getApplicationContext(), OverlayDialog.class);
        //launch_intent.setClassName("com.example.welcome.truecallerlikeapp.views","com.example.welcome.truecallerlikeapp.views.OverlayDialog");
        launch_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        launch_intent.putExtra("number", number);
        launch_intent.putExtra("date", getStartAsString(start));
        //launch_intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ctx.startActivity(launch_intent);
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        Intent launch_intent = new  Intent(ctx.getApplicationContext(), OverlayDialog.class);
        //launch_intent.setClassName("com.example.welcome.truecallerlikeapp.views","com.example.welcome.truecallerlikeapp.views.OverlayDialog");
        launch_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        launch_intent.putExtra("number", number);
        launch_intent.putExtra("date", getStartAsString(start));
        //launch_intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ctx.startActivity(launch_intent);
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        Intent launch_intent = new  Intent(ctx.getApplicationContext(), OverlayDialog.class);
        //launch_intent.setClassName("com.example.welcome.truecallerlikeapp.views","com.example.welcome.truecallerlikeapp.views.OverlayDialog");
        launch_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        launch_intent.putExtra("number", number);
        launch_intent.putExtra("date", getStartAsString(start));
        //launch_intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ctx.startActivity(launch_intent);
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
        Intent launch_intent = new  Intent(ctx.getApplicationContext(), OverlayDialog.class);
        //launch_intent.setClassName("com.example.welcome.truecallerlikeapp.views","com.example.welcome.truecallerlikeapp.views.OverlayDialog");
        launch_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        launch_intent.putExtra("number", number);
        launch_intent.putExtra("date", getStartAsString(start));
        //launch_intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ctx.startActivity(launch_intent);
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
        Intent launch_intent = new  Intent(ctx.getApplicationContext(), OverlayDialog.class);
        //launch_intent.setClassName("com.example.welcome.truecallerlikeapp.views","com.example.welcome.truecallerlikeapp.views.OverlayDialog");
        launch_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        launch_intent.putExtra("number", number);
        launch_intent.putExtra("date", getStartAsString(start));
        //launch_intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ctx.startActivity(launch_intent);
    }

    private String getStartAsString(Date start) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String reportDate = df.format(start);
        return reportDate;
    }
}
