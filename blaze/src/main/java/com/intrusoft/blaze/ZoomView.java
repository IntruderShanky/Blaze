package com.intrusoft.blaze;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/**
 * Displays an arbitrary image with continuous zoom animation.
 *  The {@link ZoomView} class can load images from various sources (such as resources or bitmap),
 *  takes care of computing its measurement from the image so that it can be used in any layout manager.
 *
 *  @see MotionView
 *
 * Created by Intruder Shanky.
 * @since October 2016
 */
public class ZoomView extends View {

    Bitmap imageSrc;
    float x1, y1, x2, y2;
    int HEIGHT, WIDTH, MAX_ZOOM;
    RectF rect1, rect2;
    Handler handler;
    Paint paint;
    Integer integer;
    Context context;
    float changeFactor = 0.5f;

    public ZoomView(Context context) {
        super(context);
        this.context = context;
    }

    public ZoomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ZoomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(final Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.blaze, 0, 0);
        try {
            integer = typeArray.getResourceId(R.styleable.blaze_src, R.drawable.place_holder);
            changeFactor = typeArray.getFloat(R.styleable.blaze_translation_factor, 0.5f);
        } finally {
            typeArray.recycle();
        }
        resetValues();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (HEIGHT > 0) {
            if (imageSrc != null) {
                rect1.set(0, 0, WIDTH, HEIGHT);
                rect2.set(x1, y1, x2, y2);
                if (rect1.intersect(rect2))
                    canvas.drawBitmap(imageSrc, null, rect2, paint);
            }
        } else resetValues();

        super.onDraw(canvas);
        handler.postDelayed(runnable, 16);
    }

    private void updateValues() {
        if (y2 - y1 > MAX_ZOOM || y2 - y1 < HEIGHT)
            changeFactor = -changeFactor;
        x1 -= changeFactor;
        x2 += changeFactor;
        y1 -= changeFactor;
        y2 += changeFactor;
        invalidate();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateValues();
        }
    };

    private void resetValues() {
        WIDTH = getWidth();
        HEIGHT = getHeight();
        MAX_ZOOM = (int) (HEIGHT * 6f);
        rect1 = new RectF();
        rect2 = new RectF();
        paint = new Paint();
        x1 = 0;
        x2 = WIDTH;
        y1 = 0;
        y2 = HEIGHT;
        handler = new Handler();
        setImageResource(integer);
    }

    /**
     * set zoom factor of image.
     * @param factor This is the speed of the animation. This value should be between 1 to 8 for best practice.
     */
    public void setTranslationFactor(float factor) {
        this.changeFactor = factor;
    }

    /**
     * set image to the {@link ZoomView} from resource Id
     * @param resId
     */
    public void setImageResource(Integer resId) {
        BitmapResolver.getBitmap(context, resId, WIDTH, HEIGHT, new BitmapResolver.FinalBitmap() {
            @Override
            public void getBitmap(Bitmap bitmap) {
                imageSrc = bitmap;
            }
        });
    }

    /**
     * set image to the {@link ZoomView} from bitmap
     * @param bitmap
     */
    public void setImageBitmap(Bitmap bitmap) {
        this.imageSrc = bitmap;
    }
}
