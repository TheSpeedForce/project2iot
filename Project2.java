import java.io.*;
import java.util.*; // Import the Scanner class to read text files


public class Project2 {
    public static void main(String[] args) {
        
        // Create class object to invoke methods
        Project2 project2 = new Project2();

        // Initalize required variables
        String command, filename;
        float temp;

        // Start reading temperature values
        while (true) {

            // Create command to get temperature reading
            filename = "tempSensor.py";
            command = project2.makeCommand(filename);
            project2.runPython(command);
               
            // Get temperature from file
            filename = "temperatures.txt";
            temp = project2.getTemp(filename);
            
            System.out.println("Temperature: "+temp);

            // check temp value
            if (temp > 78){

                // LED on if >78
                filename = "ledOn.py";
                command = project2.makeCommand(filename);
                project2.runPython(command);;
            }

            // LED off if <=78
            else if (temp <= 78){
                filename = "ledOff.py";
                command = project2.makeCommand(filename);
                project2.runPython(command);
            }

            // Print error if fails
            else {
                System.out.println("\nError in logic or circuit.");
            }

        }
            
    }// end main

    

    public String makeCommand (String filename){
        String command = "python3 "+ filename;
        return command;
    }

    public void runPython (String command) {

        // create a process that runs the file above
        try {

            // print a message
            System.out.println("\n-------- Executing Python Command ----------");
   
            // create a process and execute notepad.exe
            Process process = Runtime.getRuntime().exec (command);
            BufferedReader buf = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line=buf.readLine())!=null) {
                System.out.println(line);
            }

            // print another message
            System.out.println("------- Python Command Executed Sucessfully. -------- ");
   
        } 
        catch (Exception ex) {
            System.out.println("\n------- Python Command Execution Failed. -------- ");
            ex.printStackTrace();
        }

    } 

    public float getTemp(String filename){
        float temp = 0;
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                try {
                    temp = Float.parseFloat(data);
                    
                }
                catch (Exception e){
                    System.out.println(e);
                }

            }
            myReader.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("\nAn error occurred.");
            e.printStackTrace();

        }
        return temp;
    }
} // end class Project2

