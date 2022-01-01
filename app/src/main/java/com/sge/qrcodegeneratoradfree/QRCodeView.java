package com.sge.qrcodegeneratoradfree;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.res.ResourcesCompat;
import io.nayuki.qrcodegen.QrCode;

/**
 * Custom view
 */

public class QRCodeView extends View {
    public final static int borderPart = 20;

    final private int mBackgroundColor;
    private Canvas mExtraCanvas;
    private Bitmap mExtraBitmap;
    private String textToQR = "";


    QRCodeView(Context context, String text) {
        this(context, text, null);
    }


    public QRCodeView(Context context, String text, AttributeSet attributeSet) {
        super(context);
        textToQR = text;
        mBackgroundColor = ResourcesCompat.getColor(getResources(),
                R.color.white, null);
    }


    @Override
    protected void onSizeChanged(int width, int height,
                                 int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        mExtraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mExtraCanvas = new Canvas(mExtraBitmap);
        mExtraCanvas.drawColor(mBackgroundColor);

        createQRcode();
    }

    private void createQRcode() {
        QrCode qr = QrCode.encodeText(textToQR, QrCode.Ecc.QUARTILE);

        int screenSize = mExtraBitmap.getHeight();
        if(mExtraBitmap.getWidth() < screenSize) screenSize = mExtraBitmap.getWidth();

        int border = screenSize / borderPart;
        screenSize = screenSize - (2*border);

        double scale = (1.0*qr.size) / (1.0*screenSize);

        for (int y = 0; y < screenSize; y++) {
            for (int x = 0; x < screenSize; x++) {
                boolean color = qr.getModule((int)(x*scale) , (int)(y*scale));
                mExtraBitmap.setPixel(x+border, y+border,color ? Color.BLACK : Color.WHITE);
            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mExtraBitmap, 0, 0, null);
    }

}
