package homework02;
import java.io.*;
/** 
 * Create a PPMImage Object*/
public class PPMImage{
	/**This {@code String} is the magicNumber of PPM Image*/
	private String magicNumber;
	/**This value is width of PPM Image*/
	private int width;
	/**This value is height of PPM Image*/
	private int height;
	/**This value is Max Channel Color of PPM Image*/
	private int maxColorValue; 
	/**This array will store each  channel Red, Green, Blue pixel of PPM Image*/
	private char[] raster;
	
	/**
	 * Initializes a {@code PPMImage} object using default values.
	 * The Constructor call readImage method automatically
	 */
	public PPMImage(String imageFileName)
	{
		super();
		magicNumber = "";
		readImage(imageFileName);
	}
	/**
	 * This method takes a String as a new file and using FileOutputStream to
	 * write each byte to that String File
	 * 
	 * @param outputImageFileName as a string of the name of new file
	 */
	public void writeImage(String outputImageFileName)
	{
		/**Create a new File under String Name*/
		File b = new File(outputImageFileName);
		/** Perform Try/Catch Error */
		try {
			/**Create a OutputStream to start writing PPM Image*/
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(b));
			/**Using For Loop to write each character in a magicNumber String
			 * Convert to byte as a same time*/
			for (int i = 0; i< magicNumber.length();i++)
			{
				output.write((byte) magicNumber.charAt(i));
			}
			/**Write a byte of linefeed*/
			output.write(10);
			/**Convert a Int to String for Width*/
			String widthString = String.valueOf(width);
			/**Using For Loop to write each character in a Width String
			 * Convert to byte as a same time*/
			for (int i = 0; i< widthString.length();i++)
			{
				output.write((byte) widthString.charAt(i));
			}
			/**Write a byte of space*/
			output.write(32);
			/**Convert a Int to String for Height*/
			String heightString = String.valueOf(height);
			/**Using For Loop to write each character in a Height String
			 * Convert to byte as a same time*/
			for (int i = 0; i< heightString.length();i++)
			{
				output.write((byte) heightString.charAt(i));
			}
			/**Write a byte of linefeed*/
			output.write(10);
			/**Convert a Int to String for Max Color Value*/
			String maxValueString = String.valueOf(maxColorValue);
			/**Using For Loop to write each character in a Max Color Value 
			 * String
			 * Convert to byte as a same time*/
			for (int i = 0; i< maxValueString.length();i++)
			{
				output.write((byte) maxValueString.charAt(i));
			}
			/**Write a byte of linefeed*/
			output.write(10);
			/**Using For Loop to write all element of the Raster array
			 * Convert to byte as a same time*/
			for(int n = 0; n < raster.length; n++)
			{
				output.write((byte) raster[n]) ;
			}
			/**Flush and Close writing*/
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method takes a String as a name of file and using FileInputStream 
	 * to read and store in correct parameter
	 * 
	 * @param InputImageFileName as a string of the name of the file
	 */
	private void readImage(String imageFileName)
	{
		/**Create a File under String Name for Reading*/
		File a = new File(imageFileName);
		try {
			/**Create a InputStream to start reading PPM Image*/
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(a));
			/**Create an array of byte and store everything of the file to the array */
			byte[] byteArray = input.readAllBytes();
			/**Close Reading*/
			input.close();
			/**Create a default index for later array reading */
			int cursor = 0;
			/**Using While Loop to read and store magicNumber*/
			while(byteArray[cursor] != 10) {
				magicNumber += (char)byteArray[cursor];
				cursor++;
			}
			cursor++;
			/**Create a empty String to convert from byte*/
			String imageWidth = "";
			/**Read byte, convert to char, and adding to String*/
			while(byteArray[cursor] !=32)
			{
				imageWidth += (char)byteArray[cursor];
				cursor++;
			}
			/**Convert from String to Integer and Store to Width parameter*/
			width = Integer.parseInt(imageWidth);
			cursor++;
			String imageHeight = "";
			while(byteArray[cursor] !=10)
			{
				imageHeight += (char)byteArray[cursor];
				cursor++;
			}
			height = Integer.parseInt(imageHeight);
			cursor++;
			String imagePixel = "";
			while(byteArray[cursor] !=10)
			{
				imagePixel += (char)byteArray[cursor];
				cursor++;
			}
			maxColorValue = Integer.parseInt(imagePixel);
			cursor++;
			/**Create size of array by formula width*height*3 */
			raster = new char[width*height*3];
			/**Create a default index for Raster array reading */
			int rasterIndex = 0;
			/**
			 * Using While loop to read the remaining element (pixel data) in a 
			 * file and store to the raster array */
			while(cursor < byteArray.length)
			{
				raster[rasterIndex] = (char)byteArray[cursor];
				rasterIndex++;
				cursor++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method will manipulate the PPM Image by manipulate each color
	 * channel in R,G,B in Raster Array Elements 
	 * First, convert each element in Raster Array to byte, and then integer
	 * Second, manipulate channel R,G,B with given formula
	 * Third, store it back to the Raster array
	 */
	public void grayscale() {
		for(int n = 0; n < raster.length; n+=3)
		{
			int red = Byte.toUnsignedInt((byte)raster[n]);
			if(red > 255)
				red = 255;
			int green = Byte.toUnsignedInt((byte)raster[n+1]);
			if(green > 255)
				green = 255;
			int blue = Byte.toUnsignedInt((byte)raster[n+2]);
			if(blue > 255)
				blue = 255;
			double redChange = (red * 0.299f) + (green * 0.587f) + (blue * 0.114f);
			if(redChange > 255)
				redChange = 255;
			raster[n] = (char)((byte) redChange);
			double greenChange = (red * 0.299f) + (green * 0.587f) + (blue * 0.144f);
			if(greenChange> 255)
				greenChange = 255;
			raster[n+1] = (char)((byte) greenChange);
			double blueChange = (red * 0.299f) + (green * 0.587f) + (blue * 0.144f);
			if(blueChange> 255)
				blueChange = 255;
			raster[n+2] = (char)((byte) blueChange);
		}
	}
	/**
	 * This method will manipulate the PPM Image by manipulate each color
	 * channel in R,G,B in Raster Array Elements 
	 * First, convert each element in Raster Array to byte, and then integer
	 * Second, manipulate channel R,G,B with given formula
	 * Third, store it back to the Raster array
	 */
	public void sepia()
	{
		for(int n = 0; n < raster.length; n+=3)
		{
			int red = Byte.toUnsignedInt((byte)raster[n]);
			if(red > 255)
				red = 255;
			int green = Byte.toUnsignedInt((byte)raster[n+1]);
			if(green > 255)
				green = 255;
			int blue = Byte.toUnsignedInt((byte)raster[n+2]);
			if(blue > 255)
				blue = 255;
			double redChange = (red * 0.393f) + (green * 0.769f) + (blue * 0.189f);
			if(redChange > 255)
				redChange = 255;
			raster[n] = (char)((byte) redChange);
			double greenChange = (red * 0.349f) + (green * 0.686f) + (blue * 0.168f);
			if(greenChange> 255)
				greenChange = 255;
			raster[n+1] = (char)((byte) greenChange);
			double blueChange = (red * 0.272f) + (green * 0.534f) + (blue * 0.131f);
			if(blueChange> 255)
				blueChange = 255;
			raster[n+2] = (char)((byte) blueChange);
		}
	}
	/**
	 * This method will manipulate the PPM Image by manipulate each color
	 * channel in R,G,B in Raster Array Elements 
	 * First, convert each element in Raster Array to byte, and then integer
	 * Second, manipulate channel R,G,B with given formula
	 * Third, store it back to the Raster array
	 */
	public void negative()
	{
		for(int n = 0; n < raster.length; n+=3)
		{
			int red = Byte.toUnsignedInt((byte)raster[n]);
			if(red > 255)
				red = 255;
			int green = Byte.toUnsignedInt((byte)raster[n+1]);
			if(green > 255)
				green = 255;
			int blue = Byte.toUnsignedInt((byte)raster[n+2]);
			if(blue > 255)
				blue = 255;
			double redChange = 255 - red;
			raster[n] = (char)((byte) redChange);
			double greenChange = 255 - green;
			raster[n+1] = (char)((byte) greenChange);
			double blueChange = 255 - blue;
			raster[n+2] = (char)((byte) blueChange);
		}
	}
}
	
	

	


