package dk.sdu.swe.data.seeders;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.models.Channel;
import org.hibernate.Session;

public class v2_CreateChannels {
    public static void run() throws Exception {
        Session session = DB.openSession();

        int users = ((Number) session.createSQLQuery("SELECT COUNT(*) FROM channels").getSingleResult()).intValue();
        if(users != 0) {
            return;
        }

        session.saveOrUpdate(new Channel("TV 2", "https://pbs.twimg.com/profile_images/469398737484144640/o1P1XYg8.png"));
        session.saveOrUpdate(new Channel("TV 2 Fri", "https://scontent.fcph2-1.fna.fbcdn.net/v/t1.6435-9/41502650_1847272165361954_6425328215060905984_n.jpg?_nc_cat=100&ccb=1-3&_nc_sid=09cbfe&_nc_ohc=AA3i-a1ACUwAX-9Kgoa&_nc_ht=scontent.fcph2-1.fna&oh=56c23ed42871bcdaf98e05c4aec1d8d9&oe=60B119A9"));
        session.saveOrUpdate(new Channel("TV 2 Charlie", "https://static.wikia.nocookie.net/logopedia/images/6/62/TV2_Charlie_Logo_%282017-present%29.png/revision/latest/scale-to-width-down/250?cb=20171115085533"));
        session.saveOrUpdate(new Channel("TV 2 Zulu", "https://digitalt.tv/wp-content/uploads/2019/07/kanallogo-tv2zulu.png"));

        session.close();
    }
}
