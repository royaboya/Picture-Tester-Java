import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from
 * SimplePicture and allows the student to add functionality to
 * the Picture class.
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
  ///////////////////// constructors //////////////////////////////////

  /**
   * Constructor that takes no arguments
   */
  public Picture() {
    /*
     * not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor
     */
    super();
  }

  /**
   * Constructor that takes a file name and creates the picture
   * 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName) {
    // let the parent class handle this fileName
    super(fileName);
  }

  /**
   * Constructor that takes the width and height
   * 
   * @param height the height of the desired picture
   * @param width  the width of the desired picture
   */
  public Picture(int height, int width) {
    // let the parent class handle this width and height
    super(width, height);
  }

  /**
   * Constructor that takes a picture and creates a
   * copy of that picture
   * 
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture) {
    // let the parent class do the copy
    super(copyPicture);
  }

  /**
   * Constructor that takes a buffered image
   * 
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image) {
    super(image);
  }

  ////////////////////// methods ///////////////////////////////////////

  /**
   * Method to return a string with information about this picture.
   * 
   * @return a string with information about the picture such as fileName,
   *         height and width.
   */
  public String toString() {
    String output = "Picture, filename " + getFileName() +
        " height " + getHeight()
        + " width " + getWidth();
    return output;

  }

  /** Method to set the blue to 0 */
  public void zeroBlue() {
    Pixel[][] pixels = this.getPixels2D();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        pixels[i][j].setBlue(0);
      }
    }
  }

  /** Method to set the red and blue to 0 */
  public void greenOnly() {
    // add your code here
    Pixel[][] pixels = this.getPixels2D();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        pixels[i][j].setRed(0);
        pixels[i][j].setBlue(0);
      }
    }
  }

  /** Method to set the red,green,blue to (255 - its original value) */
  public void negate() {
    // add your code here
    Pixel[][] pixels = this.getPixels2D();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        pixels[i][j].setRed(255 - pixels[i][j].getRed());
        pixels[i][j].setGreen(255 - pixels[i][j].getGreen());
        pixels[i][j].setBlue(255 - pixels[i][j].getBlue());

      }
    }

  }

  /** Method to set all red,green,blue to the average of the three values */
  public void grayscale() {
    // add your code here
    Pixel[][] pixels = this.getPixels2D();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        int average = (pixels[i][j].getRed() + pixels[i][j].getBlue() + pixels[i][j].getGreen()) / 3;
        pixels[i][j].setRed(average);
        pixels[i][j].setGreen(average);
        pixels[i][j].setBlue(average);
      }
    }
  }

  /** Method to make the shape of the fish stand out */
  public void enhanceFish() {
    // add your code here
    Pixel[][] pixels = this.getPixels2D();
    for (int i = 0; i < 152; i++) { // chose 152 and 475 to remove extraneous white pixels
      for (int j = 0; j < 474; j++) {
        Pixel pixel = pixels[i][j];
        int red = pixels[i][j].getRed();
        int green = pixels[i][j].getGreen();
        int blue = pixels[i][j].getBlue();

        if (6 <= red && red <= 23 && 148 <= green && green <= 173 && 153 <= blue && blue <= 201) {
          pixel.setBlue(255);
          pixel.setGreen(255);
          pixel.setRed(255);
        }
      }
    }
  }

  /**
   * Method to highlight the edges of object in a picture by checking large
   * changes in color
   */
  public void edgeDetection() {
    // add your code here
    Pixel[][] pixels = this.getPixels2D();
    // vertical scan
    for (int i = 0; i < pixels.length - 1; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        Pixel pixel = pixels[i][j];
        if (pixels[i][j].colorDistance(pixels[i + 1][j].getColor()) > 14.8) {
          pixel.setGreen(0);
          pixel.setBlue(0);
          pixel.setRed(0);
        } else {
          pixel.setRed(255);
          pixel.setGreen(255);
          pixel.setBlue(255);
        }
      }
    }
  }

  /**
   * Method to mirror the picture around a vertical line in the center of the
   * picture from left to right
   */
  public void mirrorVertical() {
    // add your code here
    Pixel[][] pixels = this.getPixels2D();

    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < this.getWidth() / 2; j++) {
        Pixel otherSide = pixels[i][getWidth() - j - 1];
        otherSide.setColor(pixels[i][j].getColor());
      }
    }
  }

  /** Method to mirror around a diagonal line from bottom left to top right */
  public void mirrorDiagonal() {
    // add your code here
    System.out.println(this.getWidth());
    System.out.println(this.getHeight());

    Pixel[][] pixels = this.getPixels2D();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < getHeight(); j++) {
        Pixel current = pixels[i][j];
        current.setColor(pixels[j][i].getColor());

      }
    }

  }

  /**
   * Method to mirror just part of a picture of a temple to fix the broken part of
   * the temple
   */
  public void mirrorTemple() {
    // add your code here
    Pixel[][] pixels = this.getPixels2D();
    for (int i = 0; i < 98; i++) {
      for (int j = 0; j < getWidth() / 2; j++) {
        Pixel otherSide = pixels[i][getWidth() - j - 1];
        otherSide.setColor(pixels[i][j].getColor());

      }
    }

  }

  /**
   * Method to mirror just part of a picture of a snowman, so that it will have
   * four arms instead of two
   */
  public void mirrorArms() { // column 170 and row 153 - > start; column 170 and row 194
    // add your code here
    Pixel[][] pixels = this.getPixels2D();
    for (int i = 153; i < 194; i++) {
      for (int j = 0; j <= 170; j++) {
        Pixel otherPixel = pixels[Math.abs(pixels[i][j].getY() - 190) + 190][j];
        otherPixel.setColor(pixels[i][j].getColor());
      }
    }

    for (int i = 153; i < 194; i++) {
      for (int j = 239; j < 296; j++) {
        // maybe get a midpoint value from under arms by subtracting distance from
        // bottom divide by 2 and add from there
        // getHeight()/2 + (getHeight() - i)][
        Pixel otherPixel = pixels[Math.abs(pixels[i][j].getY() - 190) + 190][j];
        otherPixel.setColor(pixels[i][j].getColor());

      }
    }

  }

  /** Method to copy the gull in the picture to another location of the picture */
  public void copyGull() {
    // add your code here
    Pixel[][] pixels = this.getPixels2D();

    for (int i = 232; i < 323; i++) {
      for (int j = 238; j < 347; j++) {
        Pixel otherPixel = pixels[i][Math.abs(pixels[i][j].getX() - 347) + 347];
        otherPixel.setColor(pixels[i][j].getColor());

      }
    }
  }

  /** Method to create a collage of several pictures */
  public void createCollage() {
    // // add your code here
    Pixel[][] pixels = this.getPixels2D();
    Picture flowerOne = new Picture("flower1.jpg");
    Picture flowerTwo = new Picture("flower2.jpg");

    Pixel[][] pixelFlowerOne = flowerOne.getPixels2D();
    Pixel[][] pixelFlowerTwo = flowerTwo.getPixels2D();

    System.out.println(flowerOne.getWidth());
    System.out.println(flowerOne.getHeight());

    System.out.println(flowerTwo.getWidth());
    System.out.println(flowerTwo.getHeight());

    setFlower(pixels, pixelFlowerOne, 0, 0);
    setFlower(pixels, pixelFlowerTwo, 101, 0);
    setFlower(pixels, pixelFlowerOne, 201, 0);
    for (int i = 0; i < pixelFlowerTwo.length; i++) {
      for (int j = 0; j < pixelFlowerTwo[0].length; j++) {
        if (pixelFlowerTwo[i][j].getRed() > 230 && pixelFlowerTwo[i][j].getBlue() > 230
            && pixelFlowerTwo[i][j].getGreen() > 230) {
          pixelFlowerTwo[i][j].setBlue(0);
          pixelFlowerTwo[i][j].setRed(255);
          pixelFlowerTwo[i][j].setGreen(255);
        }

      }
    }
    setFlower(pixels, pixelFlowerTwo, 301, 0);
    setFlower(pixels, pixelFlowerOne, 401, 0);

    for (int i = 0; i < pixels.length; i++) {
      for (int j = 1; j <= 100; j++) {
        Pixel other = pixels[i][getWidth() - j];
        other.setColor(pixels[i][j].getColor());
      }
    }

  }

  public void setFlower(Pixel[][] img, Pixel[][] flower, int row, int col) {

    for (int i = 0; i < flower.length; i++) {
      for (int j = 0; j < flower[i].length; j++) {
        if (row >= img.length) {
          return;
        }

        img[row][col].setColor(flower[i][j].getColor()); // col becomes too big, need to reset
        col++;
      }
      row++;
      col -= 100;
    }
  }

  /**
   * Method to replace the blue background with the pixels in the newBack picture
   * 
   * @param newBack the picture to copy from
   */
  public void chromakey(Picture newBack) {
    // add your code here
    Pixel[][] pixels = this.getPixels2D();
    Pixel[][] pixelsNew = newBack.getPixels2D();

    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        int red = pixels[i][j].getRed();
        int green = pixels[i][j].getGreen();
        int blue = pixels[i][j].getBlue();

        // if (red <= 29 && 15 <= green && green <= 50 && 10 <= blue && blue <= 85) {
        // pixels[i][j].setColor(pixelsNew[i][j].getColor());
        // }
        if (blue > red && blue > green) {
          pixels[i][j].setColor(pixelsNew[i][j].getColor());
        }
      }

    }

  }

  /** Method to decode a message hidden in the red value of the current picture */
  public void decode() {
    // add your code here
    Pixel[][] pixels = this.getPixels2D();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        if (pixels[i][j].getRed() % 2 == 1) {
          pixels[i][j].setRed(255);
          pixels[i][j].setGreen(255);
          pixels[i][j].setBlue(255);
        } else {
          pixels[i][j].setRed(0);
          pixels[i][j].setGreen(0);
          pixels[i][j].setBlue(0);
        }
      }
    }
  }

  /**
   * Hide a black and white message in the current picture by changing the green
   * to even and then setting it to odd if the message pixel is black
   * 
   * @param messagePict the picture with a message
   */
  public void encodeGreen(Picture messagePict) {
    // add your code here
    Pixel[][] pixels = this.getPixels2D();
    Pixel[][] pixelMessage = messagePict.getPixels2D();
    for (int i = 0; i < pixelMessage.length; i++) {
      for (int j = 0; j < pixelMessage[i].length; j++) {
        int blue = pixelMessage[i][j].getBlue();
        int red = pixelMessage[i][j].getRed();
        int green = pixelMessage[i][j].getGreen();
        if (blue == 0 && red == 0 && green == 0) {
          if (pixels[i][j].getGreen() % 2 == 0) {
            pixels[i][j].setGreen(pixels[i][j].getGreen() + 1);
          }
        } else {
          if (pixels[i][j].getGreen() % 2 != 0) {
            pixels[i][j].setGreen(pixels[i][j].getGreen() + 1);
          }
        }
      }
    }
  }

  public void decodeGreen() {
    // add your code here
    Pixel[][] pixels = this.getPixels2D();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        if (pixels[i][j].getGreen() % 2 == 1) {
          pixels[i][j].setRed(0);
          pixels[i][j].setGreen(0);
          pixels[i][j].setBlue(0);
        } else {
          pixels[i][j].setRed(255);
          pixels[i][j].setGreen(255);
          pixels[i][j].setBlue(255);
        }
      }
    }
  }

}
