package showgif.gl.com.showgif;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by gl152 on 2017/8/11.
 */

public class GiftView extends ImageView {

    private Movie movie;
    private long movieStart = 0;

    public GiftView(Context context) {
        super(context);
        init();
    }

    public GiftView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GiftView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        movie = Movie.decodeStream(getResources().openRawResource(R.drawable.floatcircleviewgif));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long now = SystemClock.uptimeMillis(); // 从开机到现在的毫秒数（手机睡眠的时间不包括在内）；

        if (movieStart == 0) {
            movieStart = now;
        }

        if (movie != null) {
            int duration = movie.duration();
            int reltime = (int) ((now - movieStart) % duration);
            movie.setTime(reltime);
            movie.draw(canvas, 0, 0);

            invalidate();
        }
    }
}
