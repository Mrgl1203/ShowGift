package showgif.gl.com.showgif;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.io.InputStream;

import util.GifUrl;

public class MainActivity extends AppCompatActivity {
    private ImageView gifiv;
    private AssetManager assetManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gifiv = (ImageView) findViewById(R.id.gifiv);
        assetManager = getAssets();
        String loadUrl = GifUrl.G4;
//        gifiv.setImageBitmap(getFileFromAsset("floatcircleviewgif.gif"));  //只能显示gif第一帧
//        Glide.with(this).load(GifUrl.G2).into(gifiv);//可以加载网络gif图
        //RequestOptions一次设置可以在多次使用
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priorityOf(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(400, 4000);
        Glide.with(this).load(loadUrl)
                .apply(options)
                .thumbnail(Glide.with(this).load(loadUrl))//在gif没有加载出来前会先加载他的缩略图出来
                .into(gifiv);
    }


    public Bitmap getFileFromAsset(String filename) {
        try {
            InputStream in = assetManager.open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
