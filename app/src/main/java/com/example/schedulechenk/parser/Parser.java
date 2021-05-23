package com.example.schedulechenk.parser;

import android.util.Log;
import android.util.Pair;

import com.example.schedulechenk.models.ComplexModel;
import com.example.schedulechenk.models.CourseModel;
import com.example.schedulechenk.models.GroupModel;
import com.example.schedulechenk.models.PairModel;
import com.example.schedulechenk.models.ScheduleModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Parser {
    private Document complexDocument;
    private Document groupDocument;
    private Document sheduleDocument;

    List<ComplexModel> complexModels = new ArrayList<>();
    List<GroupModel> groupModels = new ArrayList<>();
    List<ScheduleModel> scheduleModels = new ArrayList<>();

    private String complexUrl = "https://pronew.chenk.ru/blocks/manage_groups/website/view1.php";
    private String groupUrl = "https://pronew.chenk.ru/blocks/manage_groups/website/list.php?id=";
    private String scheduleUrl = "https://pronew.chenk.ru/blocks/manage_groups/website/view.php?gr=";


    public List<ComplexModel> getComplexWeb() {
        Thread getComplexThread = new Thread(() -> {
            try {
                //получаем две кнопки с меин стр сайта
                complexDocument = Jsoup.connect(complexUrl).get();
                Elements complexButtons = complexDocument.getElementsByClass("spec-select-block gray-gradient");

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getComplexThread.start();

        try {
            getComplexThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return complexModels;
    }

    public List<CourseModel> getCourse() {
        List<CourseModel> courseModels = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            CourseModel courseModel = new CourseModel();
            courseModel.setCourse(i + " курс:");
            courseModels.add(courseModel);
        }
        return courseModels;
    }

    public List<GroupModel> getGroupWeb(int id) {
        Thread getGroupThread = new Thread(() -> {
            try {
                groupDocument = Jsoup.connect(groupUrl + id).get();
                int size = groupDocument.getElementsByClass("spec-year-block-container").size();
                Element container = groupDocument.getElementsByClass("spec-year-block-container").get(size - 1);
                Elements blocks = container.getElementsByClass("spec-year-block");
                //blocks.get(0) фулл блок с 1м курсом
                //blocks.get(0).children().get(0).text() отдельные элементы первого курса

                for (Element element : blocks) {
                    Log.d("complexParse", "" + element);

                    for (int i = 1; i < element.childrenSize(); i++) {
                        GroupModel groupModel = new GroupModel();
                        groupModel.setComplexId(id);
                        groupModel.setYear(element.children().get(0).text());
                        groupModel.setGroupId(Integer.parseInt(element.children().get(i).attr("group_id")));
                        groupModel.setGroup(element.children().get(i).text());

                        //region logs
                        Log.d("complexParse", "комплекс айди " + id);
                        Log.d("complexParse", "курс обучения  " + element.children().get(0).text());
                        Log.d("complexParse", "айди группы  " + element.children().get(i).attr("group_id"));
                        Log.d("complexParse", "группа   " + element.children().get(i).text());
                        Log.d("complexParse", "--------------- Добавление модели -------------------");
                        //endregion

                        groupModels.add(groupModel);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getGroupThread.start();

        try {
            getGroupThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return groupModels;
    }


    public List<ScheduleModel> getScheduleWeb(int groupId, int complexId) {
        Thread getSchedule = new Thread(() -> {
            try {
                //получаем неделю
                sheduleDocument = Jsoup.connect(scheduleUrl + groupId + "&dep=" + complexId).get();
                //sheduleDocument = Jsoup.connect("https://realpepper.github.io/").get();

                Elements scheduleTable = sheduleDocument.getElementsByTag("td");

                for(Element tdElement:scheduleTable){

                    List<PairModel> pairModels = new ArrayList<>();

                    for(Element lessonElement:tdElement.children().get(1).children()){

                        PairModel pairModel = new PairModel();


                        Elements firstElementInLessonDiv = lessonElement.children().first().children();

                        if(lessonElement.children().last().children().size() != 0){
                            Elements secondElementInLessonDiv = lessonElement.children().last().children().get(0).children();
                            Elements thridElementInLessonDiv = lessonElement.children().last().children().get(1).children();

                            pairModel.setDiscipline(secondElementInLessonDiv.first().text());

                            if(lessonElement.getElementsByTag("sup").size() != 0)
                                pairModel.setIsCancel(secondElementInLessonDiv.last().text());

                            pairModel.setPair(firstElementInLessonDiv.get(0).text());

                            pairModel.setStartTime(firstElementInLessonDiv.get(1).text());

                            pairModel.setEndTime(firstElementInLessonDiv.get(2).text());

                            pairModel.setEducator(thridElementInLessonDiv.first().children().first().text());

                            pairModel.setCabinet(thridElementInLessonDiv.last().text());

                        }else {
                            pairModel.setCabinet("отсутствует");
                            pairModel.setPair(firstElementInLessonDiv.get(0).text());
                            pairModel.setStartTime(firstElementInLessonDiv.get(1).text());
                            pairModel.setEndTime(firstElementInLessonDiv.get(2).text());
                        }
                        pairModels.add(pairModel);
                    }

                    ScheduleModel scheduleModel = new ScheduleModel();

                    scheduleModel.setWeek(sheduleDocument.getElementsByTag("span").get(3).text());

                    scheduleModel.setDay(tdElement.children().get(0).text());

                    scheduleModel.setPairModels(pairModels);

                    scheduleModels.add(scheduleModel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getSchedule.start();
        try {
            getSchedule.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return scheduleModels;
    }
}


    