package dk.sdu.swe.persistence.dao;

import dk.sdu.swe.persistence.DB;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.persistence.IChannelDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

/**
 * The type Channel dao.
 */
public class ChannelDAOImpl extends AbstractDAO<Channel> implements IChannelDAO {
    private static ChannelDAOImpl instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
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
