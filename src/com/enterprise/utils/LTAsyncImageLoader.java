package com.enterprise.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import com.enterprise.utils.exception.LTHttpException;

public class LTAsyncImageLoader {

    private HashMap<String, SoftReference<Drawable>> imageCache;

    private static LTAsyncImageLoader INSTANCE = new LTAsyncImageLoader();

    public LTAsyncImageLoader() {
        imageCache = new HashMap<String, SoftReference<Drawable>>();
    }

    public static LTAsyncImageLoader getInstance() {
        return INSTANCE;
    }

    public Drawable loadDrawable(final String imageUrl,
                                 final ImageCallback imageCallback) {
        if (imageCache.containsKey(imageUrl)) {
            SoftReference<Drawable> softReference = imageCache.get(imageUrl);
            Drawable drawable = softReference.get();
            if (drawable != null) {
                imageCallback.imageLoaded(drawable, imageUrl);
                return drawable;
            }
        }
        final Handler handler = new Handler() {
            public void handleMessage(Message message) {
                imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
            }
        };
        new Thread() {
            @Override
            public void run() {
                Drawable drawable;
				try {
					drawable = loadImageFromUrl(imageUrl);
				} catch (LTHttpException e) {
					drawable = null;
				}
                imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
                Message message = handler.obtainMessage(0, drawable);
                handler.sendMessage(message);
            }
        }.start();
        return null;
    }

    public static Drawable loadImageFromUrl(String url) throws LTHttpException {
        URL m;
        InputStream i = null;
        try {
            m = new URL(url);
            i = (InputStream) m.openStream();
        } catch (MalformedURLException e1) {
//            e1.printStackTrace();
            throw new LTHttpException(String.format("下载图片失败：%s",e1.getMessage()));
        } catch (IOException e) {
        	throw new LTHttpException(String.format("下载图片失败：%s",e.getMessage()));
//            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(i, "src");
        return d;
    }

    public interface ImageCallback {
        public void imageLoaded(Drawable imageDrawable, String imageUrl);
    }

}
