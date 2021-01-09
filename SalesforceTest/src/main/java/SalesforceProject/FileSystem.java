package SalesforceProject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class FileSystem {
    //  Global variables
    static FileTreeNode currentDir, rootDir;

    public static void main(String[] args) throws Exception {
        Setup();

        parseInput();
    }

    public static void Setup() throws Exception {
        try {
            // Creating root dir to initialize
            rootDir = new FileTreeNode("/", true, false, null);

            // Setting current dir as root directory
            currentDir = rootDir;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Parse the input of command
    public static void parseInput() {
        // Scanner to read command input
       Scanner sc = new Scanner(System.in);
       String command = sc.nextLine();

        if (!command.equals("quit")) {
            parseCommand(command);
            // Recursion to read another command until we get "quit"
            parseInput();
        }
    }

    // Parse which command to run based on the input
    public static void parseCommand(String command){
        String[] cmdSplit = command.split(" ");
        switch (cmdSplit[0]) {

            case "pwd":
                pwdCommand();
                break;

            case "ls":
                lsCommand(cmdSplit);
                break;

            case "mkdir":
                mkdirCommand(cmdSplit);
                break;

            case "cd":
                cdCommand(cmdSplit);
                break;

            case "touch":
                touchCommand(cmdSplit);
                break;

            default:
                System.out.println("Invalid command " + command);
        }
    }

    // Logic for executing pwdCommand
    private static void pwdCommand() {
        FileTreeNode tempParentNode = currentDir.getParent();
        String path = currentDir.getName();

        while (tempParentNode != null) {
            path = tempParentNode.getName() + "'/'" + path;
            tempParentNode = tempParentNode.getParent();
        }

        // Printing the path of current dir
        System.out.println(path);
    }

    // Logic for executing lsCommand
    private static void lsCommand(String[] cmdArray) {
        // printing all children of current directory
        currentDir.printChildren();
    }

    // Logic for executing mkDirCommand
    private static void mkdirCommand(String[] cmdArray) {
        if (cmdArray.length == 1) {
            System.out.println("Invalid mkdir command. Enter new directory name.");
        } else {
            try {
                // Adding a new directory in current directory
                currentDir.addChild(new FileTreeNode(cmdArray[1], false, false, currentDir));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Logic for executing cd Command
    private static void cdCommand(String[] cmdArray) {
        if (cmdArray.length == 1) {
            System.out.println("Invalid cd command.");
        } else {
            try {
                // 3 cases for cd command
                switch (cmdArray[1]) {
                    // go to root
                    case "/":
                        currentDir = rootDir;
                        break;

                    // Go to parent
                    case "..":
                        currentDir = currentDir.getParent();
                        break;

                    // Go to specific dir path
                    default:
                        currentDir = currentDir.getChild(cmdArray[1]);
                        break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Logic for executing touch command
    private static void touchCommand(String[] cmdArray){
        if (cmdArray.length == 1) {
            System.out.println("Invalid touch command.");
        }
        else{
            try {
                // Creating a new file
                File file = new File(cmdArray[1]);
                if(!file.exists()){
                    new FileOutputStream(file).close();
                    currentDir.addChild(new FileTreeNode(cmdArray[1], false, true, currentDir));
                } else {
                    System.out.println("File with same name already exists.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
