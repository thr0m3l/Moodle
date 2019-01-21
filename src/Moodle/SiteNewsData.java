package Moodle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class SiteNewsData {
    private static SiteNewsData siteNewsData=new SiteNewsData();
    private static  String filename="SiteNews.data";

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
}