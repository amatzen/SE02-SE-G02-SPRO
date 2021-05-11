package dk.sdu.swe.data.dao;

import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.persistence.IChannelDAO;

public class ChannelDAOImpl extends AbstractDAO<Channel> implements IChannelDAO {
    private static ChannelDAOImpl instance;

    public static IChannelDAO getInstance() {
        if (instance == null) {
            instance = new ChannelDAOImpl();
        }
        return instance;
    }

    private ChannelDAOImpl() {
        super(Channel.class);
    }
}
