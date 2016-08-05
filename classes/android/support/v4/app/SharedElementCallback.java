package android.support.v4.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.List;
import java.util.Map;

public abstract class SharedElementCallback
{
  private static final String BUNDLE_SNAPSHOT_BITMAP = "sharedElement:snapshot:bitmap";
  private static final String BUNDLE_SNAPSHOT_IMAGE_MATRIX = "sharedElement:snapshot:imageMatrix";
  private static final String BUNDLE_SNAPSHOT_IMAGE_SCALETYPE = "sharedElement:snapshot:imageScaleType";
  private static int MAX_IMAGE_SIZE = 1048576;
  private Matrix mTempMatrix;
  
  private static Bitmap createDrawableBitmap(Drawable paramDrawable)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    Bitmap localBitmap;
    if ((i <= 0) || (j <= 0)) {
      localBitmap = null;
    }
    for (;;)
    {
      return localBitmap;
      float f = Math.min(1.0F, MAX_IMAGE_SIZE / (i * j));
      if (((paramDrawable instanceof BitmapDrawable)) && (f == 1.0F))
      {
        localBitmap = ((BitmapDrawable)paramDrawable).getBitmap();
      }
      else
      {
        int k = (int)(f * i);
        int m = (int)(f * j);
        localBitmap = Bitmap.createBitmap(k, m, Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap);
        Rect localRect = paramDrawable.getBounds();
        int n = localRect.left;
        int i1 = localRect.top;
        int i2 = localRect.right;
        int i3 = localRect.bottom;
        paramDrawable.setBounds(0, 0, k, m);
        paramDrawable.draw(localCanvas);
        paramDrawable.setBounds(n, i1, i2, i3);
      }
    }
  }
  
  public Parcelable onCaptureSharedElementSnapshot(View paramView, Matrix paramMatrix, RectF paramRectF)
  {
    Object localObject;
    if ((paramView instanceof ImageView))
    {
      ImageView localImageView = (ImageView)paramView;
      Drawable localDrawable1 = localImageView.getDrawable();
      Drawable localDrawable2 = localImageView.getBackground();
      if ((localDrawable1 != null) && (localDrawable2 == null))
      {
        Bitmap localBitmap2 = createDrawableBitmap(localDrawable1);
        if (localBitmap2 != null)
        {
          localObject = new Bundle();
          ((Bundle)localObject).putParcelable("sharedElement:snapshot:bitmap", localBitmap2);
          ((Bundle)localObject).putString("sharedElement:snapshot:imageScaleType", localImageView.getScaleType().toString());
          if (localImageView.getScaleType() == ImageView.ScaleType.MATRIX)
          {
            Matrix localMatrix = localImageView.getImageMatrix();
            float[] arrayOfFloat = new float[9];
            localMatrix.getValues(arrayOfFloat);
            ((Bundle)localObject).putFloatArray("sharedElement:snapshot:imageMatrix", arrayOfFloat);
          }
        }
      }
    }
    for (;;)
    {
      return (Parcelable)localObject;
      int i = Math.round(paramRectF.width());
      int j = Math.round(paramRectF.height());
      Bitmap localBitmap1 = null;
      if ((i > 0) && (j > 0))
      {
        float f = Math.min(1.0F, MAX_IMAGE_SIZE / (i * j));
        int k = (int)(f * i);
        int m = (int)(f * j);
        if (this.mTempMatrix == null) {
          this.mTempMatrix = new Matrix();
        }
        this.mTempMatrix.set(paramMatrix);
        this.mTempMatrix.postTranslate(-paramRectF.left, -paramRectF.top);
        this.mTempMatrix.postScale(f, f);
        localBitmap1 = Bitmap.createBitmap(k, m, Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap1);
        localCanvas.concat(this.mTempMatrix);
        paramView.draw(localCanvas);
      }
      localObject = localBitmap1;
    }
  }
  
  public View onCreateSnapshotView(Context paramContext, Parcelable paramParcelable)
  {
    Object localObject1 = null;
    Object localObject2;
    if ((paramParcelable instanceof Bundle))
    {
      Bundle localBundle = (Bundle)paramParcelable;
      Bitmap localBitmap2 = (Bitmap)localBundle.getParcelable("sharedElement:snapshot:bitmap");
      if (localBitmap2 == null)
      {
        localObject2 = null;
        return (View)localObject2;
      }
      ImageView localImageView = new ImageView(paramContext);
      localObject1 = localImageView;
      localImageView.setImageBitmap(localBitmap2);
      localImageView.setScaleType(ImageView.ScaleType.valueOf(localBundle.getString("sharedElement:snapshot:imageScaleType")));
      if (localImageView.getScaleType() == ImageView.ScaleType.MATRIX)
      {
        float[] arrayOfFloat = localBundle.getFloatArray("sharedElement:snapshot:imageMatrix");
        Matrix localMatrix = new Matrix();
        localMatrix.setValues(arrayOfFloat);
        localImageView.setImageMatrix(localMatrix);
      }
    }
    for (;;)
    {
      localObject2 = localObject1;
      break;
      if ((paramParcelable instanceof Bitmap))
      {
        Bitmap localBitmap1 = (Bitmap)paramParcelable;
        localObject1 = new ImageView(paramContext);
        ((ImageView)localObject1).setImageBitmap(localBitmap1);
      }
    }
  }
  
  public void onMapSharedElements(List<String> paramList, Map<String, View> paramMap) {}
  
  public void onRejectSharedElements(List<View> paramList) {}
  
  public void onSharedElementEnd(List<String> paramList, List<View> paramList1, List<View> paramList2) {}
  
  public void onSharedElementStart(List<String> paramList, List<View> paramList1, List<View> paramList2) {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/app/SharedElementCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */