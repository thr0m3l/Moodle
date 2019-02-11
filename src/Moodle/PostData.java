package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class PostData  {
    private static PostData postData = new PostData();
    private static String fileName = new String("postData.dat");

    private Post lastPost;

    public Post getLastPost() {
        return lastPost;
    }

    public void setLastPost(Post lastPost) {
        this.lastPost = lastPost;
    }

    private ObservableList<Post> posts = FXCollections.observableArrayList();

    public static PostData getPostData() {
        return postData;
    }

    public static void setPostData(PostData postData) {
        PostData.postData = postData;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        PostData.fileName = fileName;
    }

    public ObservableList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ObservableList<Post> posts) {
        this.posts = posts;
    }
    public void loadPostData() throws IOException {
        Path locPath = FileSystems.getDefault().getPath(fileName);
        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))) {
            boolean eof = false;
            while(!eof) {
                try {
                    Post post = (Post) locFile.readObject();
                    posts.add(post);
                } catch(EOFException e) {
                    eof = true;
                }
            }
        } catch(InvalidClassException e) {
            System.out.println("InvalidClassException " + e.getMessage());
        } catch(IOException e) {
            System.out.println("IOException " + e.getMessage());
        } catch(ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e.getMessage());
        }
    }
    public void savePostData () throws IOException{
        Path locPath = FileSystems.getDefault().getPath(fileName);
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))) {
            for(Post post : posts) {
                locFile.writeObject(post);
            }

        } catch (IOException e){
            System.out.println("Unable to save PostData" + " " + e.getMessage());
        }
    }
}
