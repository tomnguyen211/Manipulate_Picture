package homework02;

import java.util.Scanner;

/**
 * This Main Class ConsoleUI will perform a simple UI to test the Image
 * Manipulation 
 * @author Dat Nguyen, 402005276, 2013-07
 *
 */
public class ConsoleUI {
	public static void main(String[] args)
	{
		/**Create a Scanner Object to get input from user of file to manipulate
		 * */
		Scanner name = new Scanner(System.in);
		System.out.println("Enter the name of image: ");
		String nameImage = name.nextLine();		
		/**Create a String with a path file and name*/
		String file = "src\\files\\" + nameImage;
		/**Create a PPMImage Object to store the file */
		PPMImage image = new PPMImage(file);
		/**Create a Scanner Object to get input of option to manipulate image */
		Scanner function = new Scanner(System.in);
		System.out.println("Option:\t1:sepia\t2:greyscale\t3:negative\t4:None");
		System.out.println("Enter Option(1,2,3): ");
		int option = function.nextInt();
		/**Create a Switch to sort option that input from user*/
		switch(option)
		{
			case 1:
				image.sepia();
				break;
			case 2:
				image.grayscale();
				break;
			case 3:
				image.negative();
				break;
			case 4:
				break;
			default:
				System.out.println("Error! Return Image to Original");
				break;
		}
		/**Create a Scanner Object to get input of new name of the file */
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter new name for the file: ");
		String newName = sc.nextLine();
		/**Create a String of the new file name and use writeImage method to
		 * start writing */
		String fileOuput = "src\\newfiles\\" + newName +".ppm";
		image.writeImage(fileOuput);
		name.close();
		function.close();
		sc.close();
	}
}
