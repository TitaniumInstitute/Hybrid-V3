package com.ti.framework.utils.video;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import com.ti.framework.base.FrameworkException;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

public class SpecializedScreenRecorder extends ScreenRecorder {
    private String name;
    private ScreenRecorder screenRecorder;

    public SpecializedScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
                                     Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder,
                                     String name) throws IOException, AWTException {
        super(cfg,captureArea,fileFormat,screenFormat,mouseFormat,audioFormat,movieFolder);
        this.name = name;

    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException{
        if (!movieFolder.exists()){
            movieFolder.mkdirs();
        }else if(!movieFolder.isDirectory()){
            new FrameworkException(String.format("\\%s\\ is not a directory", movieFolder));
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        return new File(movieFolder, name + "-"+dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
    }
}
