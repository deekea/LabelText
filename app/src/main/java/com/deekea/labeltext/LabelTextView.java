package com.deekea.labeltext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * 功能: TextView 左边 带一个 长方形 的条形。 颜色与TextColor一致
 */
public class LabelTextView extends TextView {

    private int labelColor;
    // in pixels
    private int labelMargin;
    // in pixels
    private int labelHeight;
    private int labelSize;

    private static final float DEFAULT_LABEL_MARGIN_DP = 7.5f;
    private static final float DEFAULT_LABEL_SIZE_DP = 2.5f;

    public LabelTextView(Context context) {
        this(context, null);
    }

    public LabelTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textStyle);
    }

    public LabelTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabelTextView);
        labelColor = typedArray.getColor(R.styleable.LabelTextView_labelColor, getTextColors().getDefaultColor());
        int drawablePadding = getCompoundDrawablePadding();
        labelMargin = drawablePadding != 0 ? drawablePadding : dip2px(context, DEFAULT_LABEL_MARGIN_DP);
        labelSize = (int) typedArray.getDimension(R.styleable.LabelTextView_labelSize, dip2px(context, DEFAULT_LABEL_SIZE_DP));
        labelHeight = (int) typedArray.getDimension(R.styleable.LabelTextView_labelHeight, getTextSize());
        typedArray.recycle();

        refreshView();
        setGravity(Gravity.CENTER);
    }

    private void refreshView() {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(labelColor);
        gd.setShape(GradientDrawable.RECTANGLE);

        gd.setBounds(0, 0, labelSize, labelHeight);
        setCompoundDrawables(gd, null, null, null);

        setCompoundDrawablePadding(labelMargin);
    }

    public void setLabelColor(int labelColor) {
        this.labelColor = labelColor;
    }

    public void setLabelMargin(int labelMargin) {
        this.labelMargin = labelMargin;
    }

    public void setLabelHeight(int labelHeight) {
        this.labelHeight = labelHeight;
    }

    public void setLabelSize(int labelSize) {
        this.labelSize = labelSize;
    }

    public void update() {
        refreshView();
    }
}
