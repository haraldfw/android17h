package org.haraldfw.oving4.dummy;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample uri for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ImageContent {

    private static final List<Image> dummyImages = new ArrayList<Image>() {{
        add(new Image("0", "http://www.designformusic.com/wp-content/uploads/2015/10/insurgency-digital-album-cover-design.jpg", "insurgency"));
        add(new Image("1", "http://www.designformusic.com/wp-content/uploads/2016/01/perfectly-chilled-album-cover-artwork-design-500x500.jpg", "Perfectly chilled"));
        add(new Image("2", "https://spark.adobe.com/images/landing/examples/blizzard-album-cover.jpg", "cold notes"));
        add(new Image("3", "https://blog.spoongraphics.co.uk/wp-content/uploads/2017/album-art/8.jpg", "tycho"));
        add(new Image("4", "https://www.billboard.com/files/styles/900_wide/public/media/Joy-Division-Unknown-Pleasures-album-covers-billboard-1000x1000.jpg", "Joy Division"));
        add(new Image("5", "http://illusion.scene360.com/wp-content/uploads/2014/10/computergraphics-album-covers-2014-05.jpg", "Minorville"));
        add(new Image("6", "https://creators-images.vice.com/content-images/contentimage/no-slug/32b467be1ad9b7c6db2671bc3ffd69ab.jpg", "Coldplay - parachutes"));
    }};

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Image> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Image> ITEM_MAP = new HashMap<>();

    static {
        // Add some sample items.
        for (int i = 0; i < dummyImages.size(); i++) {
            addItem(dummyImages.get(i));
        }
    }


    private static void addItem(Image item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class Image {
        public final String id;
        public final Drawable imageDrawable;
        public final String description;

        public Image(String id, String uri, String description) {
            this.id = id;
            this.description = description;
            this.imageDrawable = drawableFromUrl(uri);
        }

        @Override
        public String toString() {
            return description;
        }
    }

    static Drawable drawableFromUrl(String uri) {
        InputStream input;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(uri).openConnection();
            connection.connect();
            input = connection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to create drawable for uri " + uri);
            return null;
        }
        System.out.println("Created drawable for uri " + uri);
        return new BitmapDrawable(BitmapFactory.decodeStream(input));
    }
}
