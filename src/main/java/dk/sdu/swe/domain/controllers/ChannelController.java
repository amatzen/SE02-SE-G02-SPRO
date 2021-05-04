package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.Programme;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ChannelController {

    private static ChannelController instance;

    private ChannelController() {

    }

    public static ChannelController getInstance() {
        if (instance == null) {
            instance = new ChannelController();
        }
        return instance;
    }

    public List<Channel> getAll() {
        Session session = DB.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Channel> criteriaQuery = criteriaBuilder.createQuery(Channel.class);
        Root<Channel> channelRoot = criteriaQuery.from(Channel.class);
        criteriaQuery.select(channelRoot);

        List<Channel> res = session.createQuery(criteriaQuery).getResultList();

        session.close();

        return res;
    }

}
