package com.example.finalprojectcis385;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class NewRecord extends AppCompatActivity {

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final int NOTIFICATION_ID = 0;
    private static final String TAG = "Values";
    private NotificationManager mNotifyManager;

    private Spinner spinner;
    private EditText weight;
    private EditText reps;
    private AppDatabaseReps dbReps;
    private RecordDAO recordDA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise2);

        // Play with data given via Intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_RECORD);
        String intentPassing = message + "madness.";
        Log.v(TAG, intentPassing);

        // Get values from the page
        spinner = (Spinner) findViewById(R.id.exerciseSelectSpinner);
        weight = (EditText) findViewById(R.id.InputWeightValue);
        reps = (EditText) findViewById(R.id.InputRepRecordValue);

        // Get DB
        dbReps = Room.databaseBuilder(getApplicationContext(),
                AppDatabaseReps.class, "Reps").allowMainThreadQueries().build();
        recordDA = dbReps.recordDAO();

        // Populate spinner with static array
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercises_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        createNotificationChannel();

    }


    public void LogRepRecord(View view) {

        //Here is where I actually insert the values into the DB

        if(Integer.parseInt(weight.getText().toString()) != 0 && Integer.parseInt(reps.getText().toString()) != 0){
            recordDA.InsertNewRecord(spinner.getSelectedItem().toString(), Integer.parseInt(weight.getText().toString()), Integer.parseInt(reps.getText().toString()) );
            Log.v(TAG, "Success???");
            sendNotification();
        }

    }

    public void createNotificationChannel()
    {
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            mNotifyManager.createNotificationChannel(notificationChannel);

        }
    }

    public void sendNotification() {
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }

    private NotificationCompat.Builder getNotificationBuilder(){
        Intent notificationIntent = new Intent(this, NewRecord.class);

        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Congratulations!")
                .setContentText("Your new PR has been saved!")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);
        return notifyBuilder;
    }
}