package SalesforceProject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FileSystemTest {

    static FileTreeNode currentDir, rootDir;

    @Test
    void main() throws Exception {
        FileSystem.Setup();
        assertDoesNotThrow(() -> FileSystem.parseCommand("pwd"));
    }

}