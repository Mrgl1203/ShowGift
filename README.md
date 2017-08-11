# ShowGift
### 安卓加载gif图的两种方式
* ImageView是不能完美加载Gif格式的图片，如果我们在ImageView中src指定的资源是gif格式的话，我们将会惊喜的发觉画面永远停留在第一帧，解决方法如下：
#### 方法1
Glide可以加载gif图，无论时本地资源文件中的还是网络gif

#### 方法2
自定义view通过movie类获取gif的时间，在ondraw中绘制每一帧
```
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
 ```
