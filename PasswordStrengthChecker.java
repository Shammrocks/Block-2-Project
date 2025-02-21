//Imports
import java.util.Scanner;
import java.lang.Math;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PasswordStrengthChecker
    {public static String userName;
    public static String name;
    public static String birthYear;
    public static String password;
    public static double passwordStrength;



    public static void main(String[] args)
        {//Get user info and password
        Scanner scanner = new Scanner(System.in);  //Create a Scanner object
        System.out.println("Welcome to the password strength checker, please enter some information followed by your password to check its strength!");

        System.out.println("\n" + "Please enter some username:");
        userName = scanner.nextLine();  //Read user input

        System.out.println("\n" + "Please enter a full name:");
        name = scanner.nextLine();  //Read user input

        System.out.println("\n" + "Please enter the a year of birth:");
        birthYear = scanner.nextLine();  //Read user input

        System.out.println("\n" + "Finally, please enter a password you would like to test the strength of:");
        password = scanner.nextLine();  //Read user input


        //Check password strength
        passwordStrength = 0;

        checkPassLength(); //-0.000... to +2^(n-10), where n = length to passwordStrength
        checkPassTypes(); //+1 to +16 to passwordStrength
        checkCommonPass(); //+10 or -10 to passwordStrength
        checkPassPersonal(); //+10 or -10 to passwordStrength


        //awesome, password score of 100 or greater
        if(passwordStrength >= 100)
            System.out.println("Your passwords strength is awsome!");

        //good, password score of 50 to 99
        else if(passwordStrength >= 50 && passwordStrength <= 99)
            System.out.println("Your passwords strength is good!");

        //ok, password score of 20 to 49
        else if(passwordStrength >= 20 && passwordStrength <= 49)
            System.out.println("Your passwords strength is ok!");

        //bad, password score of 10 to 19
        else if(passwordStrength >= 10 && passwordStrength <= 19)
            System.out.println("Your passwords strength is bad!");

        //terrible, password score of 10 or less
        else
            System.out.println("Your passwords strength is terrible!");
        }



    //Check password length
    public static void checkPassLength()
        {if(password.length() >= 10)
            passwordStrength += Math.pow(2, ((password.length() - 10)));

        else
            passwordStrength -= Math.pow(2, ((password.length() - 10)));
        }



    //Check for password char types
    public static  void checkPassTypes()
        {int numCharType = 0;

        for(int i = 0; i < password.length(); i++) //Check if there are any lowercase letters
            {if(Character.isLowerCase((password.charAt(i))))
                {numCharType++;
                break;
                }
            }

        for(int i = 0; i < password.length(); i++) //Check if there are any uppercase letters
            {if(Character.isUpperCase((password.charAt(i))))
                {numCharType++;
                break;
                }
            }

        for(int i = 0; i < password.length(); i++) //Check if there are any numbers
            {if(Character.isDigit((password.charAt(i))))
                {numCharType++;
                break;
                }
            }

        for(int i = 0; i < password.length(); i++) //Check if there are any symbols
            {if(!Character.isLetterOrDigit((password.charAt(i))))
                {numCharType++;
                break;
                }
            }

            passwordStrength += Math.pow(2, numCharType);
        }



    //Check if password contains any words within a given txt such as a dictionary of common passwords
    public static void checkCommonPass()
        {boolean passed = true;
        String filePath = "commonPasswords.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
            {String line;
            while((line = reader.readLine()) != null)
                if(password.contains(line))
                    {passwordStrength -= 10;
                    passed = false;
                    break;
                    }

            if(passed)
                passwordStrength += 10;
            }

        catch(IOException e)
            {System.err.println("An error occurred while checking your password with our database: " + e.getMessage());
            }
        }



    //Check if password contains any personal info
    public static void checkPassPersonal()
        {boolean passed = true;
        String[] personalInfo = (name + " " + userName + " " + birthYear).split(" ");
        for(int i = 0; i < personalInfo.length; i++)
            if(password.contains(personalInfo[i]))
                {passwordStrength -= 10;
                passed = false;
                break;
                }

        if(passed)
            passwordStrength += 10;
        }
    }