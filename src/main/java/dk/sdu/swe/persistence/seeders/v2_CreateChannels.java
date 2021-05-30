package dk.sdu.swe.persistence.seeders;

import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.persistence.DB;
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
        if (channels != 0) {
            return;
        }

        Channel a = (new Channel("TV 2", "https://digitalt.tv/pics/kanalloger/tv2.png"));
        Channel b = (new Channel("TV 2 Fri", "https://digitalt.tv/pics/kanalloger/tv2fri.png"));
        Channel c = (new Channel("TV 2 Charlie", "https://digitalt.tv/pics/kanalloger/tv2charlie.png"));
        Channel d = (new Channel("TV 2 Zulu", "https://digitalt.tv/pics/kanalloger/tv2zulu.png"));
        Channel e = (new Channel("TV 2 News", "https://digitalt.tv/pics/kanalloger/tv2news.png"));
        Channel f = (new Channel("TV 2 Sport", "https://digitalt.tv/pics/kanalloger/tv2sport.png"));
        Channel g = (new Channel("TV 2 Sport X", "https://digitalt.tv/pics/kanalloger/tv2sportx.png"));


        // Values fetched from
        // https://tvtid-api.api.tv2.dk/api/tvtid/v1/schedules/channels
        a.setEpgId(3L);
        b.setEpgId(12566L);
        c.setEpgId(31L);
        d.setEpgId(4L);
        e.setEpgId(133L);
        f.setEpgId(77L);
        g.setEpgId(2147483561L);

        session.saveOrUpdate(a);
        session.saveOrUpdate(b);
        session.saveOrUpdate(c);
        session.saveOrUpdate(d);
        session.saveOrUpdate(e);
        session.saveOrUpdate(f);
        session.saveOrUpdate(g);

        session.close();
    }
}
