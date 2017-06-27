/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */

import java.util.Iterator;

public class RunLengthEncoding implements Iterable {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
  private SenDList pixelRunsDList;
  private int width, height;

  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with two parameters) constructs a run-length
   *  encoding of a black PixImage of the specified width and height, in which
   *  every pixel has red, green, and blue intensities of zero.
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   */

  public RunLengthEncoding(int width, int height) {
    // Your solution here.
      this(width, height, new int[]{0}, new int[]{0}, new int[]{0},
          new int[]{width * height});
  }

  /**
   *  RunLengthEncoding() (with six parameters) constructs a run-length
   *  encoding of a PixImage of the specified width and height.  The runs of
   *  the run-length encoding are taken from four input arrays of equal length.
   *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
   *  blue[i].
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   *  @param red is an array that specifies the red intensity of each run.
   *  @param green is an array that specifies the green intensity of each run.
   *  @param blue is an array that specifies the blue intensity of each run.
   *  @param runLengths is an array that specifies the length of each run.
   *
   *  NOTE:  All four input arrays should have the same length (not zero).
   *  All pixel intensities in the first three arrays should be in the range
   *  0...255.  The sum of all the elements of the runLengths array should be
   *  width * height.  (Feel free to quit with an error message if any of these
   *  conditions are not met--though we won't be testing that.)
   */

  public RunLengthEncoding(int width, int height, int[] red, int[] green,
                           int[] blue, int[] runLengths) {
      // Your solution here.
      this.width = width;
      this.height = height;

      pixelRunsDList = new SenDList();
      for(int i=0; i < runLengths.length; i++)  {
          SenPixel runPixel = new SenPixel(red[i], green[i], blue[i], runLengths[i]);
          pixelRunsDList.insertBack(runPixel);
      }
  }

  /**
   *  getWidth() returns the width of the image that this run-length encoding
   *  represents.
   *
   *  @return the width of the image that this run-length encoding represents.
   */

  public int getWidth() {
    // Replace the following line with your solution.
    return width;
  }

  /**
   *  getHeight() returns the height of the image that this run-length encoding
   *  represents.
   *
   *  @return the height of the image that this run-length encoding represents.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return height;
  }

  /**
   *  iterator() returns a newly created RunIterator that can iterate through
   *  the runs of this RunLengthEncoding.
   *
   *  @return a newly created RunIterator object set to the first run of this
   *  RunLengthEncoding.
   */
  public RunIterator iterator() {
      // Replace the following line with your solution.
      return new RunIterator(pixelRunsDList.head);
      // You'll want to construct a new RunIterator, but first you'll need to
      // write a constructor in the RunIterator class.
  }

  /**
   *  toPixImage() converts a run-length encoding of an image into a PixImage
   *  object.
   *
   *  @return the PixImage that this RunLengthEncoding encodes.
   */
  public PixImage toPixImage() {
      // Replace the following line with your solution.
      PixImage image = new PixImage(width, height);
      RunIterator it = iterator();
      int col = 0, row = 0;
      while(it.hasNext()) {
          int[] runPixelInfoArray = it.next();
          int runLength = runPixelInfoArray[0];
          short r = (short)runPixelInfoArray[1];
          short g = (short)runPixelInfoArray[2];
          short b = (short)runPixelInfoArray[3];
          for(int i=0; i<runLength; i++) {
              image.setPixel(col, row, r, g, b);
              col++;
              if (col == width) { col = 0; row++;  }
          } 
      }

      return image;
  }

  /**
   *  toString() returns a String representation of this RunLengthEncoding.
   *
   *  This method isn't required, but it should be very useful to you when
   *  you're debugging your code.  It's up to you how you represent
   *  a RunLengthEncoding as a String.
   *
   *  @return a String representation of this RunLengthEncoding.
   */
  public String toString() {
    // Replace the following line with your solution.
      String rleString = "RLE: " + pixelRunsDList.size + " runs\n";

      SenPixel nodePixel;
      SenDListNode curDListNode = pixelRunsDList.head;
      int totalRunsCount = 0;
      while(curDListNode != null) {
          totalRunsCount++;
          nodePixel = (SenPixel)curDListNode.item;
          rleString += "\trunsCount " + totalRunsCount + " :\t"
                        + "runLength :\t" + nodePixel.getRunLengthEncodingCount()
                        + "\t" + nodePixel + "\n";

          curDListNode = curDListNode.next;
      }

      return rleString;
  }


  /**
   *  The following methods are required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of a specified PixImage.
   * 
   *  Note that you must encode the image in row-major format, i.e., the second
   *  pixel should be (1, 0) and not (0, 1).
   *
   *  @param image is the PixImage to run-length encode.
   */
  public RunLengthEncoding(PixImage image) {
      width = image.getWidth();
      height = image.getHeight();
      pixelRunsDList = new SenDList();

      int curRunLength = 0;
      SenPixel basePixel = image.getPixel(0,0);
      for (int row=0; row<height; row++) {
          for (int col=0; col<width; col++)  {

              if(basePixel.equals(image.getPixel(col, row)))  {
                  curRunLength++;
              }else {
                  pixelRunsDList.insertBack(new SenPixel(basePixel, curRunLength));
                  curRunLength = 1;
                  basePixel = image.getPixel(col, row);
              }
          }
      }
      // insert the last runs
      pixelRunsDList.insertBack(new SenPixel(basePixel, curRunLength));

      // Your solution here, but you should probably leave the following line
      // at the end.
      check();
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same RGB intensities, or if the sum of
   *  all run lengths does not equal the number of pixels in the image.
   */
  public void check() {
      // Your solution here.
      int totalRunsLength = 0;
      int leftR = -1, leftG = -1, leftB = -1;
      RunIterator it = iterator();

      int runsCount = 0;
      while (it.hasNext()) {
          int[] rightRun = it.next();
          runsCount++;
          totalRunsLength += rightRun[0];
          if (leftR == rightRun[1] && leftG == rightRun[2] && leftB == rightRun[3]) {
              System.err.println("The " + runsCount + " Run and its next have the same RGB intensities");
              System.err.println("Run : \n " + this);
              return;
          }
          leftR = rightRun[1];
          leftG = rightRun[2];
          leftB = rightRun[3];
      }
      if (totalRunsLength != width * height) {
          System.err.println("The sum of all run lengths is not equal to the number of pixels in the image.");
          System.err.println("totalRunsLength = " + totalRunsLength + ",   width = " + width + ",    height" + height);
          System.err.println("rle.this = \n" + this);
      }
  }


  /**
   *  The following method is required for Part IV.
   */

  /**
   *  setPixel() modifies this run-length encoding so that the specified color
   *  is stored at the given (x, y) coordinates.  The old pixel value at that
   *  coordinate should be overwritten and all others should remain the same.
   *  The updated run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs with exactly the same RGB color.
   *
   *  @param x the x-coordinate of the pixel to modify.
   *  @param y the y-coordinate of the pixel to modify.
   *  @param red the new red intensity to store at coordinate (x, y).
   *  @param green the new green intensity to store at coordinate (x, y).
   *  @param blue the new blue intensity to store at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
      // Your solution here, but you should probably leave the following line at the end.
      SenDListNode curRunNode = pixelRunsDList.head;
      if (curRunNode == null)   {
          System.out.println("Error, no valid image info in this rle!!");
          return;
      }
      int runLengthsTotal = ((SenPixel)curRunNode.item).getRunLengthEncodingCount();
      int pixelPositionInLength = y * width + x + 1;

      /*** 1. Find which runNode the targetPixel stays, and give it to curRunNode  *******/
       while(runLengthsTotal < pixelPositionInLength) {
           if(curRunNode.next != null)   {
               runLengthsTotal += ((SenPixel)curRunNode.next.item).getRunLengthEncodingCount();
               curRunNode = curRunNode.next;
           }else {
               System.err.println("Error, cann't find a valid pixel in this rleImage!!");
               System.err.println("x = " + x + ",   y = " + y + ",   pixelPositionInLength = " + pixelPositionInLength);
               System.err.println("runLengthsTotal = " + runLengthsTotal);
               break;
           }
      }

      /** 2. If the new pixelColor is the same, no need to change pixelRunsDList ***/
      SenPixel targetPixel = new SenPixel(red, green, blue);
      if (targetPixel.equals(curRunNode.item)) {
          return;
      }
      /** 3. Find out the neiborsInfo of the target pixel    **********************/
      boolean hasPrevRunNode = (curRunNode.prev != null);
      boolean hasNextRunNode = (curRunNode.next != null);
      boolean samePixelAsPrevRunNode = false;
      boolean samePixelAsNextRunNode = false;
      int prevNodeRunLength = -1, nextNodeRunLength = -1;
      if(hasPrevRunNode) {
          samePixelAsPrevRunNode = targetPixel.equals(curRunNode.prev.item);
          prevNodeRunLength = ((SenPixel)curRunNode.prev.item).getRunLengthEncodingCount();
      }
      if(hasNextRunNode) {
          samePixelAsNextRunNode = targetPixel.equals(curRunNode.next.item);
          nextNodeRunLength = ((SenPixel)curRunNode.next.item).getRunLengthEncodingCount();
      }
      int curNodeRunLength = ((SenPixel)curRunNode.item).getRunLengthEncodingCount();

      /** 4. Seperate: if the targetPixel itself represets a single runNode ******/
      if (curNodeRunLength == 1) {
          if (samePixelAsPrevRunNode && samePixelAsNextRunNode) {
              /** Combine THREE nodes as curRunNode, then setPixel **/
              ((SenPixel)curRunNode.prev.item).setRunLengthEncodingCount(
                  prevNodeRunLength + 1 + nextNodeRunLength
              );
              curRunNode.prev.next = curRunNode.next.next;
              pixelRunsDList.remove(curRunNode.next);
              pixelRunsDList.remove(curRunNode);
          }else if (!samePixelAsPrevRunNode && !samePixelAsNextRunNode) {
              /** Combine ZERO nodes, Still single, setPixel directly **/
              targetPixel.setRunLengthEncodingCount(1);
              curRunNode.item = targetPixel;
          }else {
              if(samePixelAsPrevRunNode)    {
                  /** Need to combine curRunNode.prev and curRunNode **/
                  ((SenPixel)curRunNode.prev.item).setRunLengthEncodingCount(
                      prevNodeRunLength + 1
                  );
                  curRunNode.prev.next = curRunNode.next;
                  pixelRunsDList.remove(curRunNode);
              }else if (samePixelAsNextRunNode) {
                  /** Need to combine curRunNode.next and curRunNode **/
                  ((SenPixel)curRunNode.next.item).setRunLengthEncodingCount(
                      nextNodeRunLength + 1
                  );
                  curRunNode.next.prev = curRunNode.prev;
                  pixelRunsDList.remove(curRunNode);
              }
          }
          return;
      }else

      /** 5. Combine at most TWO nodes, either with Prev or Next *************/
      if (pixelPositionInLength == runLengthsTotal - curNodeRunLength + 1)  {
          /** The targetPixel is at the Beginning of a runNode **/
          ((SenPixel)curRunNode.item).setRunLengthEncodingCount(
              curNodeRunLength - 1
          );

          if(samePixelAsPrevRunNode)    {
              /** targetPixel will be added into curRunNode.prev **/
              ((SenPixel)curRunNode.prev.item).setRunLengthEncodingCount(
                  prevNodeRunLength + 1
              );
          }else { // targetPixel is at the beginning of curRunNode
              /** targetPixel will be a newRunNode before curRunNode **/
              targetPixel.setRunLengthEncodingCount(1);
              pixelRunsDList.insertBefore(curRunNode, targetPixel);
         }
      }else if (pixelPositionInLength == runLengthsTotal)  {
          /** The targetPixel is at the End of a runNode **/
          ((SenPixel)curRunNode.item).setRunLengthEncodingCount(
              curNodeRunLength - 1
          );

          if(samePixelAsNextRunNode)    {
              /** targetPixel will be added into curRunNode.next **/
              ((SenPixel)curRunNode.next.item).setRunLengthEncodingCount(
                  nextNodeRunLength + 1
              );
          }else { // targetPixel is at the beginning of curRunNode
              /** targetPixel will be a newRunNode before curRunNode **/
              targetPixel.setRunLengthEncodingCount(1);
              pixelRunsDList.insertAfter(curRunNode, targetPixel);
          }
      }else {
          /** The targetPixel is in the middle of a runNode **/
          SenPixel curOldPixel = (SenPixel)curRunNode.item;
          int curNodeHeadPixelPositionInLength = runLengthsTotal - curNodeRunLength + 1;
          int leftLength = pixelPositionInLength - curNodeHeadPixelPositionInLength;
          int rightLength = runLengthsTotal - pixelPositionInLength;

          pixelRunsDList.insertBefore(curRunNode, new SenPixel(curOldPixel, leftLength));
          pixelRunsDList.insertAfter(curRunNode, new SenPixel(curOldPixel, rightLength));

          targetPixel.setRunLengthEncodingCount(1);
          curRunNode.item = targetPixel;
      }
      return;
  }


  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * setAndCheckRLE() sets the given coordinate in the given run-length
   * encoding to the given value and then checks whether the resulting
   * run-length encoding is correct.
   *
   * @param rle the run-length encoding to modify.
   * @param x the x-coordinate to set.
   * @param y the y-coordinate to set.
   * @param intensity the grayscale intensity to assign to pixel (x, y).
   */
  private static void setAndCheckRLE(RunLengthEncoding rle,
                                     int x, int y, int intensity) {
      rle.setPixel(x, y,
                 (short) intensity, (short) intensity, (short) intensity);
      rle.check();
  }

  /**
   * main() runs a series of tests of the run-length encoding code.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 3, 6 },
                                                   { 1, 4, 7 },
                                                   { 2, 5, 8 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x3 image.  Input image:");
    System.out.print(image1);
    RunLengthEncoding rle1 = new RunLengthEncoding(image1);
    rle1.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3,
           "RLE1 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(image1.equals(rle1.toPixImage()),
           "image1 -> RLE1 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 42);
    image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           /*
                       array2PixImage(new int[][] { { 42, 3, 6 },
                                                    { 1, 4, 7 },
                                                    { 2, 5, 8 } })),
           */
           "\n\n\nSetting RLE1[0][0] = 42 fails.\nimage1: \n"
               + image1 + "\nrle1.toPixImage() : \n" + rle1.toPixImage() + "\n\n\n\n");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 0, 42);
    image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][0] = 42 fails. \nimage1: \n"
          + image1 + "\nrle1.toPixImage() : \n" + rle1.toPixImage() + "\n\n\n\n");

//      System.err.println("\n\n\nSetting check RLE1[1][0] = 42.\nimage1: \n"
//          + image1 + "\nrle1.toPixImage() : \n" + rle1.toPixImage() + "\n\n\n\n");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 1, 2);
    image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][1] = 2 fails.");

//      System.err.println("\n\n\nSetting check RLE1[0][1] = 2.\nimage1: \n"
//          + image1 + "\nrle1.toPixImage() : \n" + rle1.toPixImage() + "\n\n\n\n");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 0);
    image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][0] = 0 fails.");
//    System.err.println("\n\n\nSetting check RLE1[0][0] = 0.\nimage1: \n"
//          + image1 + "\nrle1.toPixImage() : \n" + rle1.toPixImage() + "\n\n\n\n");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 7);
    image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 7 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 42);
    image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 2, 42);
    image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][2] = 42 fails.");


    PixImage image2 = array2PixImage(new int[][] { { 2, 3, 5 },
                                                   { 2, 4, 5 },
                                                   { 3, 4, 6 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on another 3x3 image.  Input image:");
    System.out.print(image2);
    RunLengthEncoding rle2 = new RunLengthEncoding(image2);
    rle2.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
           "RLE2 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(rle2.toPixImage().equals(image2),
           "image2 -> RLE2 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 0, 1, 2);
    image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 2, 0, 2);
    image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[2][0] = 2 fails.");


    PixImage image3 = array2PixImage(new int[][] { { 0, 5 },
                                                   { 1, 6 },
                                                   { 2, 7 },
                                                   { 3, 8 },
                                                   { 4, 9 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 5x2 image.  Input image:");
    System.out.print(image3);
    RunLengthEncoding rle3 = new RunLengthEncoding(image3);
    rle3.check();
    System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
    doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
           "RLE3 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 5x2 encoding.");
    doTest(rle3.toPixImage().equals(image3),
           "image3 -> RLE3 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 4, 0, 6);
    image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[4][0] = 6 fails.\nimage3: \n"
          + image3 + "\nrle3.toPixImage() : \n" + rle3.toPixImage() + "\n\n\n\n");



    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 1, 6);
    image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][1] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 0, 1);
    image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][0] = 1 fails.");


    PixImage image4 = array2PixImage(new int[][] { { 0, 3 },
                                                   { 1, 4 },
                                                   { 2, 5 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x2 image.  Input image:");
    System.out.print(image4);
    RunLengthEncoding rle4 = new RunLengthEncoding(image4);
    rle4.check();
    System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
    doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
           "RLE4 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x2 encoding.");
    doTest(rle4.toPixImage().equals(image4),
           "image4 -> RLE4 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 2, 0, 0);
    image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[2][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 0);
    image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 1);
    image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 1 fails.");

//      System.err.println("\n\n\nSetting check RLE1[1][0] = 0.\nimage4: \n"
//          + image4 + "\nrle4.toPixImage() : \n" + rle4.toPixImage() + "\n\n\n\n");

  }
}
