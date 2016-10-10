
package android.yuchang.weathermvp.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.yuchang.weathermvp.R;


/**
 * 指示器
 * Availables attributes are:<br/>
 * activeColor:当前正在显示的点的颜色，默认白色
 * inactiveColor: 没有显示的点 (default to 0x44FFFFFF)
 * inactiveType: inactive定义是是实心还是空心  either stroke or fill (default to stroke)
 * activeType:active定义是是实心还是空心, either stroke or fill (default to fill)
 * fadeOut: Define the time (in ms) until the indicator will fade out (default to 0 = never fade out)
 * radius:定义圆的radius (default to 4.0)
 */
public class YCCircleFlowIndicator extends View implements AnimationListener {
    //空心
    private static final int STYLE_STROKE = 0;
    //实心
    private static final int STYLE_FILL = 1;

    //圆的角度
    private float radius = 4;
    private float circleSeparation = 2 * radius + radius;
    private float activeRadius = 0.5f;
    private int fadeOutTime = 0;
    private final Paint mPaintInactive = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint mPaintActive = new Paint(Paint.ANTI_ALIAS_FLAG);
    private ViewPager viewFlow;
    private int currentScroll = 0;
    private int flowWidth = 0;
    private FadeTimer timer;
    public AnimationListener animationListener = this;
    private Animation animation;
    private boolean mCentered = false;

    /**
     * Default constructor
     *
     * @param context
     */
    public YCCircleFlowIndicator(Context context) {
        super(context);
        initColors(0xFFFFFFFF, 0xFFFFFFFF, STYLE_FILL, STYLE_STROKE);
    }

    /**
     * The contructor used with an inflater
     *
     * @param context
     * @param attrs
     */
    public YCCircleFlowIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Retrieve styles attributs
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.YCCircleFlowIndicator);

        // Gets the inactive circle type, defaulting to "fill"
        int activeType = a.getInt(R.styleable.YCCircleFlowIndicator_activeType,
                STYLE_FILL);

        int activeDefaultColor = 0xFFFFFFFF;

        // Get a custom inactive color if there is one
        int activeColor = a
                .getColor(R.styleable.YCCircleFlowIndicator_activeColor,
                        activeDefaultColor);

        // Gets the inactive circle type, defaulting to "stroke"
        int inactiveType = a.getInt(
                R.styleable.YCCircleFlowIndicator_inactiveType, STYLE_STROKE);

        int inactiveDefaultColor = 0x44FFFFFF;
        // Get a custom inactive color if there is one
        int inactiveColor = a.getColor(
                R.styleable.YCCircleFlowIndicator_inactiveColor,
                inactiveDefaultColor);

        // Retrieve the radius
        radius = a.getDimension(R.styleable.YCCircleFlowIndicator_radius, 4.0f);

        circleSeparation = a.getDimension(R.styleable.YCCircleFlowIndicator_circleSeparation, 2 * radius + radius);
        activeRadius = a.getDimension(R.styleable.YCCircleFlowIndicator_activeRadius, 0.5f);
        // Retrieve the fade out time
        fadeOutTime = a.getInt(R.styleable.YCCircleFlowIndicator_fadeOut, 0);

        mCentered = a.getBoolean(R.styleable.YCCircleFlowIndicator_centered, false);

        initColors(activeColor, inactiveColor, activeType, inactiveType);
    }

    private void initColors(int activeColor, int inactiveColor, int activeType,
                            int inactiveType) {
        // Select the paint type given the type attr
        switch (inactiveType) {
            case STYLE_FILL:
                mPaintInactive.setStyle(Style.FILL);
                break;
            default:
                mPaintInactive.setStyle(Style.STROKE);
        }
        mPaintInactive.setColor(inactiveColor);

        // Select the paint type given the type attr
        switch (activeType) {
            case STYLE_STROKE:
                mPaintActive.setStyle(Style.STROKE);
                break;
            default:
                mPaintActive.setStyle(Style.FILL);
        }
        mPaintActive.setColor(activeColor);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = 3;
        if (viewFlow != null) {
            count = viewFlow.getAdapter().getCount();
        }

        //this is the amount the first circle should be offset to make the entire thing centered
        float centeringOffset = 0;

        int leftPadding = getPaddingLeft();

        // Draw stroked circles
        for (int iLoop = 0; iLoop < count; iLoop++) {
            canvas.drawCircle(leftPadding + radius
                            + (iLoop * circleSeparation) + centeringOffset,
                    getPaddingTop() + radius, radius, mPaintInactive);
        }
        float cx = 0;
        if (flowWidth != 0) {
            // Draw the filled circle according to the current scroll
            cx = (currentScroll * circleSeparation) / flowWidth;
        }
        // The flow width has been upadated yet. Draw the default position
        canvas.drawCircle(leftPadding + radius + cx + centeringOffset, getPaddingTop()
                + radius, radius + activeRadius, mPaintActive);
    }

	/*
     * (non-Javadoc)
	 * 
	 * @see
	 * org.taptwo.android.widget.ViewFlow.ViewSwitchListener#onSwitched(android
	 * .view.View, int)
	 */

    public void onSwitched(View view, int position) {
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.taptwo.android.widget.FlowIndicator#setViewFlow(org.taptwo.android
	 * .widget.ViewFlow)
	 */

    public void setViewFlow(ViewPager view) {
        resetTimer();
        viewFlow = view;
        flowWidth = viewFlow.getWidth();
        invalidate();
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.taptwo.android.widget.FlowIndicator#onScrolled(int, int, int,
	 * int)
	 */

    public void onScrolled(int h, int v, int oldh, int oldv) {
        setVisibility(View.VISIBLE);
        resetTimer();
        flowWidth = viewFlow.getWidth();
        if (viewFlow.getAdapter().getCount() * flowWidth != 0) {
            currentScroll = h % ((viewFlow.getAdapter().getCount()) * flowWidth);
        } else {
            currentScroll = h;
        }
        invalidate();
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        // We were told how big to be
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        // Calculate the width according the views count
        else {
            int count = 3;
            if (viewFlow != null) {
                count = viewFlow.getAdapter().getCount();
            }
            float temp = circleSeparation - 2 * radius;
            result = (int) (getPaddingLeft() + getPaddingRight()
                    + (count * 2 * radius) + (count - 1) * temp + 1);
            // Respect AT_MOST value if that was what is called for by
            // measureSpec
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * Determines the height of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        // We were told how big to be
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        // Measure the height
        else {
            result = (int) (2 * radius + getPaddingTop() + getPaddingBottom() + 1);
            // Respect AT_MOST value if that was what is called for by
            // measureSpec
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * Sets the fill color
     *
     * @param color ARGB value for the text
     */
    public void setFillColor(int color) {
        mPaintActive.setColor(color);
        invalidate();
    }

    /**
     * Sets the stroke color
     *
     * @param color ARGB value for the text
     */
    public void setStrokeColor(int color) {
        mPaintInactive.setColor(color);
        invalidate();
    }

    /**
     * Resets the fade out timer to 0. Creating a new one if needed
     */
    private void resetTimer() {
        // Only set the timer if we have a timeout of at least 1 millisecond
        if (fadeOutTime > 0) {
            // Check if we need to create a new timer
            if (timer == null || timer._run == false) {
                // Create and start a new timer
                timer = new FadeTimer();
                timer.execute();
            } else {
                // Reset the current tiemr to 0
                timer.resetTimer();
            }
        }
    }

    /**
     * Counts from 0 to the fade out time and animates the view away when
     * reached
     */
    private class FadeTimer extends AsyncTask<Void, Void, Void> {
        // The current count
        private int timer = 0;
        // If we are inside the timing loop
        private boolean _run = true;

        public void resetTimer() {
            timer = 0;
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            while (_run) {
                try {
                    // Wait for a millisecond
                    Thread.sleep(1);
                    // Increment the timer
                    timer++;

                    // Check if we've reached the fade out time
                    if (timer == fadeOutTime) {
                        // Stop running
                        _run = false;
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            animation = AnimationUtils.loadAnimation(getContext(),
                    android.R.anim.fade_out);
            animation.setAnimationListener(animationListener);
            startAnimation(animation);
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        setVisibility(View.GONE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }
}