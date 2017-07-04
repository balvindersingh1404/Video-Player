package com.example.balvinder.videoplayer;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends Activity {

    ProgressDialog pDialog;
    VideoView videoview;

    String VideoURL ="rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        videoview = (VideoView) findViewById(R.id.VideoView);

        pDialog = new ProgressDialog(VideoViewActivity.this);
        pDialog.setTitle("Video Streaming ");
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        try {
            MediaController mediacontroller = new MediaController(
                    VideoViewActivity.this);
            mediacontroller.setAnchorView(videoview);
            Uri video = Uri.parse(VideoURL);
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoview.requestFocus();
        videoview.setOnPreparedListener(new OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoview.start();
            }
        });

    }

}