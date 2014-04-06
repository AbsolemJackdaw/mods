//package telepads.util;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.io.FileUtils;
//
//import telepads.block.TETelepad;
//
//public class ExportTelepad {
//
//	static File f = new File("telepads.txt");
//
//	public static void export(TETelepad pad) {
//
//		try {
//
//			FileUtils.writeStringToFile(f, (pad.xCoord+"#" + pad.yCoord +"#" + pad.zCoord+"#"+pad.channelName+"#"+pad.dimension+"#"+pad.telepadname));
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		}
//
//
//	}
//
//	public static List<int[]> read(String channel){
//
//		List<int[]> allCoords = new ArrayList<int[]>();
//
//		try {
//
//			List<String> c = FileUtils.readLines(f);
//
//			while(c != null)
//				for(String data : c){
//					if(data.contains(channel)){
//
//						String[] ar = data.split("#");
//						int[] a = new int[3];
//						a[0] = Integer.parseInt(ar[0]);//x
//						a[1] = Integer.parseInt(ar[1]);//y
//						a[2] = Integer.parseInt(ar[2]);//z
//						a[3] = Integer.parseInt(ar[4]);//dim
//
//						allCoords.add(a);
//					}
//				}
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		}
//
//		return allCoords;	
//	}
//
//
//
//
//	public static void delete(TETelepad pad){
//
//		String channel = pad.channelName;
//
//		String lineToRemove = pad.xCoord+"#" + pad.yCoord +"#" + pad.zCoord+"#"+pad.channelName+"#"+pad.dimension+"#"+pad.telepadname;
//
//
//		try {
//
//			if (!f.isFile()) {
//				System.out.println("Parameter is not an existing file");
//				return;
//			}
//
//			//Construct the new file that will later be renamed to the original filename.
//			File tempFile = new File(f.getAbsolutePath() + ".tmp");
//
//			BufferedReader br = new BufferedReader(new FileReader(f));
//			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
//
//			String line = null;
//
//			//Read from the original file and write to the new
//			//unless content matches data to be removed.
//			while ((line = br.readLine()) != null) {
//
//				if (!line.trim().equals(lineToRemove)) {
//
//					pw.println(line);
//					pw.flush();
//				}
//			}
//			pw.close();
//			br.close();
//
//			//Delete the original file
//			if (!f.delete()) {
//				System.out.println("Could not delete original file");
//				return;
//			}
//
//			//Rename the new file to the filename the original file had.
//			if (!tempFile.renameTo(f))
//				System.out.println("Could not rename temp file");
//
//		}
//		catch (FileNotFoundException ex) {
//			ex.printStackTrace();
//		}
//		catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}
//
//	public static String readName(int[] is) {
//
//		try {
//
//			List<String> c = FileUtils.readLines(f);
//
//			for(String data : c){
//				if(data.contains(""+is[0]) && data.contains(""+is[1]) && data.contains(""+is[2])){
//
//					String[] ar = data.split("#");
//
//					return ar[5];
//				}
//			}
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		}
//
//		return "errored pad";
//	}
//}
