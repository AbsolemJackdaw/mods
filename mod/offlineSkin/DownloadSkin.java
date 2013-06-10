package offlineSkin;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadSkin{
	private final URL url;
	private final File file;

	public DownloadSkin(File targetFile, URL url){
		this.file = targetFile;
		this.url = url;
	}

	public void download(){
		try{
			ReadableByteChannel channel = Channels.newChannel(this.url.openStream());
			FileOutputStream stream = new FileOutputStream(this.file);
			stream.getChannel().transferFrom(channel, 0, 1 << 24);
			stream.close();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
}