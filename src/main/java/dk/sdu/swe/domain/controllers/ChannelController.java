package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.ChannelDAOImpl;
import dk.sdu.swe.domain.controllers.contracts.IChannelController;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.persistence.IChannelDAO;

import java.util.List;

public class ChannelController implements IChannelController {

    private IChannelDAO channelDAO;

    private static IChannelController instance;

    private ChannelController() {
        channelDAO = ChannelDAOImpl.getInstance();
    }

    public static IChannelController getInstance() {
        if (instance == null) {
            instance = new ChannelController();
        }
        return instance;
    }

    @Override
    public List<Channel> getAll() {
        return channelDAO.getAll();
    }

}
