package cs.ttu.vvclass.activities;

@SuppressWarnings("serial")
public class ZeroInputException extends Exception
{
      //Parameterless Constructor
      public ZeroInputException() {}

      //Constructor that accepts a message
      public ZeroInputException(String message)
      {
         super(message);
      }
 }