package com.scs.web.uni_space.util;

import com.scs.web.uni_space.domain.entity.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author mq_xu
 * @ClassName JSoupSpider
 * @Description JSoup实现的一个爬虫工具
 * @Date 9:13 2019/11/7
 * @Version 1.0
 **/
public class JSoupSpider {
    private static Logger logger = LoggerFactory.getLogger(JSoupSpider.class);
    private static Random random = new Random();

    /**
     * 爬取用户信息
     *
     * @return
     */
    public static List<User> getUsers() {
        Document document = null;
        List<User> userList = new ArrayList<>(100);
        for (int i = 1; i <= 3; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("i=" + i + "的连接失败");
            }
            Elements divs = document.getElementsByClass("col-xs-8");
            divs.forEach(div -> {
                Element wrapDiv = div.child(0);
                Element link = wrapDiv.child(0);
                Elements linkChildren = link.children();
                User user = new User();
                user.setMobile(DataUtil.getMobile());
                user.setPassword(DataUtil.getPassword());
                user.setAccount("");
                user.setGender(DataUtil.getGender());
                user.setAvatar("https:" + linkChildren.get(0).attr("src"));
                user.setNickname(linkChildren.get(1).text());
                user.setEmail("");
                user.setIntroduction(linkChildren.get(2).text());
                user.setBirthday(DataUtil.getBirthday());
                // 设置星座
                user.setConstellation(DataUtil.getConstellation());
                // 设置地址
//                user.setAddress(DataUtil.getAddress());
                user.setAddress("江苏省南京市");
                user.setSkinId(0L);
                userList.add(user);
            });
        }
        return userList;
    }

    public static List<Journal> getJournal() {
        Document document = null;
        List<Journal> journalList = new ArrayList<>(100);
        int j = 0;
        for (int i = 0; i <= 180; ) {
            try {
                document = Jsoup.connect("https://book.douban.com/review/best/?start=" + i).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements mainList = document.getElementsByClass("main review-item");
            mainList.forEach(item -> {
                String cover = item.child(0).child(0).attr("src");
                String publishTime = null;
                if (item.child(1).children().size() == 4) {
                    publishTime = item.child(1).child(3).text();
                } else {
                    publishTime = item.child(1).child(2).text();
                }
                String title = item.child(2).child(0).child(0).text();
                String likes = item.child(2).child(3).child(0).text();
                String co = item.child(2).child(3).child(2).text();
                String comments = co.substring(0, co.length() - 2);

                Journal journal = new Journal();
                journal.setUserId((long) (random.nextInt(71) + 1));
                journal.setTitle(title);
                journal.setThumbnail(cover);
                journal.setComments(Integer.valueOf(comments));
                journal.setLikes(Integer.valueOf(likes));
                journal.setCreateTime(Timestamp.valueOf(publishTime));

                String textUrl = item.child(2).child(0).child(0).attr("href");
                Document document1 = null;
                try {
                    document1 = Jsoup.connect(textUrl).get();
                } catch (IOException e) {
                    logger.error("连接失败");
                }
                Elements re = document1.getElementsByClass("review-content clearfix");
                Elements ps = re.get(0).select("p");
                StringBuilder textHtml = new StringBuilder();
                ps.forEach(p -> {
                    if (!p.text().equals("")) {
                        textHtml.append(p);
                    }
                });
                journal.setContent(textHtml.toString());
                journalList.add(journal);
            });
            j++;
            i = 2 * j * 10;
            System.out.println(i);
        }
        System.out.println(journalList.size());
        return journalList;
    }

    public static List<Music> getMusic() {
        List<Music> musicList = new ArrayList<>(100);
        Document document = null;
        for (int i = 0; i <= 4; i++) {
            try {
                document = Jsoup.connect("http://www.htqyy.com/genre/musicList/11?pageIndex=" + i + "&pageSize=20&order=hot").get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements titles = document.getElementsByClass("title");
            titles.forEach(title -> {
                Music music = new Music();
                Element a = title.child(0);
                String name = a.text();
                String url = a.attr("href");
                Document documentPlus = null;
                try {
                    documentPlus = Jsoup.connect("http://www.htqyy.com" + url).get();
                } catch (IOException e) {
                    logger.error("连接失败");
                }
                Elements jplayerWraps = documentPlus.getElementsByClass("jplayerWrap");
                Element jqueryDiv = jplayerWraps.get(0);
                Element audio = jqueryDiv.child(1);


                musicList.add(music);
            });
        }


        return musicList;
    }

    /**
     * 获取图片
     * @return
     */
    public static List<Photo> getPhoto() {
        Document document = null;
        List<Photo> photoList = new ArrayList<>(100);
        for (int i = 60; i <= 100; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("i=" + i + "的连接失败");
            }
            Elements divs = document.getElementsByClass("col-xs-8");
            divs.forEach(div -> {
                Element wrapDiv = div.child(0);
                Element link = wrapDiv.child(0);
                Elements linkChildren = link.children();
                Photo photo = new Photo();
                photo.setAlbumId((long)(random.nextInt(95) + 1));
                photo.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
                photo.setUrl("https:" + linkChildren.get(0).attr("src"));
                photo.setDescription(linkChildren.get(1).text());
                photoList.add(photo);
            });
        }
        return photoList;
    }

    public static List<PhotoAlbum> getPhotoAlbum() {
        Document document = null;
        List<PhotoAlbum> photoAlbumList = new ArrayList<>(100);
        for (int i = 21; i <= 24; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("i=" + i + "的连接失败");
            }
            Elements divs = document.getElementsByClass("col-xs-8");
            divs.forEach(div -> {
                Element wrapDiv = div.child(0);
                Element link = wrapDiv.child(0);
                Elements linkChildren = link.children();
                PhotoAlbum photoAlbum = new PhotoAlbum();
                photoAlbum.setUserId((long)(random.nextInt(71) + 1));
                photoAlbum.setCover("https:" + linkChildren.get(0).attr("src"));
                photoAlbum.setName(linkChildren.get(1).text());
                photoAlbum.setType(DataUtil.getType());
                photoAlbum.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
                photoAlbum.setIntroduction(linkChildren.get(2).text());
                photoAlbumList.add(photoAlbum);
            });
        }
        return photoAlbumList;
    }
}
