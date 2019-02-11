package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;

public class SiteNewsData {
    private static SiteNewsData siteNewsData=new SiteNewsData();
    private static  String filename="SiteNews.data";
    private long serialVersionUID = 1L;
    private SiteNews siteNews;

    private ObservableList<SiteNews>news= FXCollections.observableArrayList();

    public static SiteNewsData getSiteNewsData() {  return siteNewsData;   }

    public ObservableList<SiteNews> getSiteNews(){ return news; }



    public void saveSiteNewsData()throws IOException{
        //SiteNewsData.getSiteNewsData().getSiteNews().add(new SiteNews());
        Path locPath= FileSystems.getDefault().getPath(filename);
        try(ObjectOutputStream locFile=new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))){
            for(SiteNews sn:news){
                locFile.writeObject(sn);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadSiteNewsData() throws IOException {
        Path locPath = FileSystems.getDefault().getPath(filename);
        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))) {
            boolean eof = false;
            while(!eof) {
                try {
                    SiteNews user = (SiteNews) locFile.readObject();
                    news.add(user);
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


}