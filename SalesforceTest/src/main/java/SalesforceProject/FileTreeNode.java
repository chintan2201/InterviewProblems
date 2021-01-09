package SalesforceProject;

import java.util.ArrayList;

// This class is representing Tree view data structure of file system
public class FileTreeNode {

    // Node name of file/dir
    private String nodeName;

    // list of children in this node
    private ArrayList<FileTreeNode> childrenNodes;

    // Is this dir is root or not?
    private boolean isRoot;

    // Is this node is file or not?
    private boolean isFile;

    // Parent node of current file/dir
    private FileTreeNode parent;

    // Initialization of new node (file/dir)
    public FileTreeNode(String name, boolean isRoot, boolean isFile, FileTreeNode parentNode) throws Exception {

        this.nodeName = name;

        if (isRoot && isFile) {
            Exception ex = new Exception("Node can't be a root directory as well as a file.");
            throw ex;
        }

        this.isFile = isFile;
        this.isRoot = isRoot;

        if (isRoot) {
            this.parent = null;
        } else {
            this.parent = parentNode;
        }

        this.childrenNodes = new ArrayList<FileTreeNode>();
    }

    // Get name of the current node
    public String getName() {
        return this.nodeName;
    }

    // Get the parent of current node
    public FileTreeNode getParent() {
        return this.parent;
    }

    // Print the children of current directory
    public void printChildren() {
        for (FileTreeNode cdf : childrenNodes) {
            String end = "";
            if (!cdf.isFile)
                end = "/";
            System.out.println(cdf.getName() + end);
        }
    }

    // Adding a new child node to current directory
    public void addChild(FileTreeNode treeNode) throws Exception {
        if (this.isFile) {
            Exception fileEx = new Exception("Node can't add child to a file type.");
            throw fileEx;
        }
        childrenNodes.add(treeNode);
    }

    // Get the child node by name
    public FileTreeNode getChild(String childName) throws Exception {
        if (!this.childrenNodes.isEmpty()) {
            for (FileTreeNode childNode : this.childrenNodes) {
                if (childNode.getName().equals(childName))
                    return childNode;
            }
        }

        Exception childNotFoundEx = new Exception(childName + " is not present in this directory.");
        throw childNotFoundEx;
    }
}
