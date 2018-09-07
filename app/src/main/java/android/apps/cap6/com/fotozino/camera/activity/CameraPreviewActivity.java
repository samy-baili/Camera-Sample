package android.apps.cap6.com.fotozino.camera.activity;

import android.apps.cap6.com.fotozino.R;
import android.apps.cap6.com.fotozino.utils.FileUtils;
import android.apps.cap6.com.fotozino.utils.ScreenUtils;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.wonderkiln.camerakit.CameraKitEventCallback;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraView;

import java.io.IOException;


public class CameraPreviewActivity extends AppCompatActivity {

    public final static String PICTURE_PATH = "picture_path";

    private CameraView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.camera_preview_activity);

        initActionBar();

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(R.string.camera_activity_toolbar_title);
    }

    private void initViews() {
        int screenSize = ScreenUtils.getScreenWidth(this);

        this.cameraView = findViewById(R.id.camera_view);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cameraView.getLayoutParams();
        params.width = screenSize;
        params.height = screenSize;
        cameraView.setLayoutParams(params);

        FloatingActionButton cameraCaptureButton = findViewById(R.id.camera_capture_button);
        cameraCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraView.captureImage(new CameraKitEventCallback<CameraKitImage>() {
                    @Override
                    public void callback(CameraKitImage cameraKitImage) {
                        savePreviewInFile(cameraKitImage.getJpeg());
                    }
                });
            }
        });
    }

    private void savePreviewInFile(byte[] captureByte) {
        try {
            FileUtils.byteArrayToFile(this, PICTURE_PATH, captureByte);
            launchCaptureActivity();
        }
        catch (IOException e) {e.printStackTrace();}
    }

    private void launchCaptureActivity() {
        startActivity(new Intent(this, CapturePreviewActivity.class));
    }

}
