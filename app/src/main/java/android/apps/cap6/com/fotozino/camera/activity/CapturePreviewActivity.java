package android.apps.cap6.com.fotozino.camera.activity;

import android.apps.cap6.com.fotozino.R;
import android.apps.cap6.com.fotozino.utils.ScreenUtils;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class CapturePreviewActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_preview_activity_layout);

        initActionBar();

        displayPreview();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void displayPreview() {
        int screenSize = ScreenUtils.getScreenWidth(this);

        ImageView imageView = findViewById(R.id.image_view);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        params.width = screenSize;
        params.height = screenSize;
        imageView.setLayoutParams(params);

        imageView.setImageBitmap(BitmapFactory.decodeFile(getFilesDir().getAbsolutePath() + "/" + CameraPreviewActivity.PICTURE_PATH));
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
