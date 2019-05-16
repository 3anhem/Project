package vn.edu.vtn.quanlybanhang.util.fcm;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFireBaseMessagingService extends FirebaseMessagingService {
    NotificationHelper helper;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
//        if (remoteMessage.getNotification() != null) { // Có nghĩa là gửi từ Server của Google
//            toProcessShowMess(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
//            return;
//        }
        toProcessShowMess(remoteMessage.getData().get("body"), remoteMessage.getData().get("title"), remoteMessage.getData().get("image"));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void toProcessShowMess(String body, String title, String urlImage) {
        Log.d("AAAA", "Check MyFireBaseMessagingService " + body + title + urlImage);
        //    helper = new NotificationHelper(this);
//////        Notification.Builder builder = helper.getEDMTChannelNotification(title, body);
//////        helper.getManager().notify(new Random().nextInt(), builder.build());
//        helper.showNotification(this, body, );

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "FCM")
//                .setSmallIcon(R.drawable.avatar_default)
//                .setContentTitle(title)
//                .setContentText(body)
//                .setAutoCancel(true);
//
//        String CHANNEL_ID = "my_channel_01";// The id of the channel.
//        //  CharSequence name = getString(R.string.channel_name);// The user-visible name of the channel.
//        int importance = 0;
//        NotificationChannel mChannel = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            importance = NotificationManager.IMPORTANCE_HIGH;
//             mChannel = new NotificationChannel(CHANNEL_ID, "VTN", importance);
//        }
//
//
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//        builder.setContentIntent(pendingIntent);
//
//        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        builder.setSound(uri);
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            notificationManager.createNotificationChannel(mChannel);
//        }
//        int idRandom = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE); // Multiple Notification
//        notificationManager.notify(idRandom, builder.build());
        NotificationHelper.setNotification(this, body, title, urlImage);
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void toProcessShowMess(String body) {
//        toProcessShowMess(body, "From Google !!!");
//    }
}
