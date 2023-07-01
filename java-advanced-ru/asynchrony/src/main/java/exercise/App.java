package exercise;

import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String f1Path, String f2Path, String destPath) {
        CompletableFuture<String> futureFile1 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(Path.of(f1Path));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> futureFile2 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(Path.of(f2Path));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return futureFile1.thenCombine(futureFile2, (data1, data2) -> {
                    try {
                        String totalData = String.format("%s%s", data1, data2);
                        Files.writeString(Path.of(destPath), totalData, StandardOpenOption.CREATE);
                        return totalData;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .exceptionally( e -> {
                    System.out.println("Exception: " + e.getMessage());
                    return null;
                });
    }

    public static CompletableFuture<Long> getDirectorySize(String path) {
        return CompletableFuture.supplyAsync(() -> {
            Long size;
            try {
                size = Files.walk(Path.of(path), 1)
                        .filter(Files::isRegularFile)
                        .mapToLong(file -> {
                            try {
                                return Files.size(file);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .sum();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return size;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles("src/main/resources/file1.txt", "src/main/resources/file2.txt", "src/main/resources/done.txt");
        // END
    }
}

