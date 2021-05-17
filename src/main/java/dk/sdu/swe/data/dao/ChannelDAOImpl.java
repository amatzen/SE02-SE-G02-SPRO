package dk.sdu.swe.data.dao;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.models.User;
import dk.sdu.swe.domain.persistence.IChannelDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class ChannelDAOImpl extends AbstractDAO<Channel> implements IChannelDAO {
    private static ChannelDAOImpl instance;

    public synchronized static IChannelDAO getInstance() {
        if (instance == null) {
            instance = new ChannelDAOImpl();
        }
        return instance;
    }

    private ChannelDAOImpl() {
        super(Channel.class);
    }

    @Override
    public Optional<Channel> getByEpgId(Long epgId) {
        Session session = DB.openSession();
        Channel channel = null;
        Transaction trans = session.beginTransaction();
        try {
            channel = session.byNaturalId(Channel.class)
                .using("epgIdentifier", epgId).load();
        } finally {
            trans.commit();
            session.close();
        }
        return Optional.ofNullable(channel);
    }

}
