package dk.sdu.swe.persistence.seeders;

import dk.sdu.swe.persistence.DB;
import dk.sdu.swe.domain.models.Channel;
import org.hibernate.Session;

/**
 * The type V 2 create channels.
 */
public class v2_CreateChannels {
    /**
     * Run.
     *
     * @throws Exception the exception
     */
    public static void run() throws Exception {
        Session session = DB.openSession();

        int channels = ((Number) session.createSQLQuery("SELECT COUNT(*) FROM channels").getSingleResult()).intValue();
        if(channels != 0) {
            return;
        }

        Channel a = (new Channel("TV 2", "https://pbs.twimg.com/profile_images/469398737484144640/o1P1XYg8.png"));
        Channel b = (new Channel("TV 2 Fri", "https://scontent.fcph2-1.fna.fbcdn.net/v/t1.6435-9/41502650_1847272165361954_6425328215060905984_n.jpg?_nc_cat=100&ccb=1-3&_nc_sid=09cbfe&_nc_ohc=AA3i-a1ACUwAX-9Kgoa&_nc_ht=scontent.fcph2-1.fna&oh=56c23ed42871bcdaf98e05c4aec1d8d9&oe=60B119A9"));
        Channel c = (new Channel("TV 2 Charlie", "https://static.wikia.nocookie.net/logopedia/images/6/62/TV2_Charlie_Logo_%282017-present%29.png/revision/latest/scale-to-width-down/250?cb=20171115085533"));
        Channel d = (new Channel("TV 2 Zulu", "https://digitalt.tv/wp-content/uploads/2019/07/kanallogo-tv2zulu.png"));

        // Values fetched from
        // https://tvtid-api.api.tv2.dk/api/tvtid/v1/schedules/channels
        a.setEpgId(3L);
        b.setEpgId(12566L);
        c.setEpgId(31L);
        d.setEpgId(4L);

        session.saveOrUpdate(a);
        session.saveOrUpdate(b);
        session.saveOrUpdate(c);
        session.saveOrUpdate(d);

        session.close();
    }
}
