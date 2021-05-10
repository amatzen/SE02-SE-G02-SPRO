package dk.sdu.swe.data.dao;

import dk.sdu.swe.domain.models.Channel;

public class ChannelDAOImpl extends AbstractDAO<Channel> {
    private static ChannelDAOImpl instance;

    public static ChannelDAOImpl getInstance() {
        if (instance == null) {
            instance = new ChannelDAOImpl();
        }
        return instance;
    }

    private ChannelDAOImpl() {
        super(Channel.class);
    }
}
