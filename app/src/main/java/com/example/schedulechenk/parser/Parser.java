package com.example.schedulechenk.parser;

import android.util.Log;

import com.example.schedulechenk.models.ComplexModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Document complexDocument;
    private Document groupDocument;
    private Document sheduleDocument;

    List<ComplexModel> complexModels = new ArrayList<>();

    private String complexUrl = "https://pronew.chenk.ru/blocks/manage_groups/website/view1.php";
    private String groupUrl = "https://pronew.chenk.ru/blocks/manage_groups/website/view1.php";
    private String ScheduleUrl = "https://pronew.chenk.ru/blocks/manage_groups/website/view1.php";


    public List<ComplexModel> getComplexWeb() {
        Thread getComplexThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //получаем две кнопки с меин стр сайта
                    complexDocument = Jsoup.connect(complexUrl).get();
                    Elements complexButtons = complexDocument.getElementsByClass("spec-select-block gray-gradient");
                    //вытащить айдишники group_id
                    Log.d("complexParse", "Первый " + complexButtons.get(0).text() +
                            "\nВторой " + complexButtons.get(1).text());

                    for (Element name : complexButtons) {
                        ComplexModel complexModel = new ComplexModel();
                        complexModel.setComplexName(name.text());
                        if (name.text().equals("КОМПЛЕКС ул. Блюхера 91"))
                            complexModel.setComplexID(1);
                        else
                            complexModel.setComplexID(3);
                        complexModels.add(complexModel);
                    }
                } catch (IOException e) {e.printStackTrace();}
            }
        });
        getComplexThread.start();

        try {getComplexThread.join();}
        catch (InterruptedException e) { e.printStackTrace();}

        return  complexModels;
    }
}
