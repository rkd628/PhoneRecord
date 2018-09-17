package com.whatchasay.phonerecord;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import static android.content.Intent.ACTION_POWER_CONNECTED;
import static android.content.Intent.ACTION_POWER_DISCONNECTED;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    private int mJobId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

//        PowerConnectionReceiver mPowerConncetionRecevier = new PowerConnectionReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(ACTION_POWER_CONNECTED);
//        intentFilter.addAction(ACTION_POWER_DISCONNECTED);
//        getApplication().registerReceiver(mPowerConncetionRecevier,intentFilter);

        File newxmlfile = new File(getFilesDir(),"temp.xml");
        Log.d("seokil",getFilesDir().toString());
        try {
            newxmlfile.createNewFile();
        } catch (IOException e) {
            Log.e("IOException", "Exception in create new File(");
        }
        FileOutputStream fileos = null;
        try {
            fileos = new FileOutputStream(newxmlfile);
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", e.toString());
        }
        XmlSerializer serializer = Xml.newSerializer();
        try {
            serializer.setOutput(fileos, "UTF-8");
            serializer.startDocument(null, Boolean.valueOf(true));
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            serializer.startTag(null, "root");
            serializer.startTag(null, "Child1");
            serializer.endTag(null, "Child1");
            serializer.startTag(null, "Child2");
            serializer.attribute(null, "attribute", "value");
            serializer.endTag(null, "Child2");
            serializer.startTag(null, "Child3");
            serializer.text("Some text inside child 3");
            serializer.endTag(null, "Child3");
            serializer.endTag(null, "root");
            serializer.endDocument();
            serializer.flush();
            fileos.close();
            //TextView tv = (TextView)findViewById(R.);

        } catch (Exception e) {
            Log.e("Exception", "Exception occured in wroting");
        }

    }

    public void temp(View v) {

        JobInfo.Builder builder = new JobInfo.Builder(mJobId++, new ComponentName(this, MyJobService.class));
        builder.setRequiresCharging(true);
        builder.setPersisted(true);
        builder.setOverrideDeadline(3000);
        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.schedule(builder.build());

        return;
    }

//    private String writeXml(List<Message> messages) {
//        XmlSerializer serializer = Xml.newSerializer();
//        StringWriter writer = new StringWriter();
//        try {
//            serializer.setOutput(writer);
//            serializer.startDocument("UTF-8", true);
//            serializer.startTag("", "messages");
//            serializer.attribute("", "number", String.valueOf(messages.size()));
//            for (Message msg : messages) {
//                serializer.startTag("", "message");
//                serializer.attribute("", "date", msg.getDate());
//                serializer.startTag("", "title");
//                serializer.text(msg.getTitle());
//                serializer.endTag("", "title");
//                serializer.startTag("", "url");
//                serializer.text(msg.getLink().toExternalForm());
//                serializer.endTag("", "url");
//                serializer.startTag("", "body");
//                serializer.text(msg.getDescription());
//                serializer.endTag("", "body");
//                serializer.endTag("", "message");
//            }
//            serializer.endTag("", "messages");
//            serializer.endDocument();
//            return writer.toString();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

}
