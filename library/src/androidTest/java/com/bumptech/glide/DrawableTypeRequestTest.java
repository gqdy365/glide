package com.bumptech.glide;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.tests.GlideShadowLooper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.bumptech.glide.tests.Util.arg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(shadows = GlideShadowLooper.class)
public class DrawableTypeRequestTest {
    private DrawableTypeRequest<String> request;
    private String model;
    private RequestManager.OptionsApplier optionsApplier;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        optionsApplier = mock(RequestManager.OptionsApplier.class);
        when(optionsApplier.apply(any(GenericRequestBuilder.class))).thenAnswer(arg(0));
        request = new DrawableTypeRequest<String>(String.class, mock(ModelLoader.class),
                mock(ModelLoader.class), Robolectric.application, Glide.get(Robolectric.application),
                mock(RequestTracker.class), mock(Lifecycle.class), optionsApplier);
    }

    @After
    public void tearDown() {
        Glide.tearDown();
    }

    @Test
    public void testDefaultOptionsAreAppliedOnAsBitmap() {
        BitmapTypeRequest<String> builder = request.asBitmap();
        verify(optionsApplier).apply(eq(builder));
    }

    @Test
    public void testDefaultOptionsAreAppliedOnAsGif() {
        GifTypeRequest<String> builder = request.asGif();
        verify(optionsApplier).apply(eq(builder));
    }
}