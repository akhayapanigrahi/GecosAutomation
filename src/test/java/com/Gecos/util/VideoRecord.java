package com.Gecos.util;

/*
* Copyright @ QA All rights Reserved.
* @version 1.0, April, 2017
* @author Akshaya Kumar Panigrahi
*
* Description: This class contains code for Screen Recordings i.e For the whole execution, 
* this is going to record all the steps of the browser while execution... 
* This will make videos and will be saved inside Recordings Folder..... 
*
*
*/
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

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
 
public class VideoRecord {
    public ScreenRecorder screenRecorder;
 
       public void startRecording() throws Exception
       {    
              //File file = new File("C:\\Videos");
  		 File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Recordings");
                            
              Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
              int width = screenSize.width;
              int height = screenSize.height;
                             
              Rectangle captureSize = new Rectangle(0,0, width, height);
                             
            GraphicsConfiguration gc = GraphicsEnvironment
               .getLocalGraphicsEnvironment()
               .getDefaultScreenDevice()
               .getDefaultConfiguration();
 
           this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
               new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
               new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                    CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                    DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                    QualityKey, 1.0f,
                    KeyFrameIntervalKey, 15 * 60),
               new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                    FrameRateKey, Rational.valueOf(30)),
               null, file, "MyVideo");
          
          this.screenRecorder.start();
       
       }
 
       public void stopRecording() throws Exception
       {
         this.screenRecorder.stop();
       }
       
      
}