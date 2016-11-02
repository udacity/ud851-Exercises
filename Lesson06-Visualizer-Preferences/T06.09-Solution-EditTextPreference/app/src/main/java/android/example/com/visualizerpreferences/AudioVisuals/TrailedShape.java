package android.example.com.visualizerpreferences.AudioVisuals;

/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorInt;

import java.util.LinkedList;

/**
 * Abstract class representing a shape and a trail for where it's been
 */
abstract class TrailedShape {

    // Static variables for the center of the parent view and the minimum size of all of the shapes
    private static float sViewCenterX, sViewCenterY;
    private static float sMinSize;

    // Variables for determining size
    private final float mMultiplier;

    // Variables for determining trail
    private final Path mTrailPath;
    private final LinkedList<TrailPoint> mTrailList;

    // Paint for drawing
    private final Paint mPaint;
    private Paint mTrailPaint;

    // Variable for determining position
    private float mShapeRadiusFromCenter;

    TrailedShape(float multiplier) {
        this.mMultiplier = multiplier;

        // Setup trail variables
        this.mTrailPath = new Path();
        this.mTrailList = new LinkedList<>();

        // Setup paint and attributes
        this.mPaint = new Paint();
        this.mTrailPaint = new Paint();

        mPaint.setStyle(Paint.Style.FILL);
        mTrailPaint.setStyle(Paint.Style.STROKE);
        mTrailPaint.setStrokeWidth(5);
        mTrailPaint.setStrokeJoin(Paint.Join.ROUND);
        mTrailPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    // Static methods
    static void setMinSize(float minSize) {
        TrailedShape.sMinSize = minSize;
    }

    static void setViewCenterY(float viewCenterY) {
        TrailedShape.sViewCenterY = viewCenterY;
    }

    static void setViewCenterX(float viewCenterX) {
        TrailedShape.sViewCenterX = viewCenterX;
    }

    /**
     * This draw method abstracts out what is common between drawing all shapes
     *
     * @param canvas         The canvas to draw on
     * @param currentFreqAve The average frequency for the same, which determines the boost in size
     * @param currentAngle   The current angle around the center to draw the shape
     */
    void draw(Canvas canvas, float currentFreqAve, double currentAngle) {

        float currentSize = sMinSize + mMultiplier * currentFreqAve;

        // Calculate where the shape is
        float shapeCenterX = calcLocationInAnimationX(mShapeRadiusFromCenter, currentAngle);
        float shapeCenterY = calcLocationInAnimationY(mShapeRadiusFromCenter, currentAngle);

        // Calculate where the next point in the trail is
        float trailX = calcLocationInAnimationX((mShapeRadiusFromCenter + currentSize - sMinSize), currentAngle);
        float trailY = calcLocationInAnimationY((mShapeRadiusFromCenter + currentSize - sMinSize), currentAngle);

        mTrailPath.rewind(); // clear the trail
        mTrailList.add(new TrailPoint(trailX, trailY, currentAngle)); // add the new line segment

        // Keep the trail size correct
        while (currentAngle - mTrailList.peekFirst().theta > 2 * Math.PI) {
            mTrailList.poll();
        }

        // Draw the trail
        mTrailPath.moveTo(mTrailList.peekFirst().x, mTrailList.peekFirst().y);
        for (TrailPoint trailPoint : mTrailList) {
            mTrailPath.lineTo(trailPoint.x, trailPoint.y);
        }

        canvas.drawPath(mTrailPath, mTrailPaint);

        // Call the abstract drawThisShape method, this must be defined for each shape.
        drawThisShape(shapeCenterX, shapeCenterY, currentSize, canvas, mPaint);
    }

    /**
     * Determines how to draw the particular shape
     *
     * @param shapeCenterX Center X position of the shape
     * @param shapeCenterY Center Y position of the shape
     * @param currentSize  Size of the shape
     * @param canvas       The canvas to draw on
     * @param paint        The paint to draw with
     */
    protected abstract void drawThisShape(float shapeCenterX, float shapeCenterY, float currentSize, Canvas canvas, Paint paint);

    /**
     * Clears the trail
     */
    void restartTrail() {
        mTrailList.clear();
    }

    /**
     * Calculates the center x location
     *
     * @param radiusFromCenter    The distance from the center of this shape
     * @param currentAngleRadians The current angle of the shape
     * @return
     */
    private float calcLocationInAnimationX(float radiusFromCenter, double currentAngleRadians) {
        return (float) (sViewCenterX + Math.cos(currentAngleRadians) * radiusFromCenter);

    }

    /**
     * Calculates the center y location
     *
     * @param radiusFromCenter    The distance from the center of this shape
     * @param currentAngleRadians The current angle of the shape
     * @return
     */
    private float calcLocationInAnimationY(float radiusFromCenter, double currentAngleRadians) {
        return (float) (sViewCenterY + Math.sin(currentAngleRadians) * radiusFromCenter);
    }

    /**
     * Sets the shape color
     *
     * @param color Color to set this shape to
     */
    void setShapeColor(@ColorInt int color) {
        mPaint.setColor(color);
    }

    /**
     * Sets the trail color
     *
     * @param color Color to set this trail to
     */
    void setTrailColor(@ColorInt int color) {
        mTrailPaint.setColor(color);
    }

    /**
     * Sets the shapes distance from the center of the view
     *
     * @param mShapeRadiusFromCenter Distance from center of the view
     */
    void setShapeRadiusFromCenter(float mShapeRadiusFromCenter) {
        this.mShapeRadiusFromCenter = mShapeRadiusFromCenter;
    }

    /**
     * Inner class representing points in the trail
     */
    private class TrailPoint {
        final float x;
        final float y;
        final double theta;

        TrailPoint(float x, float y, double theta) {
            this.x = x;
            this.y = y;
            this.theta = theta;
        }
    }
}
